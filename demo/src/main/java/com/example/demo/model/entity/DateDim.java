package com.example.demo.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "date_dim")
public class DateDim {

    @Id
    @Column(name = "date_sk")
    private long dateSk;

    @Column(name = "full_date")
    private String fullDate;

    @Column(name = "day_since_2005")
    private int daySince2005;

    @Column(name = "month_since_2005")
    private int monthSince2005;

    @Column(name = "day_of_week")
    private String dayOfWeek;

    @Column(name = "calendar_month")
    private String calendarMonth;

    @Column(name = "calendar_year")
    private int calendarYear;

    @Column(name = "calendar_year_month")
    private String calendarYearMonth;

    @Column(name = "day_of_month")
    private int dayOfMonth;

    @Column(name = "day_of_year")
    private int dayOfYear;

    @Column(name = "week_of_year_sunday")
    private int weekOfYearSunday;

    @Column(name = "year_week_sunday")
    private String yearWeekSunday;

    @Column(name = "week_sunday_start")
    private String weekSundayStart;

    @Column(name = "week_of_year_monday")
    private int weekOfYearMonday;

    @Column(name = "year_week_monday")
    private String yearWeekMonday;

    @Column(name = "week_monday_start")
    private String weekMondayStart;

    @Column(name = "holiday")
    private String holiday;

    @Column(name = "day_type")
    private String dayType;

}
