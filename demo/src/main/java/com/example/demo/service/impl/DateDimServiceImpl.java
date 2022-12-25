package com.example.demo.service.impl;

import com.example.demo.model.entity.DateDim;
import com.example.demo.repository.DateDimRepository;
import com.example.demo.service.DateDimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DateDimServiceImpl implements DateDimService {

    @Autowired
    private DateDimRepository dateDimRepository;

    @Override
    public List<DateDim> list() {
        return dateDimRepository.findAll();
    }

    @Override
    public DateDim getDateDimByFullDate(String date) {
        return dateDimRepository.getDateDimByFullDate(date);
    }

    @Override
    public DateDim findById(long id) {
        return dateDimRepository.findById(id).orElse(null);
    }
}
