package com.mjamsek.metrics.lib.socket.session;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResourceLoadMessage extends SocketSessionMessage {
    
    private String resourceType;
    
    private Integer decodedBodySize;
    
    private Integer encodedBodySize;
    
    private Integer transferSize;
    
    private Integer redirectTime;
    
    @JsonProperty("DNSTime")
    private Integer DNSTime;
    
    @JsonProperty("TCPHandleTime")
    private Integer TCPHandleTime;
    
    private Integer secureConnectionTime;
    
    private Integer responseTime;
    
    private Integer requestStartTime;
    
    private Integer responseEndTime;
    
    private String name;
    
    public String getResourceType() {
        return resourceType;
    }
    
    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }
    
    public Integer getDecodedBodySize() {
        return decodedBodySize;
    }
    
    public void setDecodedBodySize(Integer decodedBodySize) {
        this.decodedBodySize = decodedBodySize;
    }
    
    public Integer getEncodedBodySize() {
        return encodedBodySize;
    }
    
    public void setEncodedBodySize(Integer encodedBodySize) {
        this.encodedBodySize = encodedBodySize;
    }
    
    public Integer getTransferSize() {
        return transferSize;
    }
    
    public void setTransferSize(Integer transferSize) {
        this.transferSize = transferSize;
    }
    
    public Integer getRedirectTime() {
        return redirectTime;
    }
    
    public void setRedirectTime(Integer redirectTime) {
        this.redirectTime = redirectTime;
    }
    
    public Integer getDNSTime() {
        return DNSTime;
    }
    
    public void setDNSTime(Integer DNSTime) {
        this.DNSTime = DNSTime;
    }
    
    public Integer getTCPHandleTime() {
        return TCPHandleTime;
    }
    
    public void setTCPHandleTime(Integer TCPHandleTime) {
        this.TCPHandleTime = TCPHandleTime;
    }
    
    public Integer getSecureConnectionTime() {
        return secureConnectionTime;
    }
    
    public void setSecureConnectionTime(Integer secureConnectionTime) {
        this.secureConnectionTime = secureConnectionTime;
    }
    
    public Integer getResponseTime() {
        return responseTime;
    }
    
    public void setResponseTime(Integer responseTime) {
        this.responseTime = responseTime;
    }
    
    public Integer getRequestStartTime() {
        return requestStartTime;
    }
    
    public void setRequestStartTime(Integer requestStartTime) {
        this.requestStartTime = requestStartTime;
    }
    
    public Integer getResponseEndTime() {
        return responseEndTime;
    }
    
    public void setResponseEndTime(Integer responseEndTime) {
        this.responseEndTime = responseEndTime;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}
