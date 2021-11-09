package com.lnko.model.service;

import com.lnko.model.entity.Order;
import com.lnko.model.entity.User;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();
    List<Order> getAllOrdersByUser(User user);
    void saveNewOrders(List<Long> tariffsIds, Long userId);
//    Page<Order> findPaginated(Pageable pageable, Long userId);
}
