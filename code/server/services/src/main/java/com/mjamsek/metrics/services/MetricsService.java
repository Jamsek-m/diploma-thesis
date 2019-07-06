package com.mjamsek.metrics.services;

import com.mjamsek.metrics.lib.heatmap.HeatmapReport;
import com.mjamsek.metrics.lib.socket.session.MouseTrackMessage;

public interface MetricsService {
    
    void handleMouseTracking(MouseTrackMessage message);
    
    HeatmapReport generateHeatmapReport(String applicationName, Long minHeatLevel);
}
