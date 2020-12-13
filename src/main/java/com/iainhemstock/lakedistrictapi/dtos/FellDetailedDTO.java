package com.iainhemstock.lakedistrictapi.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.util.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"links", "name", "height", "prominence", "location", "classifications"})
public class FellDetailedDTO {

    private LinksDTO links;
    private HeightDTO height;
    private HeightDTO prominence;
    private LocationDTO location;
    private Set<String> classifications;
    private String name;
}

//public class FellDetailedDTO {
//
//    private LinksDTO links;
//    private HeightDTO height;
//    private HeightDTO prominence;
//    private LocationDTO location;
//    private Set<String> classifications;
//    private String name;
//
//    public FellDetailedDTO() {
//        links = new LinksDTO();
//        height = new HeightDTO();
//        prominence = new HeightDTO();
//        location = new LocationDTO();
//        classifications = new HashSet<>();
//    }
//
//    public void setHeightInFeet(String feet) {
//        height.setFeet(feet);
//    }
//
//    public void setHeightInMeters(String meters) {
//        height.setMeters(meters);
//    }
//
//    public void setProminenceInFeet(String feet) {
//        prominence.setFeet(feet);
//    }
//
//    public void setProminenceInMeters(String meters) {
//        prominence.setMeters(meters);
//    }
//
//    public void setClassifications(Set<String> classifications) {
//        this.classifications = classifications;
//    }
//
//    public Set<String> getClassifications() {
//        return classifications;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setLatitude(String latitude) {
//        location.setLatitude(latitude);
//    }
//
//    public void setLongitude(String longitude) {
//        location.setLongitude(longitude);
//    }
//
//    public void setDms(List<Map<String, String>> dms) {
//        location.setDms(dms);
//    }
//
//    public void setRegion(String region) {
//        location.setRegion(region);
//    }
//
//    public void setOsMapRef(String osMapRef) {
//        location.setOs_map_ref(osMapRef);
//    }
//
//    public void setOsMaps(Set<String> osMaps) {
//        location.setOs_maps(osMaps);
//    }
//
//    @JsonIgnore
//    public Set<String> getOsMaps() {
//        return location.getOs_maps();
//    }
//
//}
