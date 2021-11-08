package com.lnko.model.service;

import com.lnko.model.entity.Tariff;

import java.util.List;

public interface TariffService {
    List<Tariff> getAllTariffs();
    void saveTariff(Tariff tariff);
    void deleteTariff(Long id);
}
