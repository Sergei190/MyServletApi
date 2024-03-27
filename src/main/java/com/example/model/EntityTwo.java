package com.example.model;

public class EntityTwo {

    private int id;
    private String name;

    public EntityTwo() {
    }

    public EntityTwo(String name) {
        this.name = name;
    }

    public EntityTwo(int id, String name) {
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
}
