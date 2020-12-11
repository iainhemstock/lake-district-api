package com.iainhemstock.lakedistrictapi.pageobjects;

public enum LinksAttributes {
    FIRST_HREF("$.links.first.href"),
    PREV_HREF("$.links.prev.href"),
    SELF_HREF("$.links.self.href"),
    NEXT_HREF("$.links.next.href"),
    LAST_HREF("$.links.last.href"),
    PARENT_HREF("$.links.parent.href");

    private String value;

    LinksAttributes(final String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
