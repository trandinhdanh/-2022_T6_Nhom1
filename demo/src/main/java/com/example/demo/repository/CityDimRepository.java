package com.example.demo.repository;

import com.example.demo.model.entity.CityDim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityDimRepository extends JpaRepository<CityDim, Long> {

    List<CityDim> findByDescription(String cityName);

    CityDim findByCityName(String cityName);
}