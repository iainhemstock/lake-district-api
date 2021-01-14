package com.iainhemstock.lakedistrictapi.repository_interfaces;

import lombok.Getter;

import java.util.Set;

@Getter
public abstract class ResultPage<T> {
    private final ResultPageMetaData metaData;
    private final int totalItems;
    private final Set<T> items;
    private final ResultPageMetaData prevPageMetaData;
    private final ResultPageMetaData nextPageMetaData;

    public ResultPage(final ResultPageMetaData metaData,
                      final int totalItems,
                      final Set<T> items,
                      final ResultPageMetaData prevPageMetaData,
                      final ResultPageMetaData nextPageMetaData) {
        this.metaData = metaData;
        this.totalItems = totalItems;
        this.items = items;
        this.prevPageMetaData = prevPageMetaData;
        this.nextPageMetaData = nextPageMetaData;
    }

    public int getOffset() {
        return this.metaData.getOffset();
    }

    public int getLimit() {
        return this.metaData.getLimit();
    }

    public boolean hasPrevPage() {
        return !this.prevPageMetaData.isEmpty();
    }

    public boolean hasNextPage() {
        return !this.nextPageMetaData.isEmpty();
    }
}
