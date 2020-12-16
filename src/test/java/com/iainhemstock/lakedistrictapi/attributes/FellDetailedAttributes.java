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
    LATITUDE_DMS_DEGREES("$.location.dms_latitude.degrees"),
    LATITUDE_DMS_MINUTES("$.location.dms_latitude.minutes"),
    LATITUDE_DMS_SECONDS("$.location.dms_latitude.seconds"),
    LATITUDE_DMS_HEMISPHERE("$.location.dms_latitude.hemisphere"),
    LONGITUDE_DMS_DEGREES("$.location.dms_longitude.degrees"),
    LONGITUDE_DMS_MINUTES("$.location.dms_longitude.minutes"),
    LONGITUDE_DMS_SECONDS("$.location.dms_longitude.seconds"),
    LONGITUDE_DMS_HEMISPHERE("$.location.dms_longitude.hemisphere");

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
