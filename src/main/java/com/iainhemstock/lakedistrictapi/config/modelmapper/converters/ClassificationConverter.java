package com.iainhemstock.lakedistrictapi.config.modelmapper.converters;

import com.iainhemstock.lakedistrictapi.domain.Classifications;
import com.iainhemstock.lakedistrictapi.entities.Classification;
import org.modelmapper.AbstractConverter;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ClassificationConverter extends AbstractConverter<Classifications, Set<String>> {
    @Override
    protected Set<String> convert(final Classifications classifications) {
        Set<String> classificationNames = new HashSet<>();
        classifications.forEach(classification -> classificationNames.add(classification.getName()));
        return classificationNames;
    }
}
