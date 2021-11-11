package com.lnko.service.impl;

import com.lnko.model.dao.DaoFactory;
import com.lnko.model.dao.ProductDao;
import com.lnko.model.entity.Product;

import java.util.List;

public class ProductServiceImpl implements com.lnko.service.ProductService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    @Override
    public List<Product> getAllProducts() {
        try (ProductDao dao = daoFactory.createProductDao()) {
            return dao.findAll();
        }
    }
}
