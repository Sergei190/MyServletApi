package com.example.dao;

import com.example.model.EntityOne;

import java.util.List;

public interface EntityOneDao {

    List<EntityOne> getAllEntities();

    void saveEntity(EntityOne entityOne);

    void updateEntity(EntityOne entityOne);

    void deleteEntity(EntityOne entityOne);
}
