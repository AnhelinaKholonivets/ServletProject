package com.lnko.model.service;

import com.lnko.model.entity.User;

import java.math.BigDecimal;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserByLogin(String login);

    void refileBalance(Long id, BigDecimal addBalance);

    void blockUser(Long id);

    void saveUser(User user);
}
