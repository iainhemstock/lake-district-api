package com.iainhemstock.lakedistrictapi.pageobjects;

public class FellsCollectionPageObject {

    public static String firstPageLink() {
        return "$.links.first";
    }

    public static String prevPageLink() {
        return "$.links.prev";
    }

    public static String selfPageLink() {
        return "$.links.self";
    }

    public static String nextPageLink() {
        return "$.links.next";
    }

    public static String lastPageLink() {
        return "$.links.last";
    }

    public static String currentPageNumber() {
        return "$.current_page";
    }

    public static String totalPages() {
        return "$.total_pages";
    }

    public static String totalResources() {
        return "$.resources.size";
    }

    public static String resources() {
        return "$.resources.data";
    }

    public static String fellNameAt(final int index) {
        return String.format("$.resources.data[%d].name", index);
    }

    public static String fellRegionAt(final int index) {
        return String.format("$.resources.data[%d].region", index);
    }

    public static String fellSelfLinkAt(final int index) {
        return String.format("$.resources.data[%d].links.self", index);
    }

}
