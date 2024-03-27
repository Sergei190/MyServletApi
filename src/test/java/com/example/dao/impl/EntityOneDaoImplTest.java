package com.example.dao.impl;

import com.example.model.EntityOne;
import com.example.util.DatabaseConnection;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.testng.AssertJUnit.assertEquals;

public class EntityOneDaoImplTest {

    private static final String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS entity1 (id SERIAL PRIMARY KEY, name VARCHAR(255))";
    private static final String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS entity1";

    private static EntityOneDaoImpl entityOneDao;

    @BeforeAll
    static void setUp() throws SQLException {
        entityOneDao = new EntityOneDaoImpl();

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
        EntityOne entity = new EntityOne("Test Entity");
        entityOneDao.saveEntity(entity);

        List<EntityOne> entities = entityOneDao.getAllEntities();

        assertNotNull(entities);
        assertEquals(1, entities.size());
    }


    @Test
    void testUpdateEntity() {
        EntityOne entity = new EntityOne("Original Name");
        entityOneDao.saveEntity(entity);

        entity.setName("Updated Name");
        entityOneDao.updateEntity(entity);

        List<EntityOne> entities = entityOneDao.getAllEntities();

        assertEquals("Updated Name", entities.get(0).getName());
    }

    @Test
    void testDeleteEntity() {
        EntityOne entity = new EntityOne("To be deleted");
        entityOneDao.saveEntity(entity);

        List<EntityOne> initialEntities = entityOneDao.getAllEntities();

        entityOneDao.deleteEntity(entity);

        List<EntityOne> remainingEntities = entityOneDao.getAllEntities();

        assertEquals(initialEntities.size() - 1, remainingEntities.size());
    }
}