package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.repository;

import com.iainhemstock.lakedistrictapi.repository_interfaces.RepoPage;
import com.iainhemstock.lakedistrictapi.repository_interfaces.RepoPageMetaData;
import lombok.ToString;
import org.springframework.data.domain.Page;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@ToString
public class SpringPageRepoPage<T> extends RepoPage<T> {

    public static <T> RepoPage<T> empty() {
        return new SpringPageRepoPage<>(Page.empty());
    }

    public static <T> RepoPage<T> from(final Page<T> page) {
        return new SpringPageRepoPage<>(page);
    }

    private SpringPageRepoPage(final Page<T> itemPage) {
        super(new RepoPageMetaData((int) itemPage.getPageable().getOffset(), itemPage.getPageable().getPageSize()),
            (int) itemPage.getTotalElements(),
            itemPage.stream().collect(Collectors.toSet()));
    }
}
