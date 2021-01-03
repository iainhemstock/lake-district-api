package com.iainhemstock.lakedistrictapi.repository_interfaces;

import java.util.Set;

public abstract class RepoPage<T> {
    abstract public Set<T> getItems();
    abstract public int getTotalItemsAvailable();
    abstract public int getOffset();
    abstract public int getLimit();
}
