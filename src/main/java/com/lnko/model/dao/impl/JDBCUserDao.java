package com.lnko.model.dao.impl;

import com.lnko.controller.util.ConnectionManager;
import com.lnko.model.dao.UserDao;
import com.lnko.model.entity.Role;
import com.lnko.model.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCUserDao implements UserDao {
    private static final String SELECT_USER_BY_ID_QUERY = "select * from users where id = ?";
    private static final String SELECT_USER_BY_LOGIN_QUERY = "select * from users where email = ?";
    private static final String SELECT_ALL_QUERY = "select * from users";
    private static final String CREATE_USER_QUERY = "insert into users (first_name, last_name, email, " +
            "password, balance, blocked, role) " +
            "values (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_USER_BALANCE = "update users set balance = ? where id = ?";

    private final Connection connection;

    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(User user) {
        try {
            PreparedStatement ps = connection.prepareStatement(CREATE_USER_QUERY);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setBigDecimal(5, user.getBalance());
            ps.setBoolean(6, false);
            ps.setString(7, Role.USER.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findByLogin(String login) {
        User user = null;
        try {
            PreparedStatement ps = connection.prepareStatement(SELECT_USER_BY_LOGIN_QUERY);
            ps.setString(1, login);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                user = mapResultSet(resultSet);
            }

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
            PreparedStatement ps = conn.prepareStatement(SELECT_USER_BY_ID_QUERY);
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                user = mapResultSet(resultSet);
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
            Statement st = connection.createStatement();
            st.executeQuery(SELECT_ALL_QUERY);
            ResultSet resultSet = st.getResultSet();

            while (resultSet.next()) {
                users.add(mapResultSet(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void updateBalance(User user) {
        try {
            PreparedStatement ps = connection.prepareStatement(UPDATE_USER_BALANCE);
            ps.setBigDecimal(1, user.getBalance());
            ps.setLong(2, user.getId());
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    private User mapResultSet(ResultSet resultSet) throws SQLException {
        if (resultSet == null) {
            return null;
        }
        User user = new User();

        user.setId(resultSet.getLong("id"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        user.setBalance(resultSet.getBigDecimal("balance"));
        user.setBlocked(resultSet.getBoolean("blocked"));
        user.setRole(resultSet.getString("role"));

        return user;
    }
}
