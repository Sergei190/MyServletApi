package com.example.model;

public class ChildEntity {

    private int id;
    private String name;
    private ParentEntity parentEntity;

    public ChildEntity() {
    }

    public ChildEntity(String name) {
        this.name = name;
    }

    public ChildEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ParentEntity getParentEntity() {
        return parentEntity;
    }

    public void setParentEntity(ParentEntity parentEntity) {
        this.parentEntity = parentEntity;
    }
}
