package com.iainhemstock.lakedistrictapi.dtos;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.iainhemstock.lakedistrictapi.serializers.LinksDTOSerializer;
import lombok.Getter;
import lombok.Setter;

@JsonSerialize(using = LinksDTOSerializer.class)
@Getter
@Setter
public class LinksDTO {
    private FirstLink first = new FirstLink();
    private PrevLink prev = new PrevLink();
    private SelfLink self = new SelfLink();
    private NextLink next = new NextLink();
    private LastLink last = new LastLink();
    private ParentLink parent = new ParentLink();

    @Getter
    @Setter
    private abstract class AbstractLink {
        private String href = "";
    }

    public class FirstLink extends AbstractLink {}
    public class PrevLink extends AbstractLink {}
    public class SelfLink extends AbstractLink {}
    public class NextLink extends AbstractLink {}
    public class LastLink extends AbstractLink {}
    public class ParentLink extends AbstractLink {}
}
