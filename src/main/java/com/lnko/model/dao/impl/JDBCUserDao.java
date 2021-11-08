package com.lnko.model.dao.impl;

import com.lnko.controller.util.ConnectionManager;
import com.lnko.model.dao.UserDao;
import com.lnko.model.entity.Role;
import com.lnko.model.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCUserDao implements UserDao {
    private static final String SELECT_USER_BY_ID = "select * from users where id = ?";
    private static final String SELECT_USER_BY_LOGIN = "select * from users where email = ?";
    private static final String SELECT_ALL_QUERY = "select * from users";
    private static final String CREATE_USER = "insert into users (first_name, last_name, email, password, balance, blocked, role)\n" +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";

    private final Connection connection;

    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setBigDecimal(5, user.getBalance());
            preparedStatement.setBoolean(6, false);
            preparedStatement.setString(7, Role.USER.toString());
        } catch (Exception ex) {
            //   log.info("{Error save user}");
        }
    }

    @Override
    public User findByLogin(String login) {
        User user = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_LOGIN);
            preparedStatement.setString(1, login);
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

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
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
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
                user.setPassword(resultSet.getString("password"));
                user.setBalance(resultSet.getBigDecimal("balance"));
                user.setBlocked(resultSet.getBoolean("blocked"));
                user.setRole(resultSet.getString("role"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
