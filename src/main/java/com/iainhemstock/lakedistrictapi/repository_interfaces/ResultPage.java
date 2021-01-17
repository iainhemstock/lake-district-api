package com.iainhemstock.lakedistrictapi.repository_interfaces;

import lombok.Getter;

import java.util.Set;

@Getter
public abstract class ResultPage<T> {
    private final ResultPageRequest pageRequest;
    private final int totalItems;
    private final Set<T> items;
    private final ResultPageRequest prevResultPageRequest;
    private final ResultPageRequest nextResultPageRequest;

    public ResultPage(final ResultPageRequest pageRequest,
                      final int totalItems,
                      final Set<T> items,
                      final ResultPageRequest prevResultPageRequest,
                      final ResultPageRequest nextResultPageRequest) {
        this.pageRequest = pageRequest;
        this.totalItems = totalItems;
        this.items = items;
        this.prevResultPageRequest = prevResultPageRequest;
        this.nextResultPageRequest = nextResultPageRequest;
    }

    public int getOffset() {
        return this.pageRequest.getOffset();
    }

    public int getLimit() {
        return this.pageRequest.getLimit();
    }

    public String getSort() {
        return this.pageRequest.getSort();
    }

    public boolean hasPrevPage() {
        return !this.prevResultPageRequest.isNull();
    }

    public boolean hasNextPage() {
        return !this.nextResultPageRequest.isNull();
    }
}
