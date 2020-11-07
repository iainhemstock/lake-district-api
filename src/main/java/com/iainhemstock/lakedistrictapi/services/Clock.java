package com.iainhemstock.lakedistrictapi.services;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class Clock {

    private static final String DATE_FORMAT = "dd-MM-yyyy HH:mm:ss";

    public String timestamp() {
        LocalDateTime now = getNow();
        return DateTimeFormatter.ofPattern(DATE_FORMAT).format(now);
    }

    protected LocalDateTime getNow() {
        return LocalDateTime.now();
    }

}
