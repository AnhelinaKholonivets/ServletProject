package com.lnko.model.dao.impl;

import com.lnko.model.dao.OrderDao;
import com.lnko.model.entity.Order;
import com.lnko.model.entity.Product;
import com.lnko.model.entity.Tariff;
import com.lnko.model.entity.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JDBCOrderDao implements OrderDao {
    private static final String SELECT_ALL_QUERY
            = "select orders.id, email, p.name, t.name, price, date_time from orders " +
            "join users u on orders.user_id = u.id " +
            "join tariffs t on orders.tariff_id = t.id " +
            "join products p on t.product_id = p.id ";
    private static final String SELECT_ALL_BY_USER_ID_QUERY
            = "select orders.id, email, p.name, t.name, price, date_time from orders " +
            "join users u on orders.user_id = u.id " +
            "join tariffs t on orders.tariff_id = t.id " +
            "join products p on t.product_id = p.id " +
            "where orders.user_id = ?";
    private static final String INSERT_ORDER_QUERY = "insert into orders ( user_id, tariff_id, date_time) " +
            "values (?, ?, ?)";
    private static final String UPDATE_USER_BALANCE_QUERY = "update users set balance = ? where id = ?";

    private final Connection connection;

    public JDBCOrderDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Order order) {
        try {
            PreparedStatement ps = connection.prepareStatement(INSERT_ORDER_QUERY);
            ps.setLong(1, order.getId());
            ps.setLong(2, order.getUser().getId());
            ps.setLong(3, order.getTariff().getId());
            ps.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Order findById(Long id) {
        return null;
    }

    @Override
    public List<Order> findAll() {
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SELECT_ALL_QUERY)) {
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                orders.add(mapResultSet(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public List<Order> findAllByUser(User user) {
        List<Order> orders = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(SELECT_ALL_BY_USER_ID_QUERY);
            ps.setLong(1, user.getId());
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                orders.add(mapResultSet(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public void saveAllOrders(User user, List<Order> orders) {
        try {
            connection.setAutoCommit(false);

            PreparedStatement ps = connection.prepareStatement(UPDATE_USER_BALANCE_QUERY);
            ps.setBigDecimal(1, user.getBalance());
            ps.setLong(2, user.getId());
            ps.execute();

            ps = connection.prepareStatement(INSERT_ORDER_QUERY);
            for (Order order : orders) {
                ps.setLong(1, order.getUser().getId());
                ps.setLong(2, order.getTariff().getId());
                ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
                ps.addBatch();
            }
            ps.executeBatch();

            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Order entity) {

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

    private Order mapResultSet(ResultSet resultSet) throws SQLException {
        if (resultSet == null) {
            return null;
        }
        Order order = new Order();

        order.setId(resultSet.getLong("id"));
        User user = new User();
        user.setEmail(resultSet.getString("email"));
        order.setUser(user);
        Product product = new Product();
        product.setName(resultSet.getString("p.name"));
        Tariff tariff = new Tariff();
        tariff.setName(resultSet.getString("t.name"));
        tariff.setProduct(product);
        tariff.setPrice(resultSet.getBigDecimal("price"));
        order.setTariff(tariff);
        order.setDateTime(resultSet.getTimestamp("date_time").toLocalDateTime());

        return order;
    }
}
