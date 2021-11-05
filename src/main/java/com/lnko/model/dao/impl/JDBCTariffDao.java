package com.lnko.model.dao.impl;

import com.lnko.controller.util.ConnectionManager;
import com.lnko.model.dao.TariffDao;
import com.lnko.model.entity.Order;
import com.lnko.model.entity.Product;
import com.lnko.model.entity.Tariff;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCTariffDao implements TariffDao {
    private static final String SELECT_ALL_QUERY
            = "select * from tariffs join products p on p.id = tariffs.product_id";
    private static final String SELECT_BY_ID_QUERY
            = "select * from tariffs join products p on p.id = tariffs.product_id where tariffs.id = ?";

    private final Connection connection;


    public JDBCTariffDao(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void create(Tariff entity) {

    }

    @Override
    public Tariff findById(Long id) {
        Tariff tariff = null;
        try {
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(SELECT_BY_ID_QUERY);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            tariff = mapResultSet(resultSet);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return tariff;
    }

    @Override
    public List<Tariff> findAll() {
        List<Tariff> tariffs = new ArrayList<>();
        try {
            Connection conn = ConnectionManager.getConnection();
            Statement statement = conn.createStatement();
            statement.executeQuery(SELECT_ALL_QUERY);
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                tariffs.add(mapResultSet(resultSet));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return tariffs;
    }

    @Override
    public void update(Tariff entity) {

    }

    @Override
    public void delete(Long id) {

    }


    private Tariff mapResultSet(ResultSet resultSet) {
        if (resultSet == null) {
            return null;
        }
        Tariff tariff = new Tariff();
        try {
            while (resultSet.next()) {
                tariff.setId(resultSet.getLong("tariffs.id"));
                tariff.setName(resultSet.getString("tariffs.name"));
                Product product = new Product();
                product.setId(resultSet.getLong("p.id"));
                product.setName(resultSet.getString("p.name"));
                tariff.setProduct(product);
                tariff.setPrice(resultSet.getBigDecimal("price"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tariff;
    }


    @Override
    public void close()  {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}