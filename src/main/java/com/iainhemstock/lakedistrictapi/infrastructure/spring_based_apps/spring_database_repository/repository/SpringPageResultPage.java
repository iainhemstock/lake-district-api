package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.repository;

import com.iainhemstock.lakedistrictapi.repository_interfaces.NullResultPageRequest;
import com.iainhemstock.lakedistrictapi.repository_interfaces.ResultPage;
import com.iainhemstock.lakedistrictapi.repository_interfaces.ResultPageRequest;
import lombok.ToString;
import org.springframework.data.domain.Page;

import java.util.LinkedHashSet;
import java.util.stream.Collectors;

@ToString
public class SpringPageResultPage<T> extends ResultPage<T> {

    public static <T> ResultPage<T> from(final Page<T> page, final ResultPageRequest pageRequest) {
        return new SpringPageResultPage<>(page, pageRequest);
    }

    private SpringPageResultPage(final Page<T> itemPage, final ResultPageRequest pageRequest) {
        super(ResultPageRequest.of(
                (int) itemPage.getPageable().getOffset(),
                itemPage.getPageable().getPageSize(),
                pageRequest.getSort()),
            (int) itemPage.getTotalElements(),
            itemPage.stream().collect(Collectors.toCollection(LinkedHashSet::new)),
            (itemPage.hasPrevious())
                ? ResultPageRequest.of(
                    (int) itemPage.getPageable().previousOrFirst().getOffset(),
                    itemPage.getPageable().previousOrFirst().getPageSize(),
                    pageRequest.getSort())
                : new NullResultPageRequest(),
            (itemPage.hasNext())
                ? ResultPageRequest.of(
                    (int) itemPage.getPageable().next().getOffset(),
                    itemPage.getPageable().next().getPageSize(),
                    pageRequest.getSort())
                : new NullResultPageRequest());
    }
}
