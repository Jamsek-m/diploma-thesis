package com.mjamsek.metrics.entities.db;

import javax.persistence.*;

@Entity
@Table(name = "resource_loads")
@NamedQueries({
    // @formatter:off
    @NamedQuery(name = ResourceLoadEntity.CALC_RESOURCE_LOAD,
    query = "SELECT new com.mjamsek.metrics.lib.load.SingleResourceReport(" +
                "r.name, r.resourceType, " +
                "avg(r.encodedBodySize), min(r.encodedBodySize), max(r.encodedBodySize), " +
                "avg(r.transferSize), min(r.transferSize), max(r.transferSize), " +
                "avg(r.responseEndTime - r.requestStartTime), min(r.responseEndTime - r.requestStartTime)," +
                "max(r.responseEndTime - r.requestStartTime)" +
            ") FROM ResourceLoadEntity r " +
            "WHERE r.application = :application " +
                "AND r.encodedBodySize IS NOT NULL " +
                "AND r.transferSize IS NOT NULL " +
                "AND r.responseEndTime IS NOT NULL AND r.requestStartTime IS NOT NULL " +
                "AND r.resourceType IN ('script', 'css', 'link', 'js') " +
            "GROUP BY r.name, r.resourceType"),
    @NamedQuery(name = ResourceLoadEntity.CALC_RESOURCE_LOAD_IGNORE_CACHE,
    query = "SELECT new com.mjamsek.metrics.lib.load.SingleResourceReport(" +
                "r.name, r.resourceType, " +
                "avg(r.encodedBodySize), min(r.encodedBodySize), max(r.encodedBodySize), " +
                "avg(r.transferSize), min(r.transferSize), max(r.transferSize), " +
                "avg(r.responseEndTime - r.requestStartTime), min(r.responseEndTime - r.requestStartTime)," +
                "max(r.responseEndTime - r.requestStartTime)" +
            ") FROM ResourceLoadEntity r " +
            "WHERE r.application = :application " +
                "AND r.encodedBodySize IS NOT NULL AND r.encodedBodySize > 0 " +
                "AND r.transferSize IS NOT NULL AND r.transferSize > 0 " +
                "AND r.responseEndTime IS NOT NULL AND r.requestStartTime IS NOT NULL " +
                "AND r.responseEndTime > 0 AND r.requestStartTime > 0 " +
                "AND r.resourceType IN ('script', 'css', 'link', 'js') " +
            "GROUP BY r.name, r.resourceType")
    // @formatter:on
})
public class ResourceLoadEntity extends SessionBaseEntity {
    
    public static final String CALC_RESOURCE_LOAD = "ResourceLoadEntity.calcResourceLoad";
    public static final String CALC_RESOURCE_LOAD_IGNORE_CACHE = "ResourceLoadEntity.calcResourceLoadIgnoreCache";
    
    @Column(name = "resource_type")
    private String resourceType;
    
    @Column(name = "decoded_body_size")
    private Integer decodedBodySize;
    
    @Column(name = "encoded_body_size")
    private Integer encodedBodySize;
    
    @Column(name = "transfer_size")
    private Integer transferSize;
    
    @Column(name = "redirect_time")
    private Integer redirectTime;
    
    @Column(name = "dns_time")
    private Integer DNSTime;
    
    @Column(name = "tcp_handle_time")
    private Integer TCPHandleTime;
    
    @Column(name = "secure_connection_time")
    private Integer secureConnectionTime;
    
    @Column(name = "response_time")
    private Integer responseTime;
    
    @Column(name = "request_start_time")
    private Integer requestStartTime;
    
    @Column(name = "response_end_time")
    private Integer responseEndTime;
    
    @Column(name = "name")
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
