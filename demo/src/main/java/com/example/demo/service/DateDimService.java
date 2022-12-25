package com.example.demo.service;

import com.example.demo.model.entity.DateDim;

import java.util.List;

public interface DateDimService {

  List<DateDim> list();

  DateDim getDateDimByFullDate(String date);

  DateDim findById(long id);

}
