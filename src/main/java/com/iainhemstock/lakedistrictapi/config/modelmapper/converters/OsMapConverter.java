package com.iainhemstock.lakedistrictapi.config.modelmapper.converters;

import com.iainhemstock.lakedistrictapi.entities.OsMap;
import org.modelmapper.AbstractConverter;

import java.util.Set;
import java.util.stream.Collectors;

public class OsMapConverter extends AbstractConverter<Set<OsMap>, Set<String>> {
    @Override
    protected Set<String> convert(final Set<OsMap> osMaps) {
        return osMaps.stream()
            .map(OsMap::getName)
            .collect(Collectors.toSet());
    }
}
