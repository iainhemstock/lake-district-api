package com.iainhemstock.lakedistrictapi.domain;

import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.Landranger89OsMapEntity;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.OL5ExplorerOsMapEntity;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertTrue;

public class OsMapsTests {

    @Test
    public void can_perform_some_action_for_each_item() {
        OsMaps osMapEntities = new OsMaps(Set.of(
            new Landranger89OsMapEntity(),
            new OL5ExplorerOsMapEntity()));

        Set<String> expectedNames = Set.of("OS Landranger 89", "OS Explorer OL5");
        Set<String> actualNames = new HashSet<>();
        osMapEntities.forEach(osMap -> actualNames.add(osMap.getName().toString()));
        assertTrue(actualNames.containsAll(expectedNames) && expectedNames.containsAll(actualNames));
    }

}
