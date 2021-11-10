package com.lnko.model.dao.impl;

import com.lnko.model.dao.TariffDao;
import com.lnko.model.entity.Product;
import com.lnko.model.entity.Tariff;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JDBCTariffDao implements TariffDao {
    private static final Logger log = LogManager.getLogger();

    private static final String SELECT_ALL_QUERY
            = "select * from tariffs join products p on p.id = tariffs.product_id";
    private static final String SELECT_ALL_BY_IDS_QUERY
            = "select * from tariffs join products p on p.id = tariffs.product_id where tariffs.id in (?)";
    private static final String SELECT_BY_ID_QUERY
            = "select * from tariffs join products p on p.id = tariffs.product_id where tariffs.id = ?";
    private static final String DELETE_TARIFF_BY_ID_QUERY = "delete from tariffs where id = ?";
    private static final String INSERT_TARIFF_QUERY = "insert into tariffs ( name, product_id, price) " +
            "values (?, ?, ?)";

    private final Connection connection;

    public JDBCTariffDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Tariff tariff) {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_TARIFF_QUERY)) {
            ps.setString(1, tariff.getName());
            ps.setLong(2, tariff.getProduct().getId());
            ps.setBigDecimal(3, tariff.getPrice());
            ps.execute();

        } catch (SQLException e) {
            log.error("Error create tariff", e);
            e.printStackTrace();
        }
    }

    @Override
    public Tariff findById(Long id) {
        Tariff tariff = null;

        try (PreparedStatement ps = connection.prepareStatement(SELECT_BY_ID_QUERY)) {
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();

            tariff = mapResultSet(resultSet);

        } catch (Exception e) {
            log.error("Cannot get tariff by id", e);
            e.printStackTrace();
        }
        return tariff;
    }

    @Override
    public List<Tariff> findAll() {
        List<Tariff> tariffs = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(SELECT_ALL_QUERY)) {
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                tariffs.add(mapResultSet(resultSet));
            }

        } catch (SQLException e) {
            log.error("Cannot get tariffs", e);
            e.printStackTrace();
        }

        return tariffs;
    }

    @Override
    public List<Tariff> findAllByIds(List<Long> tariffsIds) {
        List<Tariff> tariffs = new ArrayList<>();

        String queryParams = tariffsIds
                .stream()
                .map(i -> "?")
                .collect(Collectors.joining(","));

        String modifiedQuery = SELECT_ALL_BY_IDS_QUERY.replace("?", queryParams);

        try (PreparedStatement ps = connection.prepareStatement(modifiedQuery)) {
            for (int i = 1; i <= tariffsIds.size(); i++) {
                ps.setLong(i, tariffsIds.get(i - 1));
            }

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                tariffs.add(mapResultSet(resultSet));
            }

        } catch (SQLException e) {
            log.error("Cannot get tariffs by ids", e);
            e.printStackTrace();
        }

        return tariffs;
    }

    @Override
    public void update(Tariff tariff) {

    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement ps = connection.prepareStatement(DELETE_TARIFF_BY_ID_QUERY)) {
            ps.setLong(1, id);
            ps.execute();

        } catch (Exception e) {
            log.error("Error delete tariff", e);
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Tariff mapResultSet(ResultSet resultSet) throws SQLException {
        if (resultSet == null) {
            return null;
        }

        Tariff tariff = new Tariff();

        tariff.setId(resultSet.getLong("tariffs.id"));
        tariff.setName(resultSet.getString("tariffs.name"));
        Product product = new Product();
        product.setId(resultSet.getLong("p.id"));
        product.setName(resultSet.getString("p.name"));
        tariff.setProduct(product);
        tariff.setPrice(resultSet.getBigDecimal("price"));

        return tariff;
    }
}