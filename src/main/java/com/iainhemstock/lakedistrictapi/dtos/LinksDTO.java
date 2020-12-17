package com.iainhemstock.lakedistrictapi.dtos;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.iainhemstock.lakedistrictapi.domain.Link;
import com.iainhemstock.lakedistrictapi.serializers.LinksDTOSerializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonSerialize(using = LinksDTOSerializer.class)
public class LinksDTO {
    private Link first;
    private Link prev;
    private Link self;
    private Link next;
    private Link last;
    private Link parent;
}

