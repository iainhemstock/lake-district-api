package com.iainhemstock.lakedistrictapi.services;

import com.iainhemstock.lakedistrictapi.services.Clock;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public final class ClockTest {

    private Clock clock = new TestableClockImpl();

    @Test
    public void return_string_timestamp_formatted_as_dd_MM_yyyy_00_00_00() {
        assertThat(clock.timestamp(), is("25-03-2016 13:54:06"));
    }

    private class TestableClockImpl extends Clock {

        @Override
        protected LocalDateTime getNow() {
            return LocalDateTime.of(
                2016,
                3,
                25,
                13,
                54,
                6);
        }

    }
}
