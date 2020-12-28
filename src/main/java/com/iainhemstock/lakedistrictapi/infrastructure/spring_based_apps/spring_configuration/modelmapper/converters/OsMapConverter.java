package com.iainhemstock.lakedistrictapi.config.modelmapper.converters;

import com.iainhemstock.lakedistrictapi.domain.OsMaps;
import org.modelmapper.AbstractConverter;

import java.util.HashSet;
import java.util.Set;

public class OsMapConverter extends AbstractConverter<OsMaps, Set<String>> {
    @Override
    protected Set<String> convert(final OsMaps osMaps) {
        Set<String> osMapNames = new HashSet<>();
        osMaps.forEach(osMap -> osMapNames.add(osMap.getOsMapName().toString()));
        return osMapNames;
    }
}
