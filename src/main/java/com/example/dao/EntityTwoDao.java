package com.example.dao;

import com.example.model.EntityTwo;

import java.util.List;

public interface EntityTwoDao {

    List<EntityTwo> getAllEntities();

    void saveEntity(EntityTwo entityTwo);

    void updateEntity(EntityTwo entityTwo);

    void deleteEntity(EntityTwo entityTwo);
}
