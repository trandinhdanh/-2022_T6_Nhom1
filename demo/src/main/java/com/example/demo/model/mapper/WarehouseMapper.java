package com.example.demo.model.mapper;

import com.example.demo.model.dto.WarehouseDTO;
import com.example.demo.model.entity.Warehouse;

import java.util.List;

public interface WarehouseMapper {

    WarehouseDTO toDTO(Warehouse warehouse);

    List<WarehouseDTO> toListDTO(List<Warehouse> warehouses);

}
