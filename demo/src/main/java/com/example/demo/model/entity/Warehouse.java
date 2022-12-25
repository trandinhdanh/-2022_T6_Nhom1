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
@Table(name = "warehouse")
public class Warehouse {

    @Id
    @Column(name = "sk")
    private long sk;

    @Column(name = "website")
    private long website;

    @Column(name = "date")
    private long date;

    @Column(name = "city")
    private long city;

    @Column(name = "type")
    private String type;

    @Column(name = "giai_db")
    private String giaiDb;

    @Column(name = "giai_1")
    private String giai1;

    @Column(name = "giai_2")
    private String giai2;

    @Column(name = "giai_3")
    private String giai3;

    @Column(name = "giai_4")
    private String giai4;

    @Column(name = "giai_5")
    private String giai5;

    @Column(name = "giai_6")
    private String giai6;

    @Column(name = "giai_7")
    private String giai7;

    @Column(name = "giai_8")
    private String giai8;

    @Column(name = "is_valid")
    private boolean isValid;

}
