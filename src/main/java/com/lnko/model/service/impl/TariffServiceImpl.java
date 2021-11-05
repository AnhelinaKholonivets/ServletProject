package com.lnko.model.service.impl;

import com.lnko.model.dao.DaoFactory;
import com.lnko.model.dao.TariffDao;
import com.lnko.model.entity.Tariff;
import com.lnko.model.service.TariffService;

import java.util.List;

public class TariffServiceImpl implements TariffService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Tariff> getAllTariffs(){
        try (TariffDao dao = daoFactory.createTariffDao()) {
            return dao.findAll();
        }
    }
}
