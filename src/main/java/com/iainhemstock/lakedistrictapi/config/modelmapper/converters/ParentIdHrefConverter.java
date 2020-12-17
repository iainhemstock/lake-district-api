package com.iainhemstock.lakedistrictapi.config.modelmapper.converters;

import com.iainhemstock.lakedistrictapi.domain.Link;
import com.iainhemstock.lakedistrictapi.entities.ParentFell;
import org.modelmapper.AbstractConverter;

public class ParentIdHrefConverter extends AbstractConverter<ParentFell, Link> {
    private String baseUrl;

    public ParentIdHrefConverter(final String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    protected Link convert(final ParentFell parent) {
        return new Link(String.format("%s/fells/%s", baseUrl, parent.getOsMapRef()));
    }
}
