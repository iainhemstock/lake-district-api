package com.iainhemstock.lakedistrictapi.attributes;

public enum DetailedFellAttributes {
    NAME("$.item.name"),
    REGION("$.item.location.region"),
    LATITUDE("$.item.location.latitude"),
    LONGITUDE("$.item.location.longitude"),
    OS_MAP_REF("$.item.location.os_map_ref"),
    SELF_HREF(LinksAttributes.SELF_HREF.value()),
    PARENT_HREF(LinksAttributes.PARENT_HREF.value()),
    HEIGHT_FEET("$.item.height.feet"),
    HEIGHT_METERS("$.item.height.meters"),
    PROMINENCE_FEET("$.item.prominence.feet"),
    PROMINENCE_METERS("$.item.prominence.meters"),
    CLASSIFICATIONS("$.item.classifications"),
    OS_MAPS("$.item.location.os_maps"),
    LATITUDE_AS_DMS_DEGREES("$.item.location.latitude_as_dms.degrees"),
    LATITUDE_AS_DMS_MINUTES("$.item.location.latitude_as_dms.minutes"),
    LATITUDE_AS_DMS_SECONDS("$.item.location.latitude_as_dms.seconds"),
    LATITUDE_AS_DMS_HEMISPHERE("$.item.location.latitude_as_dms.hemisphere"),
    LONGITUDE_AS_DMS_DEGREES("$.item.location.longitude_as_dms.degrees"),
    LONGITUDE_AS_DMS_MINUTES("$.item.location.longitude_as_dms.minutes"),
    LONGITUDE_AS_DMS_SECONDS("$.item.location.longitude_as_dms.seconds"),
    LONGITUDE_AS_DMS_HEMISPHERE("$.item.location.longitude_as_dms.hemisphere");

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
