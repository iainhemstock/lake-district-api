package com.iainhemstock.lakedistrictapi.domain;

import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.dtos.LinksDTO;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.Fell;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class PagedCollection {
    private Link firstLink;
    private Link prevLink;
    private Link selfLink;
    private Link nextLink;
    private Link lastLink;
    private int offset;
    private int limit;
    private long totalItems;
    private SummarisedFells items;

    public PagedCollection(final int offset, final int limit, final List<Fell> fells, final int totalPages, final long totalElements) {
        this.offset = offset;
        this.limit = limit;
        this.items = new SummarisedFells();
        fells.stream()
            .forEach(fell -> items.add(new SummarisedFell(new FellName(fell.getName().toString()), new Link(LinkRel.SELF, "http://localhost:8080/api/v1/fells/" + fell.getOsMapRef()))));
        firstLink = new Link(LinkRel.FIRST, String.format("http://localhost:8080/api/v1/fells?offset=%d&limit=%d", 0, limit));
        prevLink = new Link(LinkRel.PREV, String.format("http://localhost:8080/api/v1/fells?offset=%d&limit=%d", offset-1, limit));
        selfLink = new Link(LinkRel.SELF, String.format("http://localhost:8080/api/v1/fells?offset=%d&limit=%d", offset, limit));
        nextLink = new Link(LinkRel.NEXT, String.format("http://localhost:8080/api/v1/fells?offset=%d&limit=%d", offset+1, limit));
        lastLink = new Link(LinkRel.LAST, String.format("http://localhost:8080/api/v1/fells?offset=%d&limit=%d", totalPages, limit));
        totalItems = totalElements;
    }

    public PagedCollection(final LinksDTO linksDTO, final Object objectDTO) {
        throw new UnsupportedOperationException();
    }
}
