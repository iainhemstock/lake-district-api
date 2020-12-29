package com.iainhemstock.lakedistrictapi.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.serialization.LinksSerializer;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

@ToString
@EqualsAndHashCode
@JsonSerialize(using = LinksSerializer.class)
public class Links {
    private final List<Link> links;

    public Links(final Link... links) {
        this.links = new ArrayList<>();
        Collections.addAll(this.links, links);
    }

    public static Links empty() {
        return new Links();
    }

    public void forEach(final Consumer<? super Link> consumer) {
        for (Link link : this.links) {
            consumer.accept(link);
        }
    }

    public void add(final Link link) {
        for (Link existingLink : this.links) {
            if (existingLink.getRel().equals(link.getRel())) {
                this.links.remove(existingLink);
                break;
            }
        }
        this.links.add(link);
    }
}
