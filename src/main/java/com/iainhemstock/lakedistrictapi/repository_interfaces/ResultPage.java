package com.iainhemstock.lakedistrictapi.repository_interfaces;

import lombok.Getter;

import java.util.Set;

@Getter
public abstract class ResultPage<T> {
    private final ResultPageMetaData metaData;
    private final int totalItems;
    private final Set<T> items;
    @Getter private Boolean hasPreviousPage;

    public ResultPage(final ResultPageMetaData metaData, final int totalItems, final Set<T> items, final boolean hasPreviousPage) {
        this.metaData = metaData;
        this.totalItems = totalItems;
        this.items = items;
        this.hasPreviousPage = hasPreviousPage;
    }

    public int getOffset() {
        return this.metaData.getOffset();
    }

    public int getLimit() {
        return this.metaData.getLimit();
    }
}
