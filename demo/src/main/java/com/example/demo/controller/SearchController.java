package com.example.demo.controller;

import com.example.demo.model.dto.ReportDTO;
import com.example.demo.model.dto.WarehouseDTO;
import com.example.demo.model.entity.CityDim;
import com.example.demo.model.entity.DateDim;
import com.example.demo.model.mapper.WarehouseMapper;
import com.example.demo.service.CityDimService;
import com.example.demo.service.DateDimService;
import com.example.demo.service.WarehouseService;
import com.example.demo.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private DateDimService dateDimService;

    @Autowired
    private CityDimService cityDimService;

    @Autowired
    private WarehouseService warehouseService;

    @Autowired
    private WarehouseMapper warehouseMapper;

    @Autowired
    private DateUtils dateUtils;

    @GetMapping(value = {"/ket-qua-xo-so"})
    public String getByCity(Model model, @RequestParam long cityId){

        CityDim cityDim = cityDimService.findById(cityId);
        Date date = new Date();
        String dateStr = dateUtils.convertDateToString(date, "yyyy-MM-dd");
        DateDim dateDim = dateDimService.getDateDimByFullDate(dateStr);

        List<WarehouseDTO> listByCity = warehouseMapper.toListDTO(warehouseService.findByCityName(cityDim.getCityName()));

        model.addAttribute("cityName", cityDim.getCityName());
        model.addAttribute("date", dateDim.getFullDate());
        model.addAttribute("list", listByCity);
        return "search";
    }
}
