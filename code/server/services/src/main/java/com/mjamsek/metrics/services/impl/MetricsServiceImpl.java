package com.mjamsek.metrics.services.impl;

import com.mjamsek.metrics.config.MetricConfiguration;
import com.mjamsek.metrics.entities.db.AppStartupEntity;
import com.mjamsek.metrics.entities.db.MouseTrackRecordEntity;
import com.mjamsek.metrics.entities.db.PageLoadEntity;
import com.mjamsek.metrics.entities.db.ResourceLoadEntity;
import com.mjamsek.metrics.lib.dto.HeatmapRequest;
import com.mjamsek.metrics.lib.exceptions.ApplicationNotFoundException;
import com.mjamsek.metrics.lib.heatmap.HeatRecord;
import com.mjamsek.metrics.lib.heatmap.HeatmapReport;
import com.mjamsek.metrics.lib.load.*;
import com.mjamsek.metrics.lib.socket.session.AppStartupMessage;
import com.mjamsek.metrics.lib.socket.session.MouseTrackMessage;
import com.mjamsek.metrics.lib.socket.session.PageLoadMessage;
import com.mjamsek.metrics.lib.socket.session.ResourceLoadMessage;
import com.mjamsek.metrics.lib.startup.AppStartupReport;
import com.mjamsek.metrics.mappers.MetricsMapper;
import com.mjamsek.metrics.services.MetricsService;
import com.mjamsek.metrics.utils.UrlUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class MetricsServiceImpl implements MetricsService {
    
    @PersistenceContext(unitName = "main-jpa-unit")
    private EntityManager em;
    
    @Transactional
    @Override
    public void handleMouseTracking(MouseTrackMessage message) {
        if (message != null && message.getCoordinates() != null) {
            for (MouseTrackMessage.Coordinates coordinates : message.getCoordinates()) {
                
                MouseTrackRecordEntity existingEntity = getExistingCoordinate(
                    message.getApplication(),
                    message.getSessionId(),
                    UrlUtil.normalizePathname(coordinates.getPathname()),
                    coordinates.getPageX() / MetricConfiguration.MOUSE_TRACK_PIXELS,
                    coordinates.getPageY() / MetricConfiguration.MOUSE_TRACK_PIXELS
                );
                
                // check if coordinate exists
                if (existingEntity != null) {
                    existingEntity.incrementHeat(1L);
                    em.merge(existingEntity);
                } else {
                    // coordinates does not yet exists
                    MouseTrackRecordEntity entity = new MouseTrackRecordEntity();
                    entity.setApplication(message.getApplication());
                    entity.setSessionId(message.getSessionId());
                    
                    // consolidate coordinates inside y*z px box
                    entity.setMouseX(coordinates.getPageX() / MetricConfiguration.MOUSE_TRACK_PIXELS);
                    entity.setMouseY(coordinates.getPageY() / MetricConfiguration.MOUSE_TRACK_PIXELS);
                    entity.setPathname(UrlUtil.normalizePathname(coordinates.getPathname()));
                    // set initial heat of 1
                    entity.setHeatLevel(1L);
                    
                    em.persist(entity);
                }
            }
        }
    }
    
    @Transactional
    @Override
    public void handleAppStartupTracking(AppStartupMessage message) {
        if (message != null && message.getApplicationLoaded() != null && message.getNavigationStart() != null) {
            
            AppStartupEntity entity = new AppStartupEntity();
            entity.setApplicationLoaded(message.getApplicationLoaded());
            entity.setNavigationStart(message.getNavigationStart());
            entity.setSessionId(message.getSessionId());
            entity.setApplication(message.getApplication());
            
            em.persist(entity);
        }
    }
    
    @Transactional
    @Override
    public void handlePageLoadTracking(PageLoadMessage message) {
        if (message != null) {
            PageLoadEntity entity = new PageLoadEntity();
            entity.setApplication(message.getApplication());
            entity.setSessionId(message.getSessionId());
            entity.setPathname(UrlUtil.normalizePathname(message.getPathname()));
            entity.setLoadEnd(message.getLoadEnd());
            entity.setLoadStart(message.getLoadStart());
            entity.setFirstPage(message.getFirstPage());
            
            em.persist(entity);
        }
    }
    
    @Transactional
    @Override
    public void handleResourceLoadTracking(ResourceLoadMessage message) {
        if (message != null) {
            ResourceLoadEntity entity = new ResourceLoadEntity();
            entity.setName(message.getName());
            entity.setResourceType(message.getResourceType());
            entity.setDecodedBodySize(message.getDecodedBodySize());
            entity.setEncodedBodySize(message.getEncodedBodySize());
            entity.setTransferSize(message.getTransferSize());
            entity.setRedirectTime(message.getRedirectTime());
            entity.setDNSTime(message.getDNSTime());
            entity.setTCPHandleTime(message.getTCPHandleTime());
            entity.setSecureConnectionTime(message.getSecureConnectionTime());
            entity.setResponseTime(message.getResponseTime());
            entity.setRequestStartTime(message.getRequestStartTime());
            entity.setResponseEndTime(message.getResponseEndTime());
            entity.setApplication(message.getApplication());
            entity.setSessionId(message.getSessionId());
            
            em.persist(entity);
        }
    }
    
    @Override
    public HeatmapReport generateHeatmapReport(HeatmapRequest request, Long minHeatLevel) {
        TypedQuery<MouseTrackRecordEntity> query = em.createNamedQuery(MouseTrackRecordEntity.FIND_BY_APP_NAME, MouseTrackRecordEntity.class);
        query.setParameter("application", request.getApplicationName());
        query.setParameter("heatLevel", minHeatLevel);
        query.setParameter("pathname", request.getPathname());
        
        List<MouseTrackRecordEntity> records = query.getResultList();
        
        if (records.size() == 0) {
            throw new ApplicationNotFoundException();
        }
        
        List<HeatRecord> heatRecords = records.stream().map(MetricsMapper::toHeatRecord).collect(Collectors.toList());
        
        HeatmapReport report = new HeatmapReport();
        report.setRecords(heatRecords);
        report.setRecordsCount((long) heatRecords.size());
        report.setMouseBoxSize(MetricConfiguration.MOUSE_TRACK_PIXELS);
        return report;
    }
    
    @Override
    public AppStartupReport generateAppStartupReport(String applicationName, String percentileString) {
        List<String> percentiles = this.parsePercentileString(percentileString);
        
        AppStartupReport report = new AppStartupReport();
        
        Query query = em.createNamedQuery(AppStartupEntity.TIME_DIFF_STATISTICS);
        query.setParameter("application", applicationName);
        Object[] result = (Object[]) query.getSingleResult();
        
        report.setMaxLoadTime((Long) result[0]);
        report.setMinLoadTime((Long) result[1]);
        report.setAvgLoadTime((Double) result[2]);
        
        this.calculatePercentilesForAppLoad(applicationName, report, percentiles);
        
        return report;
    }
    
    @Override
    public PageLoadReport generatePageLoadReport(String applicationName,
                                                 boolean includeFirstPage,
                                                 String percentileString) {
        
        List<String> percentiles = this.parsePercentileString(percentileString);
        
        PageLoadReport report = new PageLoadReport();
        
        String pageReportQueryName = includeFirstPage ? PageLoadEntity.GET_BY_PAGE_WITH_FIRST : PageLoadEntity.GET_BY_PAGE;
        TypedQuery<SinglePageReport> query = em.createNamedQuery(pageReportQueryName, SinglePageReport.class);
        query.setParameter("application", applicationName);
        
        List<SinglePageReport> pageReports = query.getResultList();
        
        String avgPageReportQueryName = includeFirstPage ? PageLoadEntity.AVG_PAGE_LOAD_WITH_FIRST : PageLoadEntity.AVG_PAGE_LOAD;
        TypedQuery<Double> avgTimeQuery = em.createNamedQuery(avgPageReportQueryName, Double.class);
        avgTimeQuery.setParameter("application", applicationName);
        Double avgPageLoadTime = avgTimeQuery.getSingleResult();
        
        report.setPages(pageReports);
        report.setAveragePageLoadTime(avgPageLoadTime);
        
        this.calculatePercentilesForPageLoad(applicationName, report, includeFirstPage, percentiles);
        
        return report;
    }
    
    @Override
    public ResourceLoadReport generateResourceLoadReport(String applicationName, boolean ignoreCached) {
        String queryName = ignoreCached ? ResourceLoadEntity.CALC_RESOURCE_LOAD_IGNORE_CACHE : ResourceLoadEntity.CALC_RESOURCE_LOAD;
        TypedQuery<SingleResourceReport> query = em.createNamedQuery(queryName, SingleResourceReport.class);
        query.setParameter("application", applicationName);
        List<SingleResourceReport> resources = query.getResultList();
    
        if (resources.size() == 0) {
            throw new ApplicationNotFoundException();
        }
        
        ResourceLoadReport report = new ResourceLoadReport();
        report.setResources(resources);
        return report;
    }
    
    private void calculatePercentilesForAppLoad(String applicationName,
                                                AppStartupReport report,
                                                List<String> percentiles) {
        for (String percentile : percentiles) {
            try {
                Double percentileFraction = Double.parseDouble(percentile);
                if (percentileFraction < 0 || percentileFraction > 1) {
                    throw new NumberFormatException("Percentile must be decimal value between 0 and 1.");
                }
                
                Query query = em.createNamedQuery(AppStartupEntity.CALC_PERCENTILES);
                query.setParameter("application", applicationName);
                query.setParameter("percentile", percentileFraction);
                Double percentileValue = (Double) query.getSingleResult();
    
                SinglePercentileReport percentileReport = new SinglePercentileReport();
                percentileReport.setPercentile(percentileFraction);
                percentileReport.setValue(percentileValue);
                
                report.getPercentiles().add(percentileReport);
            } catch (NumberFormatException e) {
                // ignore misformatted percentiles
            }
        }
    }
    
    private void calculatePercentilesForPageLoad(String applicationName,
                                                 PageLoadReport report,
                                                 boolean includeFirstPage,
                                                 List<String> percentiles) {
        // handle each user-specified percentile
        for (String percentile : percentiles) {
            
            try {
                Double percentileFraction = Double.parseDouble(percentile);
                
                if (percentileFraction < 0 || percentileFraction > 1) {
                    throw new NumberFormatException("Percentile must be decimal value between 0 and 1.");
                }
                
                // get values for all pages for current percentile
                String queryName = includeFirstPage ? PageLoadEntity.CALC_PERCENTILE_WITH_FIRST : PageLoadEntity.CALC_PERCENTILE;
                Query query = em.createNamedQuery(queryName);
                query.setParameter(1, applicationName);
                query.setParameter(2, percentileFraction);
                List<Object[]> percentileResults = query.getResultList();
                
                // merge results with page report
                
                // for each row (page)
                for (Object[] pagePercentiles : percentileResults) {
                    Double percentileValue = (Double) pagePercentiles[0];
                    String pathname = (String) pagePercentiles[1];
                    
                    Optional<SinglePageReport> pageReport = report.getPages()
                        .stream()
                        .filter(r -> r.getPathname().equals(pathname))
                        .findFirst();
                    if (pageReport.isPresent()) {
                        
                        SinglePercentileReport percentileReport = new SinglePercentileReport();
                        percentileReport.setPercentile(percentileFraction);
                        percentileReport.setValue(percentileValue);
                        
                        pageReport.get().getPercentiles().add(percentileReport);
                    }
                }
            } catch (NumberFormatException e) {
                // Ignore misformatted percentiles
            }
        }
    }
    
    private List<String> parsePercentileString(String percentileString) {
        try {
            return Arrays.asList(percentileString.split(","));
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
    
    private MouseTrackRecordEntity getExistingCoordinate(String app, String sessionId, String pathname, Integer x, Integer y) {
        TypedQuery<MouseTrackRecordEntity> query = em.createNamedQuery(MouseTrackRecordEntity.FIND_EXISTING_COORDINATES, MouseTrackRecordEntity.class);
        query.setParameter("application", app);
        query.setParameter("sessionId", sessionId);
        query.setParameter("mouseX", x);
        query.setParameter("mouseY", y);
        query.setParameter("pathname", pathname);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (NonUniqueResultException e) {
            return query.getResultList().get(0);
        }
    }
}
