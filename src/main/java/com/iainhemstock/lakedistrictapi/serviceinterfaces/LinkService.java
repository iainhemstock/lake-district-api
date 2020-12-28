package com.iainhemstock.lakedistrictapi.serviceinterfaces;

import com.iainhemstock.lakedistrictapi.domain.Link;
import com.iainhemstock.lakedistrictapi.domain.LinkRel;
import com.iainhemstock.lakedistrictapi.domain.Links;
import com.iainhemstock.lakedistrictapi.entities.Fell;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LinkService {
    Link buildForResourceWithIdAndRel(final String resource, final String resourceId, final LinkRel rel);
    Links buildNavLinksForPageAndCollectionType(Page<Fell> page, String collection);
}
