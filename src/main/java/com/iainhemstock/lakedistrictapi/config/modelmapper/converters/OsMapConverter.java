package com.iainhemstock.lakedistrictapi.config.modelmapper.converters;

import com.iainhemstock.lakedistrictapi.domain.OsMaps;
import com.iainhemstock.lakedistrictapi.entities.OsMap;
import org.modelmapper.AbstractConverter;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class OsMapConverter extends AbstractConverter<OsMaps, Set<String>> {
    @Override
    protected Set<String> convert(final OsMaps osMaps) {
        Set<String> osMapNames = new HashSet<>();
        osMaps.forEach(osMap -> osMapNames.add(osMap.getName()));
        return osMapNames;
    }
}
