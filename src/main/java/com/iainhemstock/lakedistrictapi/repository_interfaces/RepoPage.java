package com.iainhemstock.lakedistrictapi.repository_interfaces;

import java.util.Set;

public interface RepoPage<T> {
    Set<T> getItems();
    int getTotalItemsAvailable();
    int getTotalPages();
    int getItemsCount();
    boolean isEmpty();
    boolean hasPrevious();
    boolean hasNext();
    int getPrevOffset();
    int getNextOffset();
    int getOffset();
    int getLimit();
}
