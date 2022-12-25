package com.example.demo.utils;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DateUtils {

    private String pattern = "yyyy-MM-dd";

    public static Date convertStringToDate(String value, String pattern) {
        try {
            if (value != null) {
                return new SimpleDateFormat(pattern).parse(value);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String convertDateToString(Date value, String pattern) {
        return new SimpleDateFormat(pattern).format(value);
    }

}
