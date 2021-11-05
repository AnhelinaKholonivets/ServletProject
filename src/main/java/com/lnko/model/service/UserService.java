package com.lnko.model.service;

import com.lnko.model.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserByLogin(String login, String password);
}
