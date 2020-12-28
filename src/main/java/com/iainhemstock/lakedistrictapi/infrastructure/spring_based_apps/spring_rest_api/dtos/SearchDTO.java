package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.dtos;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({ "count", "previous", "next", "results" })
public class SearchDTO {
    // todo: make resultCount a string
    private final int resultCount;
    private final List<FellDTO> results;
    private String previousUri;
    private String nextUri;

    public SearchDTO() {
        this.resultCount = 0;
        this.results = new ArrayList<>();
    }

    public SearchDTO(final List<FellDTO> results) {
        this.results = results;
        this.resultCount = this.results.size();
    }

    @JsonGetter("count")
    public int getResultCount() {
        return resultCount;
    }

    @JsonGetter("results")
    public List<FellDTO> getResults() {
        return results;
    }

    @JsonGetter("previous")
    public String getPreviousUri() {
        return previousUri;
    }

    @JsonGetter("next")
    public String getNextUri() {
        return nextUri;
    }
}
