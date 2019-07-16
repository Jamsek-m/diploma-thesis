package com.mjamsek.metrics.services;

import com.mjamsek.metrics.lib.dto.HeatmapRequest;
import com.mjamsek.metrics.lib.heatmap.HeatmapReport;
import com.mjamsek.metrics.lib.load.PageLoadReport;
import com.mjamsek.metrics.lib.socket.session.AppStartupMessage;
import com.mjamsek.metrics.lib.socket.session.MouseTrackMessage;
import com.mjamsek.metrics.lib.socket.session.PageLoadMessage;
import com.mjamsek.metrics.lib.startup.AppStartupReport;

public interface MetricsService {
    
    void handleMouseTracking(MouseTrackMessage message);
    
    void handleAppStartupTracking(AppStartupMessage message);
    
    void handlePageLoadTracking(PageLoadMessage message);
    
    HeatmapReport generateHeatmapReport(HeatmapRequest request, Long minHeatLevel);
    
    AppStartupReport generateAppStartupReport(String applicationName);
    
    PageLoadReport generatePageLoadReport(String applicationName);
}
