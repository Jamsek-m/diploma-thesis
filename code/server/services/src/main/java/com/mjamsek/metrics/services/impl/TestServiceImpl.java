package com.mjamsek.metrics.services.impl;

import com.mjamsek.metrics.entities.TestEntity;
import com.mjamsek.metrics.services.TestService;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@ApplicationScoped
public class TestServiceImpl implements TestService {
    
    @PersistenceContext(unitName = "main-jpa-unit")
    private EntityManager em;
    
    @Override
    public List<TestEntity> getAll() {
        TypedQuery<TestEntity> query = em.createNamedQuery(TestEntity.FIND_ALL, TestEntity.class);
        return query.getResultList();
    }
}
