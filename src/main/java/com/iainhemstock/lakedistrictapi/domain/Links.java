package com.iainhemstock.lakedistrictapi.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class Links {
    private final List<Link> links;

    public Links(final Link... links) {
        this.links = new ArrayList<>();
        Collections.addAll(this.links, links);
    }

    public void forEach(final Consumer<? super Link> consumer) {
        for (Link link : this.links) {
            consumer.accept(link);
        }
    }
}
