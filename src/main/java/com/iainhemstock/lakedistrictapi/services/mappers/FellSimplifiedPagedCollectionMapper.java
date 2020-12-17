package com.iainhemstock.lakedistrictapi.services.mappers;

import com.iainhemstock.lakedistrictapi.config.ApiProperties;
import com.iainhemstock.lakedistrictapi.dtos.FellSimplifiedDTO;
import com.iainhemstock.lakedistrictapi.domain.Link;
import com.iainhemstock.lakedistrictapi.dtos.PagedCollectionDTO;
import com.iainhemstock.lakedistrictapi.entities.Fell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

// todo: this class doesn't have unit tests!!
@Component
public class FellSimplifiedPagedCollectionMapper {

    @Autowired private ApiProperties apiProperties;
    private PagedCollectionDTO<FellSimplifiedDTO> pagedCollection;

    public PagedCollectionDTO<FellSimplifiedDTO> map(final Page<Fell> page) {
        pagedCollection = new PagedCollectionDTO<>();

        mapNavigationLinks(page);
        mapOffset(page.getPageable());
        mapLimit(page);
        mapTotalItems(page);
        mapSimpleFells(page);

        return pagedCollection;

    }

    private void mapOffset(final Pageable pageable) {
        pagedCollection.setOffset(String.valueOf(pageable.getOffset()));
    }

    private void mapLimit(final Page<Fell> page) {
        pagedCollection.setLimit(String.valueOf(page.getNumberOfElements()));
    }

    private void mapTotalItems(final Page<Fell> page) {
        pagedCollection.setTotal_items(String.valueOf(page.getTotalElements()));
    }

    private void mapSimpleFells(final Page<Fell> page) {
        for (Fell fell : page.toList()) {
            FellSimplifiedDTO fellSimplifiedDTO = new FellSimplifiedDTO();
            fellSimplifiedDTO.setName(fell.getName());
            fellSimplifiedDTO.setRegion(fell.getRegion().getName());
            fellSimplifiedDTO.getLinks().setSelf(new Link((String.format("%s/fells/%s",
                apiProperties.getBaseUrl(),
                fell.getOsMapRef()))));
            pagedCollection.getItems().add(fellSimplifiedDTO);
        }
    }

    private void mapNavigationLinks(final Page<Fell> page) {
        Map<String, Link> links = new HashMap<>();

        if (currentPageIsNotFirstPage(page.getPageable())) {
            mapFirstPageLink(page, links);
            mapPreviousPageLink(page, links);
        }

        pagedCollection.getLinks().setSelf(new Link((String.format("%s/fells?offset=%d&limit=%d",
            apiProperties.getBaseUrl(),
            page.getPageable().getPageNumber(),
            page.getNumberOfElements()))));

        if (currentPageIsNotLastPage(page)) {
            mapNextPageLink(page, links);
            mapLastPageLink(page, links);
        }
    }

    private void mapFirstPageLink(final Page<Fell> page, final Map<String, Link> links) {
        pagedCollection.getLinks().setFirst(new Link((String.format("%s/fells?offset=%d&limit=%d",
            apiProperties.getBaseUrl(),
            page.getPageable().first().getPageNumber(),
            page.getNumberOfElements()))));
    }

    private void mapPreviousPageLink(final Page<Fell> page, final Map<String, Link> links) {
        pagedCollection.getLinks().setPrev(new Link((String.format("%s/fells?offset=%d&limit=%d",
            apiProperties.getBaseUrl(),
            page.getPageable().previousOrFirst().getPageNumber(),
            page.getNumberOfElements()))));
    }

    private void mapNextPageLink(final Page<Fell> page, final Map<String, Link> links) {
        pagedCollection.getLinks().setNext(new Link((String.format("%s/fells?offset=%d&limit=%d",
            apiProperties.getBaseUrl(),
            page.getPageable().next().getPageNumber(),
            page.getNumberOfElements()))));
    }

    private void mapLastPageLink(final Page<Fell> page, final Map<String, Link> links) {
        pagedCollection.getLinks().setLast(new Link((String.format("%s/fells?offset=%d&limit=%d",
            apiProperties.getBaseUrl(),
            page.getTotalPages() - 1,
            page.getNumberOfElements()))));
    }

    private boolean currentPageIsNotLastPage(final Page<Fell> page) {
        return page.getPageable().getPageNumber() != page.getTotalPages() - 1;
    }

    private boolean currentPageIsNotFirstPage(final Pageable pageable) {
        return pageable.getPageNumber() != 0;
    }

}
