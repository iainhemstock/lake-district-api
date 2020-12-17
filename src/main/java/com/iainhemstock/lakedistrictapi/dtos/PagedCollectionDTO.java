package com.iainhemstock.lakedistrictapi.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class PagedCollectionDTO<T> {
    private LinksDTO links = new LinksDTO();
    private String offset;
    private String limit;
    private String total_items;
    private List<T> items = new ArrayList<>();
}
