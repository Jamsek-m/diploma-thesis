package com.mjamsek.metrics.lib.load;

import java.util.List;

public class ResourceLoadReport {
    
    private List<SingleResourceReport> resources;
    
    public List<SingleResourceReport> getResources() {
        return resources;
    }
    
    public void setResources(List<SingleResourceReport> resources) {
        this.resources = resources;
    }
}
