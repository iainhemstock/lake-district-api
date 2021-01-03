package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.repository;

import com.iainhemstock.lakedistrictapi.repository_interfaces.RepoPage;
import lombok.ToString;
import org.springframework.data.domain.Page;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ToString
public class SpringPageRepoPage<T> extends RepoPage<T> {
    private Page<T> itemPage;

    public static <T> RepoPage<T> empty() {
        SpringPageRepoPage<T> repoResult = new SpringPageRepoPage<>();
        repoResult.itemPage = Page.empty();
        return repoResult;
    }

    public static <T> RepoPage<T> from(final Page<T> page) {
        SpringPageRepoPage<T> repoResult = new SpringPageRepoPage<>();
        repoResult.itemPage = page;
        return repoResult;
    }

    @Override
    public Set<T> getItems() {
        return Collections.unmodifiableSet(new HashSet<>(this.itemPage.getContent()));
    }

    @Override
    public int getTotalItemsAvailable() {
        return (int) this.itemPage.getTotalElements();
    }

    @Override
    public int getOffset() {
        return (int) this.itemPage.getPageable().getOffset();
    }

    @Override
    public int getLimit() {
        return this.itemPage.getPageable().getPageSize();
    }
}
