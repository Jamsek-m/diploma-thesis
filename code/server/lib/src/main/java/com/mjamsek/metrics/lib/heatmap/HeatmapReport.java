package com.mjamsek.metrics.lib.heatmap;

import java.util.List;

public class HeatmapReport {
    
    private List<HeatRecord> records;
    
    private Long recordsCount;
    
    private Integer mouseBoxSize;
    
    public List<HeatRecord> getRecords() {
        return records;
    }
    
    public void setRecords(List<HeatRecord> records) {
        this.records = records;
    }
    
    public Long getRecordsCount() {
        return recordsCount;
    }
    
    public void setRecordsCount(Long recordsCount) {
        this.recordsCount = recordsCount;
    }
    
    public Integer getMouseBoxSize() {
        return mouseBoxSize;
    }
    
    public void setMouseBoxSize(Integer mouseBoxSize) {
        this.mouseBoxSize = mouseBoxSize;
    }
}
