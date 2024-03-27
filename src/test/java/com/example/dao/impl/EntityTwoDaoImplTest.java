package com.example.dao.impl;

import com.example.model.EntityTwo;
import com.example.util.DatabaseConnection;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EntityTwoDaoImplTest {

    private static final String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS entity2 (id SERIAL PRIMARY KEY, name VARCHAR(255))";
    private static final String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS entity2";

    private static EntityTwoDaoImpl entityTwoDao;

    @BeforeAll
    static void setUp() throws SQLException {
        entityTwoDao = new EntityTwoDaoImpl();

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {

            statement.execute(CREATE_TABLE_QUERY);
        }
    }

    @AfterAll
    static void tearDown() throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {

            statement.execute(DROP_TABLE_QUERY);
        }
    }

    @Test
    void testSaveAndGetAllEntities() {
        EntityTwo entity = new EntityTwo("Test Entity");
        entityTwoDao.saveEntity(entity);

        List<EntityTwo> entities = entityTwoDao.getAllEntities();

        assertNotNull(entities);
        assertEquals(1, entities.size());
    }

    @Test
    void testUpdateEntity() {
        EntityTwo entity = new EntityTwo("Original Name");
        entityTwoDao.saveEntity(entity);

        entity.setName("Updated Name");
        entityTwoDao.updateEntity(entity);

        List<EntityTwo> entities = entityTwoDao.getAllEntities();

        assertEquals("Updated Name", entities.get(0).getName());
    }

    @Test
    void testDeleteEntity() {
        EntityTwo entity = new EntityTwo("To be deleted");
        entityTwoDao.saveEntity(entity);

        List<EntityTwo> initialEntities = entityTwoDao.getAllEntities();

        entityTwoDao.deleteEntity(entity);

        List<EntityTwo> remainingEntities = entityTwoDao.getAllEntities();

        assertEquals(initialEntities.size() - 1, remainingEntities.size());
    }
}