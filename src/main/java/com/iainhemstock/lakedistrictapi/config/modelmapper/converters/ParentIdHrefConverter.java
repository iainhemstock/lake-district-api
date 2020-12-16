package com.iainhemstock.lakedistrictapi.config.modelmapper.converters;

import com.iainhemstock.lakedistrictapi.entities.ParentFell;
import org.modelmapper.AbstractConverter;

public class ParentHrefConverter extends AbstractConverter<ParentFell, String> {
    private String href;

    public ParentHrefConverter(final String href) {
        this.href = href;
    }

    @Override
    protected String convert(final ParentFell parent) {
        return String.format("%s/fells/%s", href, parent.getOsMapRef());
    }
}
