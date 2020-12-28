package com.iainhemstock.lakedistrictapi.application_interfaces;

import com.iainhemstock.lakedistrictapi.domain.Link;
import com.iainhemstock.lakedistrictapi.domain.LinkRel;
import com.iainhemstock.lakedistrictapi.domain.Links;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.Fell;
import org.springframework.data.domain.Page;

public interface LinkService {
    Link buildForResourceWithIdAndRel(final String resource, final String resourceId, final LinkRel rel);
    Links buildNavLinksForPageAndCollectionType(Page<Fell> page, String collection);
}
