package com.lnko.model.dao;

import com.lnko.model.entity.Order;
import com.lnko.model.entity.User;

import java.util.List;

public interface OrderDao extends GenericDao<Order> {
    List<Order> findAll();
    List<Order> findAllByUser(User user);
}