package com.lnko.model.dao;

import com.lnko.model.entity.Tariff;

import java.util.List;

public interface TariffDao extends GenericDao<Tariff> {
    List<Tariff> findAllByIds(List<Long> tariffsIds);
}
