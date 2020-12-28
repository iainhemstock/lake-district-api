package com.iainhemstock.lakedistrictapi.services;

import com.iainhemstock.lakedistrictapi.application_logic.ApiClockServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public final class ApiClockServiceImplTest {

    public static final String NOW_UNFORMATTED = "2016-03-25T13:54:06.00Z";
    public static final String NOW_FORMATTED = "2016-03-25 13:54:06";
    private ApiClockServiceImpl apiClockService;

    @Before
    public void setUp() {
        Clock clock = Clock.fixed(Instant.parse(NOW_UNFORMATTED), ZoneId.of("UTC"));
        apiClockService = new ApiClockServiceImpl(clock);
    }

    @Test
    public void return_string_timestamp_formatted_as_yyyy_MM_dd_00_00_00() {
        assertThat(apiClockService.now(),
            is(NOW_FORMATTED));
    }

}
