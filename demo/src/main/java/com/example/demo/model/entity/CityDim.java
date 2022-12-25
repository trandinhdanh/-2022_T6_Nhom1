package com.example.demo.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "city_dim")
public class CityDim {

    @Id
    @Column(name = "city_sk")
    private long citySk;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "description")
    private String description;

    @Column(name = "code")
    private String code;

}
