package com.iainhemstock.lakedistrictapi.dtos;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.util.*;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonPropertyOrder({"name", "height", "prominence", "location", "parent_peak", "url", "classifications"})
public class FellDto  {

    private Height height;
    private Height prominence;
    private Location location;
    private Set<String> classifications;
    private String parentPeakUrl;
    private String name;
    @EqualsAndHashCode.Include private String url;

    public FellDto() {
        height = new Height();
        prominence = new Height();
        location = new Location();
        classifications = new HashSet<>();
    }

    public void setHeightInFeet(String feet) {
        height.setFeet(feet);
    }

    public void setHeightInMeters(String meters) {
        height.setMeters(meters);
    }

    public void setProminenceInFeet(String feet) {
        prominence.setFeet(feet);
    }

    public void setProminenceInMeters(String meters) {
        prominence.setMeters(meters);
    }

    public void setClassifications(Set<String> classifications) {
        this.classifications = classifications;
    }

    public Set<String> getClassifications() {
        return classifications;
    }

    public void setParentPeak(String parentPeak) {
        this.parentPeakUrl = parentPeak;
    }

    @JsonGetter("parent_peak")
    public String getParentPeakUrl() {
        return parentPeakUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLatitude(String latitude) {
        location.setLatitude(latitude);
    }

    public void setLongitude(String longitude) {
        location.setLongitude(longitude);
    }

    public void setDms(List<Map<String, String>> dms) {
        location.setDms(dms);
    }

    public void setRegion(String region) {
        location.setRegion(region);
    }

    public void setOsMapRef(String osMapRef) {
        location.setOs_map_ref(osMapRef);
    }

    public void setOsMaps(Set<String> osMaps) {
        location.setOs_maps(osMaps);
    }

    @JsonIgnore
    public Set<String> getOsMaps() {
        return location.getOs_maps();
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @Data
    @AllArgsConstructor
    public static class Location {
        private String latitude;
        private String longitude;
        private List<Map<String, String>> dms;
        private String region;
        private String os_map_ref;
        private Set<String> os_maps;

        public Location() {
            dms = new ArrayList<>();
            os_maps = new HashSet<>();
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Height {
        private String feet;
        private String meters;
    }
}
