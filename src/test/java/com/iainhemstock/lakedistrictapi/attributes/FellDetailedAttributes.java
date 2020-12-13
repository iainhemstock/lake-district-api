package com.iainhemstock.lakedistrictapi.attributes;

public enum FellDetailedAttributes {
    NAME("$.name"),
    REGION("$.location.region"),
    LATITUDE("$.location.latitude"),
    LONGITUDE("$.location.longitude"),
    OS_MAP_REF("$.location.os_map_ref"),
    SELF_HREF(LinksAttributes.SELF_HREF.value()),
    PARENT_HREF(LinksAttributes.PARENT_HREF.value()),
    HEIGHT_FEET("$.height.feet"),
    HEIGHT_METERS("$.height.meters"),
    PROMINENCE_FEET("$.prominence.feet"),
    PROMINENCE_METERS("$.prominence.meters"),
    CLASSIFICATIONS("$.classifications"),
    OS_MAPS("$.location.os_maps"),
    DMS_DEGREES("$.location.dms[%d].degrees"),
    DMS_MINUTES("$.location.dms[%d].minutes"),
    DMS_SECONDS("$.location.dms[%d].seconds"),
    DMS_HEMISPHERE("$.location.dms[%d].hemisphere");

    private String value;

    FellDetailedAttributes(final String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public String valueAt(final int i) {
        return String.format(value, i);
    }
}
