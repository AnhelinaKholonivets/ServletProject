package com.lnko.model.dao;

import com.lnko.model.dao.impl.JDBCDaoFactory;

public abstract class DaoFactory {

    public abstract TariffDao createTariffDao();
    public abstract UserDao createUserDao();
    public abstract OrderDao createOrderDao();

    private static final class DaoFactoryHolder {
        static final DaoFactory daoFactory = new JDBCDaoFactory();
    }

    public static DaoFactory getInstance(){
        return DaoFactoryHolder.daoFactory;
    }
}
