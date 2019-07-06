package com.mjamsek.metrics.mappers;

import com.mjamsek.metrics.entities.db.MouseTrackRecordEntity;
import com.mjamsek.metrics.lib.heatmap.HeatRecord;

public class MetricsMapper {
    
    public static HeatRecord toHeatRecord(MouseTrackRecordEntity entity) {
        HeatRecord record = new HeatRecord();
        record.setHeatLevel(entity.getHeatLevel());
        record.setMouseX(entity.getMouseX());
        record.setMouseY(entity.getMouseY());
        return record;
    }
}
