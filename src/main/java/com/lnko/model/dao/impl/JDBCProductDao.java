package com.lnko.model.dao.impl;

import com.lnko.model.dao.ProductDao;
import com.lnko.model.entity.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBCProductDao implements ProductDao {
    private static final Logger log = LogManager.getLogger();

    private static final String SELECT_ALL_QUERY = "select * from products";

    private final Connection connection;

    public JDBCProductDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Product entity) {

    }

    @Override
    public Product findById(Long id) {
        return null;
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        try {
            Statement st = connection.createStatement();
            st.executeQuery(SELECT_ALL_QUERY);
            ResultSet resultSet = st.getResultSet();

            while (resultSet.next()) {
                products.add(mapResultSet(resultSet));
            }

        } catch (SQLException e) {
            log.error("Cannot get products", e);
        }
        return products;
    }

    @Override
    public void update(Product entity) {

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

    private Product mapResultSet(ResultSet resultSet) throws SQLException {
        if (resultSet == null) {
            return null;
        }
        Product product = new Product();
        product.setId(resultSet.getLong("id"));
        product.setName(resultSet.getString("name"));

        return product;
    }
}
