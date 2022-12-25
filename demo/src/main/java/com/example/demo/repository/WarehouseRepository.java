package com.example.demo.repository;

import com.example.demo.model.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

    @Query(value = "SELECT * FROM warehouse JOIN city_dim on city = city_sk WHERE warehouse.date = :dateSK AND description = :description ORDER BY warehouse.sk DESC", nativeQuery = true)
    List<Warehouse> listNew(long dateSK, String description);

    @Query(value = "SELECT * FROM warehouse JOIN city_dim on city = city_sk WHERE city_name = :cityName ORDER BY warehouse.sk DESC Limit 5", nativeQuery = true)
    List<Warehouse> findByCityName(String cityName);

}
