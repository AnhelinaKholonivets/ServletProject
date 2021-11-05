package com.lnko.model.dao;

import com.lnko.model.entity.User;

public interface UserDao extends GenericDao<User>{
    User findByLogin(String login, String password);
}
