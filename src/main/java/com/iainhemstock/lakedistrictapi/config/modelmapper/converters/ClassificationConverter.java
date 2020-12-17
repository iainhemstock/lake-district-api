package com.iainhemstock.lakedistrictapi.config.modelmapper.converters;

import com.iainhemstock.lakedistrictapi.entities.Classification;
import org.modelmapper.AbstractConverter;

import java.util.Set;
import java.util.stream.Collectors;

public class ClassificationConverter extends AbstractConverter<Set<Classification>, Set<String>> {
    @Override
    protected Set<String> convert(final Set<Classification> classifications) {
        return classifications.stream()
            .map(Classification::getName)
            .collect(Collectors.toSet());
    }
}
