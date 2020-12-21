package com.iainhemstock.lakedistrictapi.config.modelmapper.converters;

import com.iainhemstock.lakedistrictapi.domain.Classifications;
import com.iainhemstock.lakedistrictapi.entities.Classification;
import org.modelmapper.AbstractConverter;

import java.util.Set;
import java.util.stream.Collectors;

public class ClassificationConverter extends AbstractConverter<Classifications, Set<String>> {
    @Override
    protected Set<String> convert(final Classifications classifications) {
        return classifications.toSet().stream()
            .map(Classification::getName)
            .collect(Collectors.toSet());
    }
}
