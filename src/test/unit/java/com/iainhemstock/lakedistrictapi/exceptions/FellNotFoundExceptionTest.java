package com.iainhemstock.lakedistrictapi.exceptions;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class FellNotFoundExceptionTest {

    @Test
    public void will_supply_message_containing_the_unfound_fell_id() {
        FellNotFoundException ex = new FellNotFoundException("missing-fell", "", "", "");
        assertThat(ex.getMessage(),
            is(equalTo("Fell was not found for {id=missing-fell}")));
    }
}
