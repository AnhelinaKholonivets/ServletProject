package com.lnko.model.dao.impl;

import com.lnko.controller.util.ConnectionManager;
import com.lnko.model.dao.DaoFactory;
import com.lnko.model.dao.TariffDao;
import com.lnko.model.dao.UserDao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {

    private final DataSource dataSource = ConnectionManager.getDataSource();

//    @Override
//    public TeacherDao createTeacherDao() {
//        return new JDBCTeacherDao(getConnection());
//    }

    @Override
    public TariffDao createTariffDao() {
        return new JDBCTariffDao(getConnection());
    }

    @Override
    public UserDao createUserDao() {
       return new JDBCUserDao(getConnection());
    }

    private Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}