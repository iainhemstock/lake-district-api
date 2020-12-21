package com.iainhemstock.lakedistrictapi.domain;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ClassificationNamesTest {

    @Test
    public void add_one_classification_by_constructor() {
        ClassificationNames classificationNames = new ClassificationNames(new ClassificationName("Marilyn"));
        assertThat(classificationNames.get(0),
            is(new ClassificationName("Marilyn")));
    }

    @Test
    public void add_multiple_classifications_by_constructor() {
        ClassificationNames classificationNames = new ClassificationNames(
            new ClassificationName("Marilyn"),
            new ClassificationName("Wainwright"));

        assertThat(classificationNames.get(0),
            is(new ClassificationName("Marilyn")));
        assertThat(classificationNames.get(1),
            is(new ClassificationName("Wainwright")));
    }

    @Test
    public void add_classification() {
        ClassificationNames classifications = new ClassificationNames();
        classifications.add(new ClassificationName("Wainwright"));
        assertThat(classifications.get(0),
            is(new ClassificationName("Wainwright")));
    }
}
