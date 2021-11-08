package com.lnko.model.service.impl;

import com.lnko.model.dao.DaoFactory;
import com.lnko.model.dao.UserDao;
import com.lnko.model.entity.User;
import com.lnko.model.service.UserService;

import java.math.BigDecimal;
import java.util.List;

public class UserServiceImpl implements UserService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    @Override
    public List<User> getAllUsers(){
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.findAll();
        }
    }

    @Override
    public User getUserByLogin(String login){
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.findByLogin(login);
        }
    }

    @Override
    public void refileBalance(Long id, BigDecimal addBalance) {
        try (UserDao dao = daoFactory.createUserDao()) {
           // dao.update(user);
        }
    }

    @Override
    public void blockUser(Long id) {

    }

    @Override
    public void saveUser(User user){
        try (UserDao dao = daoFactory.createUserDao()) {
            dao.update(user);
        }
    }

}