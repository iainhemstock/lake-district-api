package com.iainhemstock.lakedistrictapi.domain;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class OsMapNamesTests {

    @Test
    public void add_one_simple_os_map_by_constructor() {
        OsMapNames osMaps = new OsMapNames(new OsMapName("OS Explorer OL5"));
        assertThat(osMaps.get(0),
            is(new OsMapName("OS Explorer OL5")));
    }

    @Test
    public void add_multiple_simple_os_maps_by_constructor() {
        OsMapNames osMaps = new OsMapNames(
            new OsMapName("OS Explorer OL5"),
            new OsMapName("OS Explorer OL6"));

        assertThat(osMaps.get(0),
            is(new OsMapName("OS Explorer OL5")));
        assertThat(osMaps.get(1),
            is(new OsMapName("OS Explorer OL6")));
    }

    @Test
    public void add_simple_os_map() {
        OsMapNames osMaps = new OsMapNames();
        osMaps.add(new OsMapName("OS Explorer OL6"));
        assertThat(osMaps.get(0),
            is(new OsMapName("OS Explorer OL6")));
    }

}
