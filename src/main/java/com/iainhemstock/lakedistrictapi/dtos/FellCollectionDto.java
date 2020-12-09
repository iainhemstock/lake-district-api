package com.iainhemstock.lakedistrictapi.dtos;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.iainhemstock.lakedistrictapi.serializers.FellCollectionDtoSerializer;

import java.util.ArrayList;
import java.util.List;

@JsonSerialize(using = FellCollectionDtoSerializer.class)
public class FellCollectionDto {
    public final Links links = new Links();
    public String current_page;
    public String total_pages;
    public final Resources resources = new Resources();

    public static class Links {
        public String first = "";
        public String prev = "";
        public String self = "";
        public String next = "";
        public String last = "";
    }

    public static class Resources {
        public String size;
        public final List<FellDto> data = new ArrayList<>();
    }
}
