package com.example.demo.service;

import com.example.demo.model.entity.Warehouse;

import java.util.List;

public interface WarehouseService {

    List<Warehouse> listNew(long dateSK, String description);

    List<Warehouse> findByCityName(String cityName);

}
