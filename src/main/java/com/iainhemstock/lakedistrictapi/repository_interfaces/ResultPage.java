package com.iainhemstock.lakedistrictapi.repository_interfaces;

import java.util.Set;

public abstract class ResultPage<T> {
    private final ResultPageMetaData metaData;
    private final int totalItems;
    private final Set<T> items;

    public ResultPage(final ResultPageMetaData metaData, final int totalItems, final Set<T> items) {
        this.metaData = metaData;
        this.totalItems = totalItems;
        this.items = items;
    }

    public Set<T> getItems() {
        return this.items;
    }

    public int getTotalItems() {
        return this.totalItems;
    }

    public int getOffset() {
        return this.metaData.getOffset();
    }

    public int getLimit() {
        return this.metaData.getLimit();
    }
}
