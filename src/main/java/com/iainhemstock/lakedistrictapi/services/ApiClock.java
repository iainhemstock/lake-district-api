package com.iainhemstock.lakedistrictapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class ApiClock {

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private final Clock clock;

    @Autowired
    public ApiClock(final Clock clock) {
        this.clock = clock;
    }

    public String now() {
        LocalDateTime now = LocalDateTime.now(this.clock);
        return DateTimeFormatter.ofPattern(DATE_FORMAT).format(now);
    }

}
