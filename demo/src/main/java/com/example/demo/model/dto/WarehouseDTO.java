package com.example.demo.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WarehouseDTO {

    private long id;
    private long website;
    private long date;
    private long city;
    private String type;
    private String giaiDb;
    private String giai1;
    private String[] giai2;
    private String[] giai3;
    private String[] giai4;
    private String[] giai5;
    private String[] giai6;
    private String[] giai7;
    private String giai8;
    private boolean isValid;

    private String cityName;
    private String fullDate;
    private String dateOfWeek;
}
