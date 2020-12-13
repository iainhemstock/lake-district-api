package com.iainhemstock.lakedistrictapi.config;

import com.iainhemstock.lakedistrictapi.dtos.FellDetailedDTO;
import com.iainhemstock.lakedistrictapi.entities.Fell;
import org.modelmapper.PropertyMap;

public class FellPropertyMap extends PropertyMap<Fell, FellDetailedDTO> {
    @Override
    protected void configure() {
        map(source.getHeightMeters()).getHeight().setMeters(null);
    }
}
