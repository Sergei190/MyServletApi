package com.example.dao.impl;

import com.example.dao.EntityOneDao;
import com.example.model.EntityOne;
import com.example.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EntityOneDaoImpl implements EntityOneDao {

    private static final String SELECT_ALL_ENTITIES = "SELECT * FROM entity1";
    private static final String SELECT_ENTITY_BY_ID = "SELECT * FROM entity1 WHERE id = ?";
    private static final String INSERT_ENTITY = "INSERT INTO entity1 (name) VALUES (?)";
    private static final String UPDATE_ENTITY = "UPDATE entity1 SET name = ? WHERE id = ?";
    private static final String DELETE_ENTITY = "DELETE FROM entity1 WHERE id = ?";

    @Override
    public List<EntityOne> getAllEntities() {
        List<EntityOne> entities = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ENTITIES)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                EntityOne entity1 = new EntityOne();
                entity1.setId(resultSet.getInt("id"));
                entity1.setName(resultSet.getString("name"));
                entities.add(entity1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entities;
    }

    @Override
    public void saveEntity(EntityOne entity1) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ENTITY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, entity1.getName());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                entity1.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateEntity(EntityOne entity1) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ENTITY)) {
            preparedStatement.setString(1, entity1.getName());
            preparedStatement.setInt(2, entity1.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteEntity(EntityOne entity1) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ENTITY)) {
            preparedStatement.setInt(1, entity1.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
