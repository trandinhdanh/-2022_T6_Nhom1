package com.example.demo.service;

import com.example.demo.model.entity.CityDim;

import java.util.List;

public interface CityDimService {

    CityDim findById(long id);

    List<CityDim> findByDescription(String cityName);

    CityDim findByCityName(String cityName);
}
