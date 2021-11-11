package com.lnko.service.impl;

import com.lnko.exception.LowBalanceException;
import com.lnko.model.dao.DaoFactory;
import com.lnko.model.dao.OrderDao;
import com.lnko.model.dao.TariffDao;
import com.lnko.model.dao.UserDao;
import com.lnko.model.entity.Order;
import com.lnko.model.entity.Tariff;
import com.lnko.model.entity.User;
import com.lnko.service.OrderService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {
    private final DaoFactory daoFactory;

    public OrderServiceImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

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
    public void saveNewOrders(List<Long> tariffsIds, Long userId) {
        try (OrderDao orderDao = daoFactory.createOrderDao();
             TariffDao tariffDao = daoFactory.createTariffDao();
             UserDao userDao = daoFactory.createUserDao()) {

            List<Tariff> tariffs = tariffDao.findAllByIds(tariffsIds);

            BigDecimal orderPrice = tariffs.stream()
                    .map(Tariff::getPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            User user = userDao.findById(userId);

            if (user.getBalance().compareTo(orderPrice) < 0) {
                throw new LowBalanceException();
            }
            user.setBalance(user.getBalance().subtract(orderPrice));
            List<Order> orders = tariffs
                    .stream()
                    .map(tariff -> new Order(null, user, tariff, LocalDateTime.now()))
                    .collect(Collectors.toList());

            orderDao.saveAllOrders(user, orders);
        }
    }

}
