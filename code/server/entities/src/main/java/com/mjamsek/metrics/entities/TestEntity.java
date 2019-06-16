package com.mjamsek.metrics.entities;

import javax.persistence.*;

@Entity
@Table(name = "test_table")
@NamedQueries({
    @NamedQuery(name = TestEntity.FIND_ALL, query = "SELECT t FROM TestEntity t")
})
public class TestEntity {
    
    public static final String FIND_ALL = "Test.findAll";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    private String text;
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public String getText() {
        return text;
    }
    
    public void setText(String text) {
        this.text = text;
    }
}
