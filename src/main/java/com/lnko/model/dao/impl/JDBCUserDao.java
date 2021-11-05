package com.lnko.model.dao.impl;

import com.lnko.controller.util.ConnectionManager;
import com.lnko.model.dao.UserDao;
import com.lnko.model.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCUserDao implements UserDao {
    private static final String SELECT_USER_BY_ID = "select * from users where id = ?";
    private static final String SELECT_USER_BY_LOGIN = "select * from users where email = ? and password = ?";
    private static final String SELECT_ALL_QUERY = "select * from users";

    public JDBCUserDao(Connection connection) {
    }

    @Override
    public void create(User entity) {
    }

    @Override
    public User findByLogin(String login, String password) {
        User user = null;
        try {
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(SELECT_USER_BY_LOGIN);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            user = mapResultSet(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User findById(Long id) {
        User user = null;
        try {
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(SELECT_USER_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            user = mapResultSet(resultSet);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    private User mapResultSet(ResultSet resultSet) {
        if (resultSet == null) {
            return null;
        }
        User user = new User();
        try {
            while (resultSet.next()) {
                user.setId(resultSet.getLong("id"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setEmail(resultSet.getString("email"));
                user.setBalance(resultSet.getBigDecimal("balance"));
                user.setBlocked(resultSet.getBoolean("blocked"));
                user.setRole(resultSet.getString("role"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try {
            Connection conn = ConnectionManager.getConnection();
            Statement statement = conn.createStatement();
            statement.executeQuery(SELECT_ALL_QUERY);
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                users.add(mapResultSet(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void close() {

    }
}
