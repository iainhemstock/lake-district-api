package com.iainhemstock.lakedistrictapi.dtos;

import com.fasterxml.jackson.annotation.JsonGetter;
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
public class FellDTO {

    @EqualsAndHashCode.Include private String url;

    private String name;

    private Set<String> classifications;

    private String parentPeakUrl;

    @Embedded private HeightDTO height;

    @Embedded private ProminenceDTO prominence;

    @Embedded private LocationDTO location;

    @JsonGetter("parent_peak")
    public String getParentPeakUrl() {
        return parentPeakUrl;
    }
}
