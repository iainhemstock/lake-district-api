package com.iainhemstock.lakedistrictapi.repository_interfaces;

import java.util.Set;

public abstract class RepoPage<T> {
    private final RepoPageMetaData metaData;
    private final int totalItems;
    private final Set<T> items;

    public RepoPage(final RepoPageMetaData metaData, final int totalItems, final Set<T> items) {
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
