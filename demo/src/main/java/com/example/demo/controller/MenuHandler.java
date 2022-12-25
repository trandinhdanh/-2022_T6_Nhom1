package com.example.demo.controller;

import com.example.demo.model.entity.CityDim;
import com.example.demo.service.CityDimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice(annotations = Controller.class)
public class MenuHandler {

    @Autowired
    private CityDimService cityDimService;

    @ModelAttribute("cityNam")
    public List<CityDim> getCityNam(){
       return cityDimService.findByDescription("Miền Nam");
    }

    @ModelAttribute("cityTrung")
    public List<CityDim> getCityTrung(){
        return cityDimService.findByDescription("Miền Trung");
    }
}
