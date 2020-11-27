package com.iainhemstock.lakedistrictapi.dtos;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import javax.persistence.Embedded;
import java.util.Map;
import java.util.Set;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonPropertyOrder({"name", "height", "prominence", "location", "parent_peak", "url", "classifications"})
public class FellDto  {

    @EqualsAndHashCode.Include private String url;
    private Map<String, String> height;
    private Map<String, String> prominence;
    private Map<String, Object> location;
    private Set<String> classifications;
    private String parentPeakUrl;
    private String name;

    @JsonGetter("parent_peak")
    public String getParentPeakUrl() {
        return parentPeakUrl;
    }
}
