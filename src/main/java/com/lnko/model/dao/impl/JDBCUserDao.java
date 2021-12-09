package com.lnko.model.dao.impl;

import com.lnko.model.dao.UserDao;
import com.lnko.model.entity.Role;
import com.lnko.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCUserDao implements UserDao {
    private static final Logger log = LogManager.getLogger();

    private static final String SELECT_USER_BY_ID_QUERY = "select * from users where id = ?";
    private static final String SELECT_USER_BY_LOGIN_QUERY = "select * from users where email = ?";
    private static final String SELECT_ALL_QUERY = "select * from users";
    private static final String SELECT_ALL_LIMIT_QUERY = "select * from users limit ?, ?";
    private static final String CREATE_USER_QUERY = "insert into users (first_name, last_name, email, " +
            "password, balance, blocked, role) values (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_USER_BALANCE_QUERY = "update users set balance = ? where id = ?";
    private static final String UPDATE_USER_STATUS_QUERY = "update users set blocked = ? where id = ?";

    private final Connection connection;

    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(User user) {
        try (PreparedStatement ps = connection.prepareStatement(CREATE_USER_QUERY)) {
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setBigDecimal(5, user.getBalance());
            ps.setBoolean(6, false);
            ps.setString(7, Role.USER.toString());
            ps.execute();

        } catch (SQLException e) {
            log.error("Error create user", e);
        }
    }

    @Override
    public User findByLogin(String login) {
        User user = null;

        try (PreparedStatement ps = connection.prepareStatement(SELECT_USER_BY_LOGIN_QUERY)) {
            ps.setString(1, login);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                user = mapResultSet(resultSet);
            }

        } catch (SQLException e) {
            log.error("Cannot get user by login", e);
        }
        return user;
    }

    @Override
    public User findById(Long id) {
        User user = null;

        try (PreparedStatement ps = connection.prepareStatement(SELECT_USER_BY_ID_QUERY)) {
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                user = mapResultSet(resultSet);
            }

        } catch (Exception e) {
            log.error("Cannot get user by id", e);
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();

        try (Statement st = connection.createStatement()) {
            st.executeQuery(SELECT_ALL_QUERY);
            ResultSet resultSet = st.getResultSet();

            while (resultSet.next()) {
                users.add(mapResultSet(resultSet));
            }

        } catch (SQLException e) {
            log.error("Cannot get users", e);
        }
        return users;
    }

    public List<User> findAllPage(int page, int size) {
        List<User> users = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(SELECT_ALL_LIMIT_QUERY)) {
            if (page == 1) {
                page = 0;
            }
            if (page > 1) {
                page = (page - 1) * 5;
            }
            ps.setInt(1, page);
            ps.setInt(2, size);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                users.add(mapResultSet(resultSet));
            }

        } catch (SQLException e) {
            log.error("Cannot get users", e);
        }
        return users;
    }

    @Override
    public void update(User user) {
    }

    @Override
    public void updateBalance(User user) {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_USER_BALANCE_QUERY)) {
            ps.setBigDecimal(1, user.getBalance());
            ps.setLong(2, user.getId());
            ps.execute();

        } catch (SQLException e) {
            log.error("Cannot update balance for user", e);
        }
    }

    @Override
    public void updateStatus(User user) {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_USER_STATUS_QUERY)) {
            ps.setBoolean(1, user.getBlocked());
            ps.setLong(2, user.getId());
            ps.execute();

        } catch (Exception e) {
            log.error("Cannot update status for user", e);
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
