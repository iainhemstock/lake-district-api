package com.iainhemstock.lakedistrictapi.services.mappers;

import com.iainhemstock.lakedistrictapi.config.ApiProperties;
import com.iainhemstock.lakedistrictapi.domain.LinkRel;
import com.iainhemstock.lakedistrictapi.dtos.SummarisedFellDTO;
import com.iainhemstock.lakedistrictapi.domain.Link;
import com.iainhemstock.lakedistrictapi.dtos.PagedCollectionDTO;
import com.iainhemstock.lakedistrictapi.entities.FellEntity;
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
    private PagedCollectionDTO<SummarisedFellDTO> pagedCollection;

    public PagedCollectionDTO<SummarisedFellDTO> map(final Page<FellEntity> page) {
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

    private void mapLimit(final Page<FellEntity> page) {
        pagedCollection.setLimit(String.valueOf(page.getNumberOfElements()));
    }

    private void mapTotalItems(final Page<FellEntity> page) {
        pagedCollection.setTotal_items(String.valueOf(page.getTotalElements()));
    }

    private void mapSimpleFells(final Page<FellEntity> page) {
        for (FellEntity fell : page.toList()) {
            SummarisedFellDTO summarisedFellDTO = new SummarisedFellDTO();
            summarisedFellDTO.setName(fell.getName());
            summarisedFellDTO.setRegion(fell.getRegion().getName());
            summarisedFellDTO.getLinks().setSelf(new Link(LinkRel.SELF, (String.format("%s/fells/%s",
                apiProperties.getBaseUrl(),
                fell.getOsMapRef()))));
            pagedCollection.getItems().add(summarisedFellDTO);
        }
    }

    private void mapNavigationLinks(final Page<FellEntity> page) {
        Map<String, Link> links = new HashMap<>();

        if (currentPageIsNotFirstPage(page.getPageable())) {
            mapFirstPageLink(page, links);
            mapPreviousPageLink(page, links);
        }

        pagedCollection.getLinks().setSelf(new Link(LinkRel.PARENT, (String.format("%s/fells?offset=%d&limit=%d",
            apiProperties.getBaseUrl(),
            page.getPageable().getPageNumber(),
            page.getNumberOfElements()))));

        if (currentPageIsNotLastPage(page)) {
            mapNextPageLink(page, links);
            mapLastPageLink(page, links);
        }
    }

    private void mapFirstPageLink(final Page<FellEntity> page, final Map<String, Link> links) {
        pagedCollection.getLinks().setFirst(new Link(LinkRel.FIRST, (String.format("%s/fells?offset=%d&limit=%d",
            apiProperties.getBaseUrl(),
            page.getPageable().first().getPageNumber(),
            page.getNumberOfElements()))));
    }

    private void mapPreviousPageLink(final Page<FellEntity> page, final Map<String, Link> links) {
        pagedCollection.getLinks().setPrev(new Link(LinkRel.PREV, (String.format("%s/fells?offset=%d&limit=%d",
            apiProperties.getBaseUrl(),
            page.getPageable().previousOrFirst().getPageNumber(),
            page.getNumberOfElements()))));
    }

    private void mapNextPageLink(final Page<FellEntity> page, final Map<String, Link> links) {
        pagedCollection.getLinks().setNext(new Link(LinkRel.NEXT, (String.format("%s/fells?offset=%d&limit=%d",
            apiProperties.getBaseUrl(),
            page.getPageable().next().getPageNumber(),
            page.getNumberOfElements()))));
    }

    private void mapLastPageLink(final Page<FellEntity> page, final Map<String, Link> links) {
        pagedCollection.getLinks().setLast(new Link(LinkRel.LAST, (String.format("%s/fells?offset=%d&limit=%d",
            apiProperties.getBaseUrl(),
            page.getTotalPages() - 1,
            page.getNumberOfElements()))));
    }

    private boolean currentPageIsNotLastPage(final Page<FellEntity> page) {
        return page.getPageable().getPageNumber() != page.getTotalPages() - 1;
    }

    private boolean currentPageIsNotFirstPage(final Pageable pageable) {
        return pageable.getPageNumber() != 0;
    }

}
