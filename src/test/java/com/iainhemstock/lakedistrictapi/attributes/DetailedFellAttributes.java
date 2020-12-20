package com.iainhemstock.lakedistrictapi.attributes;

public enum DetailedFellAttributes {
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
    LATITUDE_AS_DMS_DEGREES("$.location.latitude_as_dms.degrees"),
    LATITUDE_AS_DMS_MINUTES("$.location.latitude_as_dms.minutes"),
    LATITUDE_AS_DMS_SECONDS("$.location.latitude_as_dms.seconds"),
    LATITUDE_AS_DMS_HEMISPHERE("$.location.latitude_as_dms.hemisphere"),
    LONGITUDE_AS_DMS_DEGREES("$.location.longitude_as_dms.degrees"),
    LONGITUDE_AS_DMS_MINUTES("$.location.longitude_as_dms.minutes"),
    LONGITUDE_AS_DMS_SECONDS("$.location.longitude_as_dms.seconds"),
    LONGITUDE_AS_DMS_HEMISPHERE("$.location.longitude_as_dms.hemisphere");

    private final String value;

    DetailedFellAttributes(final String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public String valueAt(final int i) {
        return String.format(value, i);
    }
}
