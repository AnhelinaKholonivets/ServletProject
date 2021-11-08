package com.lnko.model.dao;

import com.lnko.model.entity.User;

import java.util.List;

public interface UserDao extends GenericDao<User>{
    User findByLogin(String login);
    List<User> findAll();
}
