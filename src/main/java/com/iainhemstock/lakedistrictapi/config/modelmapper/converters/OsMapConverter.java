package com.iainhemstock.lakedistrictapi.config.modelmapper.converters;

import com.iainhemstock.lakedistrictapi.domain.OsMapName;
import com.iainhemstock.lakedistrictapi.domain.OsMapNames;
import com.iainhemstock.lakedistrictapi.entities.OsMap;
import org.modelmapper.AbstractConverter;

import java.util.Set;
import java.util.stream.Collectors;

public class OsMapConverter extends AbstractConverter<OsMapNames, Set<String>> {
    @Override
    protected Set<String> convert(final OsMapNames osMapNames) {
        return osMapNames.stream()
            .map(OsMapName::toString)
            .collect(Collectors.toSet());
    }
}
