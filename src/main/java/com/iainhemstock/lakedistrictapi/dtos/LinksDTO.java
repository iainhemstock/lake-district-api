package com.iainhemstock.lakedistrictapi.dtos;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.iainhemstock.lakedistrictapi.serializers.LinksDTOSerializer;

@JsonSerialize(using = LinksDTOSerializer.class)
public class LinksDTO {
    public FirstLink first = new FirstLink();
    public PrevLink prev = new PrevLink();
    public SelfLink self = new SelfLink();
    public NextLink next = new NextLink();
    public LastLink last = new LastLink();
    public ParentLink parent = new ParentLink();

    public static class FirstLink {
        public String href = "";
    }

    public static class PrevLink {
        public String href = "";
    }

    public static class SelfLink {
        public String href = "";
    }

    public static class NextLink {
        public String href = "";
    }

    public static class LastLink {
        public String href = "";
    }

    public static class ParentLink {
        public String href = "";
    }
}
