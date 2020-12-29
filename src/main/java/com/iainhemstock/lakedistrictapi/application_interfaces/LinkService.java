package com.iainhemstock.lakedistrictapi.application_interfaces;

import com.iainhemstock.lakedistrictapi.domain.*;
import com.iainhemstock.lakedistrictapi.repository_interfaces.RepoPage;

public interface LinkService {
    Link buildForResourceWithIdAndRel(final String resource, final String resourceId, final LinkRel rel);
    Links buildNavLinksForPageAndCollectionType(RepoPage<SimpleFell> repoPage, String collection);
}
