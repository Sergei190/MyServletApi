package com.example.model;

import java.util.ArrayList;
import java.util.List;

public class ParentEntity {

    private int id;
    private String name;
    private List<ChildEntity> children;

    public ParentEntity() {
        children = new ArrayList<>();
    }

    public ParentEntity(String name) {
        this.name = name;
        children = new ArrayList<>();
    }

    public ParentEntity(int id, String name) {
        this.id = id;
        this.name = name;
        children = new ArrayList<>();
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

    public List<ChildEntity> getChildren() {
        return children;
    }

    public void setChildren(List<ChildEntity> children) {
        this.children = children;
    }

    public void addChild(ChildEntity childEntity) {
        children.add(childEntity);
    }
}
