package com.iainhemstock.lakedistrictapi.repository_interfaces;

import lombok.Getter;

@Getter
public class ResultPageRequest {
    private final int offset;
    private final int limit;
    private final String sort;
    private final boolean isNull;

    protected ResultPageRequest(int offset, int limit, String sort) {
        if (offset < 0) throw new IllegalArgumentException("Offset cannot be negative");
        if (limit < 1) throw new IllegalArgumentException("Limit cannot be negative or zero");
        this.offset = offset;
        this.limit = limit;
        this.sort = sort;
        this.isNull = false;
    }

    public static ResultPageRequest of(final int offset, final int limit, final String sort) {
        return new ResultPageRequest(offset, limit, sort);
    }

}
