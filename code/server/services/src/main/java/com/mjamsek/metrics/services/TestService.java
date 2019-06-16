package com.mjamsek.metrics.services;

import com.mjamsek.metrics.entities.TestEntity;

import java.util.List;

public interface TestService {
    
    List<TestEntity> getAll();
    
}