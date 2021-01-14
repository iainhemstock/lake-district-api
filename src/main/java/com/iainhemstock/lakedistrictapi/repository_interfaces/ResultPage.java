package com.iainhemstock.lakedistrictapi.repository_interfaces;

import lombok.Getter;

import java.util.Set;

@Getter
public abstract class ResultPage<T> {
    private final ResultPageMetaData metaData;
    private final int totalItems;
    private final Set<T> items;
    @Getter final private Boolean hasPreviousPage;
    private final ResultPageMetaData prevPageMetaData;
    @Getter final private Boolean hasNextPage;

    public ResultPage(final ResultPageMetaData metaData,
                      final int totalItems,
                      final Set<T> items,
                      final boolean hasPreviousPage,
                      final ResultPageMetaData prevPageMetaData,
                      final boolean hasNextPage) {
        this.metaData = metaData;
        this.totalItems = totalItems;
        this.items = items;
        this.hasPreviousPage = hasPreviousPage;
        this.prevPageMetaData = prevPageMetaData;
        this.hasNextPage = hasNextPage;
    }

    public int getOffset() {
        return this.metaData.getOffset();
    }

    public int getLimit() {
        return this.metaData.getLimit();
    }
}
