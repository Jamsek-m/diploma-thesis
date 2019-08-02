package com.mjamsek.metrics.lib.load;

public class SingleResourceReport {
    
    private String name;
    
    private String type;
    
    private ResourceValue payloadSize;
    
    private ResourceValue requestSize;
    
    private ResourceValue requestTime;
    
    public SingleResourceReport() {
    
    }
    
    public SingleResourceReport(String name, String type,
                                Double averagePayloadSize, Integer minPayloadSize, Integer maxPayloadSize,
                                Double averageRequestSize, Integer minRequestSize, Integer maxRequestSize,
                                Double averageRequestTime, Integer minRequestTime, Integer maxRequestTime) {
        this.name = name;
        this.type = type;
        
        this.payloadSize = new ResourceValue();
        this.payloadSize.setAverage(averagePayloadSize);
        this.payloadSize.setMinimum(minPayloadSize);
        this.payloadSize.setMaximum(maxPayloadSize);
        
        this.requestSize = new ResourceValue();
        this.requestSize.setAverage(averageRequestSize);
        this.requestSize.setMinimum(minRequestSize);
        this.requestSize.setMaximum(maxRequestSize);
        
        this.requestTime = new ResourceValue();
        this.requestTime.setAverage(averageRequestTime);
        this.requestTime.setMinimum(minRequestTime);
        this.requestTime.setMaximum(maxRequestTime);
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public ResourceValue getPayloadSize() {
        return payloadSize;
    }
    
    public void setPayloadSize(ResourceValue payloadSize) {
        this.payloadSize = payloadSize;
    }
    
    public ResourceValue getRequestSize() {
        return requestSize;
    }
    
    public void setRequestSize(ResourceValue requestSize) {
        this.requestSize = requestSize;
    }
    
    public ResourceValue getRequestTime() {
        return requestTime;
    }
    
    public void setRequestTime(ResourceValue requestTime) {
        this.requestTime = requestTime;
    }
    
    public static class ResourceValue {
        private Double average;
        private Integer minimum;
        private Integer maximum;
    
        public Double getAverage() {
            return average;
        }
    
        public void setAverage(Double average) {
            this.average = average;
        }
    
        public Integer getMinimum() {
            return minimum;
        }
    
        public void setMinimum(Integer minimum) {
            this.minimum = minimum;
        }
    
        public Integer getMaximum() {
            return maximum;
        }
    
        public void setMaximum(Integer maximum) {
            this.maximum = maximum;
        }
    }
}
