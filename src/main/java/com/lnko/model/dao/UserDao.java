package com.lnko.model.dao;

import com.lnko.model.entity.User;

import java.util.List;

public interface UserDao extends GenericDao<User>{
    User findByLogin(String login);
    void updateBalance(User user);
    void updateStatus(User user);
    List<User> findAllPage(int start, int size);
}
