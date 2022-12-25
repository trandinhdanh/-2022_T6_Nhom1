package com.example.demo.service.impl;

import com.example.demo.model.entity.Warehouse;
import com.example.demo.repository.WarehouseRepository;
import com.example.demo.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Override
    public List<Warehouse> listNew(long dateSK, String description) {
        return warehouseRepository.listNew(dateSK, description);
    }

    @Override
    public List<Warehouse> findByCityName(String cityName) {
        return warehouseRepository.findByCityName(cityName);
    }
}
