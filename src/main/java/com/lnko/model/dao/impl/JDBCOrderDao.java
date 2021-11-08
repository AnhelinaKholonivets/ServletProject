package com.lnko.model.dao.impl;

import com.lnko.model.dao.OrderDao;
import com.lnko.model.entity.Order;
import com.lnko.model.entity.Product;
import com.lnko.model.entity.Tariff;
import com.lnko.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCOrderDao implements OrderDao {
    private static final String SELECT_ALL_QUERY
            = "select orders.id, email, p.name, t.name, price, date_time from orders " +
            "join users u on orders.user_id = u.id " +
            "join tariffs t on orders.tariff_id = t.id " +
            "join products p on t.product_id = p.id ";
    private static final String SELECT_ALL_BY_USER_ID
            = "select orders.id, email, p.name, t.name, price, date_time from orders " +
            "join users u on orders.user_id = u.id " +
            "join tariffs t on orders.tariff_id = t.id " +
            "join products p on t.product_id = p.id " +
            "where orders.user_id = ?";

    private final Connection connection;

    public JDBCOrderDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Order entity) {

    }

    @Override
    public Order findById(Long id) {
        return null;
    }

    @Override
    public List<Order> findAll() {
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY)) {
            ResultSet resultSet = preparedStatement.executeQuery();

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
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BY_USER_ID)) {
            preparedStatement.setLong(1, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                orders.add(mapResultSet(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    private Order mapResultSet(ResultSet resultSet) {
        if (resultSet == null) {
            return null;
        }
        Order order = new Order();
        try {
            while (resultSet.next()) {
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return order;
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
}
