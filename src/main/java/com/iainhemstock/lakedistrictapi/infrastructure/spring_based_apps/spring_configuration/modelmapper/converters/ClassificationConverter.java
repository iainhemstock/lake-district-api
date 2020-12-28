package com.iainhemstock.lakedistrictapi.config.modelmapper.converters;

import com.iainhemstock.lakedistrictapi.domain.Classifications;
import org.modelmapper.AbstractConverter;

import java.util.HashSet;
import java.util.Set;

public class ClassificationConverter extends AbstractConverter<Classifications, Set<String>> {
    @Override
    protected Set<String> convert(final Classifications classifications) {
        Set<String> classificationNames = new HashSet<>();
        classifications.forEach(classification -> classificationNames.add(classification.getClassificationName().toString()));
        return classificationNames;
    }
}
