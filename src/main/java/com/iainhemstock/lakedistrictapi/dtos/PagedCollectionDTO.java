package com.iainhemstock.lakedistrictapi.dtos;

import java.util.ArrayList;
import java.util.List;

public class PagedCollectionDTO<T> {
    public final LinksDTO links = new LinksDTO();
    public String offset;
    public String limit;
    public String total_items;
    public List<T> items = new ArrayList<>();
}
