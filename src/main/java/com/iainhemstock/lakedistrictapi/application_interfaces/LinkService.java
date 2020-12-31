package com.iainhemstock.lakedistrictapi.application_interfaces;

import com.iainhemstock.lakedistrictapi.domain.Fell;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.domain.Link;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.domain.LinkRel;
import com.iainhemstock.lakedistrictapi.repository_interfaces.RepoPage;

import java.util.Map;

public interface LinkService {
    Link buildForResourceWithIdAndRel(final String resource, final String resourceId, final LinkRel rel);
    Map<LinkRel, Link> buildNavLinksForPageAndCollectionType(RepoPage<Fell> repoPage, String collection);
}
