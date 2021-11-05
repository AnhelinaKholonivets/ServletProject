package com.lnko.model.service.impl;

import com.lnko.model.dao.DaoFactory;
import com.lnko.model.dao.UserDao;
import com.lnko.model.entity.User;
import com.lnko.model.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<User> getAllUsers(){
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.findAll();
        }
    }

    public User getUserByLogin(String login, String password){
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.findByLogin(login, password);
        }
    }
}