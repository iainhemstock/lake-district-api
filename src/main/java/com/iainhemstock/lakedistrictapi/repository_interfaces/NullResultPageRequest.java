package com.iainhemstock.lakedistrictapi.repository_interfaces;

public class NullResultPageRequest extends ResultPageRequest {
    public NullResultPageRequest() {
        super(0, 1, "");
    }

    @Override
    public boolean isNull() {
        return true;
    }
}