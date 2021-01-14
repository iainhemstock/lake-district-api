package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.repository;

import com.iainhemstock.lakedistrictapi.repository_interfaces.ResultPage;
import com.iainhemstock.lakedistrictapi.repository_interfaces.ResultPageMetaData;
import lombok.ToString;
import org.springframework.data.domain.Page;

import java.util.LinkedHashSet;
import java.util.stream.Collectors;

@ToString
public class SpringPageResultPage<T> extends ResultPage<T> {

    public static <T> ResultPage<T> from(final Page<T> page) {
        return new SpringPageResultPage<>(page);
    }

    private SpringPageResultPage(final Page<T> itemPage) {
        super(new ResultPageMetaData((int) itemPage.getPageable().getOffset(), itemPage.getPageable().getPageSize()),
            (int) itemPage.getTotalElements(),
            itemPage.stream().collect(Collectors.toCollection(LinkedHashSet::new)),
            (itemPage.hasPrevious())
                ? ResultPageMetaData.of(
                    (int) itemPage.getPageable().previousOrFirst().getOffset(),
                    itemPage.getPageable().previousOrFirst().getPageSize())
                : ResultPageMetaData.empty(),
            (itemPage.hasNext())
                ? ResultPageMetaData.of(
                    (int) itemPage.getPageable().next().getOffset(),
                    itemPage.getPageable().next().getPageSize())
                : ResultPageMetaData.empty());
    }
}
