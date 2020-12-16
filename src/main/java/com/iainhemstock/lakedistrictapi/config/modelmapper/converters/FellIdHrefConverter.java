package com.iainhemstock.lakedistrictapi.config.modelmapper.converters;

import org.modelmapper.AbstractConverter;

public class HrefConverter extends AbstractConverter<String, String> {
    private String href;

    public HrefConverter(final String href) {
        this.href = href;
    }

    @Override
    protected String convert(final String osMapRef) {
        return String.format("%s/fells/%s", href, osMapRef);
    }
}
