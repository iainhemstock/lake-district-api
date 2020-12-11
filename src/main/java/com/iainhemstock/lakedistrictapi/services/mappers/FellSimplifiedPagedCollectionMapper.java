package com.iainhemstock.lakedistrictapi.services.mappers;

import com.iainhemstock.lakedistrictapi.config.ApiProperties;
import com.iainhemstock.lakedistrictapi.dtos.FellSimplifiedDTO;
import com.iainhemstock.lakedistrictapi.dtos.PagedCollectionDTO;
import com.iainhemstock.lakedistrictapi.dtos.FellDetailedDTO;
import com.iainhemstock.lakedistrictapi.entities.Fell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

// todo: this class doesn't have unit tests!!
@Component
public class FellSimplifiedPagedCollectionMapper {

    @Autowired private ApiProperties apiProperties;
    private PagedCollectionDTO<FellSimplifiedDTO> dto;

    public PagedCollectionDTO<FellSimplifiedDTO> map(final Page<Fell> page) {
        dto = new PagedCollectionDTO<>();

        mapNavigationLinks(page);
        mapOffset(page.getPageable());
        mapLimit(page);
        mapTotalItems(page);
        mapSimpleFells(page);

        return dto;

    }

    private void mapOffset(final Pageable pageable) {
        dto.offset = String.valueOf(pageable.getOffset());
    }

    private void mapLimit(final Page<Fell> page) {
        dto.limit = String.valueOf(page.getNumberOfElements());
    }

    private void mapTotalItems(final Page<Fell> page) {
        dto.total_items = String.valueOf(page.getTotalElements());
    }

    private void mapSimpleFells(final Page<Fell> page) {
        for (Fell fell : page.toList()) {
            FellSimplifiedDTO fellSimplifiedDTO = new FellSimplifiedDTO();
            fellSimplifiedDTO.name = fell.getName();
            fellSimplifiedDTO.region = fell.getRegion().getName();
            fellSimplifiedDTO.links.self.href = String.format("%s/fells/%s",
                apiProperties.getBaseUrl(),
                fell.getOsMapRef());
            dto.items.add(fellSimplifiedDTO);
        }
    }

    private void mapNavigationLinks(final Page<Fell> page) {
        if (currentPageIsNotFirstPage(page.getPageable())) {
            mapFirstPageLink(page);
            mapPreviousPageLink(page);
        }

        dto.links.self.href = String.format("%s/fells?offset=%d&limit=%d",
            apiProperties.getBaseUrl(),
            page.getPageable().getPageNumber(),
            page.getNumberOfElements());

        if (currentPageIsNotLastPage(page)) {
            mapNextPageLink(page);
            mapLastPageLink(page);
        }
    }

    private void mapFirstPageLink(final Page<Fell> page) {
        dto.links.first.href = String.format("%s/fells?offset=%d&limit=%d",
            apiProperties.getBaseUrl(),
            page.getPageable().first().getPageNumber(),
            page.getNumberOfElements());
    }

    private void mapPreviousPageLink(final Page<Fell> page) {
        dto.links.prev.href = String.format("%s/fells?offset=%d&limit=%d",
            apiProperties.getBaseUrl(),
            page.getPageable().previousOrFirst().getPageNumber(),
            page.getNumberOfElements());
    }

    private void mapNextPageLink(final Page<Fell> page) {
        dto.links.next.href = String.format("%s/fells?offset=%d&limit=%d",
            apiProperties.getBaseUrl(),
            page.getPageable().next().getPageNumber(),
            page.getNumberOfElements());
    }

    private void mapLastPageLink(final Page<Fell> page) {
        dto.links.last.href = String.format("%s/fells?offset=%d&limit=%d",
            apiProperties.getBaseUrl(),
            page.getTotalPages() - 1,
            page.getNumberOfElements());
    }

    private boolean currentPageIsNotLastPage(final Page<Fell> page) {
        return page.getPageable().getPageNumber() != page.getTotalPages() - 1;
    }

    private boolean currentPageIsNotFirstPage(final Pageable pageable) {
        return pageable.getPageNumber() != 0;
    }

}
