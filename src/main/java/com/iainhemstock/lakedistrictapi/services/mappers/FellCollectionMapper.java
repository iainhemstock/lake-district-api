package com.iainhemstock.lakedistrictapi.services.mappers;

import com.iainhemstock.lakedistrictapi.config.ApiProperties;
import com.iainhemstock.lakedistrictapi.dtos.FellCollectionDto;
import com.iainhemstock.lakedistrictapi.dtos.FellDto;
import com.iainhemstock.lakedistrictapi.entities.Fell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class FellCollectionMapper {

    @Autowired private ApiProperties apiProperties;
    private Pageable pageable;
    private FellCollectionDto dto;

    public FellCollectionDto map(final Page<Fell> page) {
        pageable = page.getPageable();
        dto = new FellCollectionDto();

        if (currentPageIsNotFirstPage()) {
            mapFirstPageLink(page);
            mapPreviousPageLink(page);
        }

        dto.links.self = String.format("%s/fells?page=%d&size=%d",
            apiProperties.getBaseUrl(),
            pageable.getPageNumber(),
            page.getNumberOfElements());

        if (currentPageIsNotLastPage(page)) {
            mapNextPageLink(page);
            mapLastPageLink(page);
        }

        dto.current_page = String.valueOf(pageable.getOffset() + 1);

        dto.total_pages = String.valueOf(page.getTotalPages());

        dto.resources.size = String.valueOf(page.getTotalElements());

        for (Fell fell : page.toList()) {
            FellDto fellDto = new FellDto();
            fellDto.setName(fell.getName());
            fellDto.setRegion(fell.getRegion().getName());
            fellDto.setUrl(String.format("%s/fells/%s",
                apiProperties.getBaseUrl(),
                fell.getOsMapRef()));
            dto.resources.data.add(fellDto);
        }

        return dto;

    }

    private void mapLastPageLink(final Page<Fell> page) {
        dto.links.last = String.format("%s/fells?page=%d&size=%d",
            apiProperties.getBaseUrl(),
            page.getTotalPages() - 1,
            page.getNumberOfElements());
    }

    private void mapNextPageLink(final Page<Fell> page) {
        dto.links.next = String.format("%s/fells?page=%d&size=%d",
            apiProperties.getBaseUrl(),
            pageable.next().getPageNumber(),
            page.getNumberOfElements());
    }

    private void mapPreviousPageLink(final Page<Fell> page) {
        dto.links.prev = String.format("%s/fells?page=%d&size=%d",
            apiProperties.getBaseUrl(),
            pageable.previousOrFirst().getPageNumber(),
            page.getNumberOfElements());
    }

    private void mapFirstPageLink(final Page<Fell> page) {
        dto.links.first = String.format("%s/fells?page=%d&size=%d",
            apiProperties.getBaseUrl(),
            pageable.first().getPageNumber(),
            page.getNumberOfElements());
    }

    private boolean currentPageIsNotLastPage(final Page<Fell> page) {
        return pageable.getPageNumber() != page.getTotalPages() - 1;
    }

    private boolean currentPageIsNotFirstPage() {
        return pageable.getPageNumber() != 0;
    }

}
