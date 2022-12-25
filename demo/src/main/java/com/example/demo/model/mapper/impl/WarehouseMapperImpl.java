package com.example.demo.model.mapper.impl;

import com.example.demo.model.dto.WarehouseDTO;
import com.example.demo.model.entity.CityDim;
import com.example.demo.model.entity.Warehouse;
import com.example.demo.model.mapper.WarehouseMapper;
import com.example.demo.service.CityDimService;
import com.example.demo.service.DateDimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WarehouseMapperImpl implements WarehouseMapper {

    @Autowired
    private DateDimService dateDimService;

    @Autowired
    private CityDimService cityDimService;

    @Override
    public WarehouseDTO toDTO(Warehouse warehouse) {
        if (warehouse == null) {
            return null;
        }

        WarehouseDTO warehouseDTO = new WarehouseDTO();

        warehouseDTO.setId(warehouse.getSk());
        warehouseDTO.setWebsite(warehouse.getWebsite());
        warehouseDTO.setDate(warehouse.getDate());
        warehouseDTO.setCity(warehouse.getCity());
        warehouseDTO.setType(warehouse.getType());
        warehouseDTO.setGiaiDb(warehouse.getGiaiDb());
        warehouseDTO.setGiai1(warehouse.getGiai1());
        warehouseDTO.setGiai2(warehouse.getGiai2().split(" "));
        warehouseDTO.setGiai3(warehouse.getGiai3().split(" "));
        warehouseDTO.setGiai4(warehouse.getGiai4().split(" "));
        warehouseDTO.setGiai5(warehouse.getGiai5().split(" "));
        warehouseDTO.setGiai6(warehouse.getGiai6().split(" "));
        warehouseDTO.setGiai7(warehouse.getGiai7().split(" "));

        warehouseDTO.setValid(warehouse.isValid());

        warehouseDTO.setCityName(cityDimService.findById(warehouse.getCity()).getCityName());
        warehouseDTO.setFullDate(dateDimService.findById(warehouse.getDate()).getFullDate());
        warehouseDTO.setDateOfWeek(dateDimService.findById(warehouse.getDate()).getDayOfWeek());

        if (Integer.parseInt(warehouse.getGiai8()) != 0 || warehouseDTO.getCityName().equals("Miền Bắc")) {
            warehouseDTO.setGiai8(warehouse.getGiai8());
        }else{
            warehouseDTO.setGiai8("");
        }
        return warehouseDTO;
    }

    @Override
    public List<WarehouseDTO> toListDTO(List<Warehouse> warehouses) {

        if (warehouses == null){
            return null;
        }

        List<WarehouseDTO> list = new ArrayList<>(warehouses.size());
        for (Warehouse warehouse : warehouses) {
            list.add(toDTO(warehouse));
        }

        return list;
    }
}
