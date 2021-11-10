package com.lnko.model.service;

import com.lnko.model.entity.User;

import java.math.BigDecimal;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserByLogin(String login);

    void refileBalance(User user, BigDecimal addBalance);

    void blockUser(Long id);

    void saveNewUser(User user);
}
