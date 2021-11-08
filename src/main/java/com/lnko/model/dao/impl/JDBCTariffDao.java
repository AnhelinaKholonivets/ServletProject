package com.lnko.model.dao.impl;

import com.lnko.model.dao.TariffDao;
import com.lnko.model.entity.Product;
import com.lnko.model.entity.Tariff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCTariffDao implements TariffDao {
    private static final String SELECT_ALL_QUERY
            = "select * from tariffs join products p on p.id = tariffs.product_id";
    private static final String SELECT_BY_ID_QUERY
            = "select * from tariffs join products p on p.id = tariffs.product_id where tariffs.id = ?";
    private static final String DELETE_TARIFF_BY_ID_QUERY = "delete from tariffs where id = ?";
    private static final String INSERT_TARIFF_QUERY = "insert into tariffs (id, name, product_id, price) " +
            "values (?, ?, ?, ?)";

    private final Connection connection;

    public JDBCTariffDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Tariff tariff) {
       try {
           PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TARIFF_QUERY);
           preparedStatement.setLong(1, tariff.getId());
           preparedStatement.setString(2, tariff.getName());
           preparedStatement.setLong(3, tariff.getProduct().getId());
           preparedStatement.setBigDecimal(4, tariff.getPrice());

       } catch (SQLException e) {
           e.printStackTrace();
       }
    }

    @Override
    public Tariff findById(Long id) {
        Tariff tariff = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_QUERY);
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
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                tariffs.add(mapResultSet(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tariffs;
    }

    @Override
    public void update(Tariff entity) {

    }

    @Override
    public void delete(Long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TARIFF_BY_ID_QUERY);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
        }

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