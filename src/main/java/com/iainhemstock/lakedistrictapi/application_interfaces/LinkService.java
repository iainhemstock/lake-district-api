package com.iainhemstock.lakedistrictapi.application_interfaces;

import com.iainhemstock.lakedistrictapi.domain.*;
import com.iainhemstock.lakedistrictapi.repository_interfaces.RepoPage;

import java.util.Set;

public interface LinkService {
    Link buildForResourceWithIdAndRel(final String resource, final String resourceId, final LinkRel rel);
    Set<Link> buildNavLinksForPageAndCollectionType(RepoPage<SimpleFell> repoPage, String collection);
}
