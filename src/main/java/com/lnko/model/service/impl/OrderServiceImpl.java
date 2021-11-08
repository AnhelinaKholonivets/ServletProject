package com.lnko.model.service.impl;

import com.lnko.model.dao.DaoFactory;
import com.lnko.model.dao.OrderDao;
import com.lnko.model.entity.Order;
import com.lnko.model.entity.User;
import com.lnko.model.service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    @Override
    public List<Order> getAllOrders() {
        try (OrderDao dao = daoFactory.createOrderDao()) {
            return dao.findAll();
        }
    }

    @Override
    public List<Order> getAllOrdersByUser(User user) {
        try (OrderDao dao = daoFactory.createOrderDao()) {
            return dao.findAllByUser(user);
        }
    }

    @Override
    public List<Order> saveNewOrders(List<Long> tariffsIds, Long userId) {
        return null;
    }

}
