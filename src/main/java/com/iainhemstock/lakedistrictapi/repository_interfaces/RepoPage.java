package com.iainhemstock.lakedistrictapi.repository_interfaces;

import java.util.Set;

public abstract class RepoPage<T> {
    private Set<T> items;
    private RepoPageMetaData repoPageMetaData;
    private int totalItems;

    abstract public Set<T> getItems();
    abstract public int getTotalItemsAvailable();
    abstract public int getTotalPages();
    abstract public int getItemsCount();
    abstract public boolean isEmpty();
    abstract public boolean hasPrevious();
    abstract public boolean hasNext();
    abstract public int getPrevOffset();
    abstract public int getNextOffset();
    abstract public int getOffset();
    abstract public int getLimit();
}
