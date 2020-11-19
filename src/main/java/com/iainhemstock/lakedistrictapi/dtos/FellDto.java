package com.iainhemstock.lakedistrictapi.dtos;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embedded;
import java.util.Set;

@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonPropertyOrder({"classifications", "height", "location", "name", "parent_peak", "prominence", "url"})
public class FellDto {

    private Set<String> classifications;

    @Embedded
    private HeightDto height;

    @Embedded
    private LocationDto location;

    private String parentPeakUrl;

    @Embedded
    private ProminenceDto prominence;

    private String name;

    @JsonGetter("parent_peak")
    public String getParentPeakUrl() {
        return parentPeakUrl;
    }

    @EqualsAndHashCode.Include
    private String url;
}
