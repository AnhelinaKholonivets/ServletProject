package com.lnko.service.impl;

import com.lnko.model.dao.DaoFactory;
import com.lnko.model.dao.TariffDao;
import com.lnko.model.entity.Tariff;
import com.lnko.service.TariffService;

import java.util.List;

public class TariffServiceImpl implements TariffService {
    private final DaoFactory daoFactory;

    public TariffServiceImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public List<Tariff> getAllTariffs(){
        try (TariffDao dao = daoFactory.createTariffDao()) {
            return dao.findAll();
        }
    }

    @Override
    public void saveTariff(Tariff tariff) {
        try (TariffDao dao = daoFactory.createTariffDao()) {
            dao.create(tariff);
        }
    }

    @Override
    public void deleteTariff(Long id) {
        try (TariffDao dao = daoFactory.createTariffDao()) {
            dao.delete(id);
        }
    }
}
