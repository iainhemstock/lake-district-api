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
public class FellDto {

    @EqualsAndHashCode.Include
    private String url;

    private String name;

    private Set<String> classifications;

    private String parentPeakUrl;

    @Embedded
    private HeightDto height;

    @Embedded
    private ProminenceDto prominence;

    @Embedded
    private LocationDto location;

    @JsonGetter("parent_peak")
    public String getParentPeakUrl() {
        return parentPeakUrl;
    }
}
