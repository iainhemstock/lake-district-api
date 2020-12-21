package com.iainhemstock.lakedistrictapi.domain;

import com.iainhemstock.lakedistrictapi.entities.classifications.BirkettClassfication;
import com.iainhemstock.lakedistrictapi.entities.classifications.MarilynClassification;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class ClassificationsTests {

    @Test
    public void can_perform_some_action_for_each_item() {
        Classifications classifications = new Classifications(Set.of(
            new MarilynClassification(),
            new BirkettClassfication()));

        Set<String> expectedNames = Set.of("Marilyn", "Birkett");
        Set<String> actualNames = new HashSet<>();
        classifications.forEach(classification -> actualNames.add(classification.getName()));
        assertTrue(actualNames.containsAll(expectedNames) && expectedNames.containsAll(actualNames));
    }
}
