package com.lnko.service.impl;

import com.lnko.model.dao.DaoFactory;
import com.lnko.model.dao.UserDao;
import com.lnko.model.entity.User;
import com.lnko.service.UserService;

import java.math.BigDecimal;
import java.util.List;

public class UserServiceImpl implements UserService {
    private final DaoFactory daoFactory;

    public UserServiceImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public List<User> getAllUsers(){
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.findAll();
        }
    }

    @Override
    public List<User> getAllUsersPage(int page, int size){
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.findAllPage(page, size);
        }
    }

    @Override
    public User getUserByLogin(String login){
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.findByLogin(login);
        }
    }

    @Override
    public void refileBalance(User user, BigDecimal addBalance) {
        try (UserDao dao = daoFactory.createUserDao()) {
            user.setBalance(user.getBalance().add(addBalance));
            dao.updateBalance(user);
        }
    }

    @Override
    public void blockUser(Long id) {
        try (UserDao dao = daoFactory.createUserDao()) {
            User user = dao.findById(id);
            user.setBlocked(!user.getBlocked());
            dao.updateStatus(user);
        }
    }

    @Override
    public void saveNewUser(User user){
        try (UserDao dao = daoFactory.createUserDao()) {
            dao.create(user);
        }
    }

}