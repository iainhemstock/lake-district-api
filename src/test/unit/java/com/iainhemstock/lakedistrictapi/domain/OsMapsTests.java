package com.iainhemstock.lakedistrictapi.domain;

import com.iainhemstock.lakedistrictapi.entities.osmaps.Landranger89OsMap;
import com.iainhemstock.lakedistrictapi.entities.osmaps.OL5ExplorerOsMap;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertTrue;

public class OsMapsTests {

    @Test
    public void can_perform_some_action_for_each_item() {
        OsMaps osMaps = new OsMaps(Set.of(
            new Landranger89OsMap(),
            new OL5ExplorerOsMap()));

        Set<String> expectedNames = Set.of("OS Landranger 89", "OS Explorer OL5");
        Set<String> actualNames = new HashSet<>();
        osMaps.forEach(osMap -> actualNames.add(osMap.getOsMapName().toString()));
        assertTrue(actualNames.containsAll(expectedNames) && expectedNames.containsAll(actualNames));
    }

}
