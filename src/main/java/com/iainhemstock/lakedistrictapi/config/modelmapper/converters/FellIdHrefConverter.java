package com.iainhemstock.lakedistrictapi.config.modelmapper.converters;

import com.iainhemstock.lakedistrictapi.domain.Link;
import org.modelmapper.AbstractConverter;

public class FellIdHrefConverter extends AbstractConverter<String, Link> {
    private String baseUrl;

    public FellIdHrefConverter(final String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    protected Link convert(final String osMapRef) {
        return new Link(String.format("%s/fells/%s", baseUrl, osMapRef));
    }
}
