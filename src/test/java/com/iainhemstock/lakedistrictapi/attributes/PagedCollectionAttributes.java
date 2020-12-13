package com.iainhemstock.lakedistrictapi.attributes;

public enum PagedCollectionAttributes {

    FIRST_HREF(LinksAttributes.FIRST_HREF.value()),
    PREV_HREF(LinksAttributes.PREV_HREF.value()),
    SELF_HREF(LinksAttributes.SELF_HREF.value()),
    NEXT_HREF(LinksAttributes.NEXT_HREF.value()),
    LAST_HREF(LinksAttributes.LAST_HREF.value()),
    OFFSET("$.offset"),
    LIMIT("$.limit"),
    TOTAL_ITEMS("$.total_items");

    private final String value;

    PagedCollectionAttributes(final String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
