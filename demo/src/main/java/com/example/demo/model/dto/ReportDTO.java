package com.example.demo.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReportDTO {

    private List<WarehouseDTO> list;
    private String fullDate;
    private String region;
    private String percent;

    private String dateOfWeek;

    public ReportDTO(List<WarehouseDTO> list, String dateOfWeek, String fullDate, String region, String percent) {
        this.list = list;
        this.dateOfWeek = dateOfWeek;
        this.fullDate = fullDate;
        this.region = region;
        this.percent = percent;
    }
}
