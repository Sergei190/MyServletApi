package com.example.dao.impl;

import com.example.dao.EntityTwoDao;
import com.example.model.EntityTwo;
import com.example.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EntityTwoDaoImpl implements EntityTwoDao {

    private static final String SELECT_ALL_ENTITIES = "SELECT * FROM entity2";
    private static final String SELECT_ENTITY_BY_ID = "SELECT * FROM entity2 WHERE id = ?";
    private static final String INSERT_ENTITY = "INSERT INTO entity2 (name) VALUES (?)";
    private static final String UPDATE_ENTITY = "UPDATE entity2 SET name = ? WHERE id = ?";
    private static final String DELETE_ENTITY = "DELETE FROM entity2 WHERE id = ?";

    @Override
    public List<EntityTwo> getAllEntities() {
        List<EntityTwo> entities = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ENTITIES)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                EntityTwo entityTwo = new EntityTwo();
                entityTwo.setId(resultSet.getInt("id"));
                entityTwo.setName(resultSet.getString("name"));
                entities.add(entityTwo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entities;
    }

    @Override
    public void saveEntity(EntityTwo entityTwo) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ENTITY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, entityTwo.getName());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                entityTwo.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateEntity(EntityTwo entityTwo) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ENTITY)) {
            preparedStatement.setString(1, entityTwo.getName());
            preparedStatement.setInt(2, entityTwo.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteEntity(EntityTwo entityTwo) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ENTITY)) {
            preparedStatement.setInt(1, entityTwo.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
