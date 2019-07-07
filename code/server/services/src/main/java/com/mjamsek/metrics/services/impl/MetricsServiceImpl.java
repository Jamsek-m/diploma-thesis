package com.mjamsek.metrics.services.impl;

import com.mjamsek.metrics.entities.db.MouseTrackRecordEntity;
import com.mjamsek.metrics.lib.dto.HeatmapRequest;
import com.mjamsek.metrics.lib.exceptions.ApplicationNotFoundException;
import com.mjamsek.metrics.lib.heatmap.HeatRecord;
import com.mjamsek.metrics.lib.heatmap.HeatmapReport;
import com.mjamsek.metrics.lib.socket.session.MouseTrackMessage;
import com.mjamsek.metrics.mappers.MetricsMapper;
import com.mjamsek.metrics.services.MetricsService;
import com.mjamsek.metrics.utils.UrlUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;
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
                    coordinates.getPageX() / 10,
                    coordinates.getPageY() / 10
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
                    
                    // consolidate coordinates inside 10x10px box
                    entity.setMouseX(coordinates.getPageX() / 10);
                    entity.setMouseY(coordinates.getPageY() / 10);
                    entity.setPathname(UrlUtil.normalizePathname(coordinates.getPathname()));
                    // set initial heat of 1
                    entity.setHeatLevel(1L);
                    
                    em.persist(entity);
                }
            }
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
        return report;
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
