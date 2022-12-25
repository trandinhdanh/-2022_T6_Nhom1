package com.example.demo.service.impl;

import com.example.demo.model.entity.CityDim;
import com.example.demo.repository.CityDimRepository;
import com.example.demo.service.CityDimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityDimServiceImpl implements CityDimService {

    @Autowired
    private CityDimRepository cityDimRepository;

    @Override
    public CityDim findById(long id) {
        return cityDimRepository.findById(id).orElse(null);
    }

    @Override
    public List<CityDim> findByDescription(String cityName) {
        return cityDimRepository.findByDescription(cityName);
    }

    @Override
    public CityDim findByCityName(String cityName) {
        return cityDimRepository.findByCityName(cityName);
    }
}
