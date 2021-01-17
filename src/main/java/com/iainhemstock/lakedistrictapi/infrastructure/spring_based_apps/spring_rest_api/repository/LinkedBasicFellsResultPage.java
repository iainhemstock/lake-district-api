package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.repository;

import com.iainhemstock.lakedistrictapi.domain.Feet;
import com.iainhemstock.lakedistrictapi.domain.Fell;
import com.iainhemstock.lakedistrictapi.domain.FellName;
import com.iainhemstock.lakedistrictapi.domain.Meters;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.domain.Link;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.domain.LinkRel;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.domain.LinkedBasicFell;
import com.iainhemstock.lakedistrictapi.repository_interfaces.ResultPage;
import com.iainhemstock.lakedistrictapi.repository_interfaces.ResultPageRequest;

import java.util.EnumMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.stream.Collectors;

public class LinkedBasicFellsResultPage extends LinkedResultPage<LinkedBasicFell> {

    public LinkedBasicFellsResultPage(final ResultPage<Fell> fellPage, final String baseUrl) {
        super(
            fellPage.getItems().stream()
                .map(fell -> mapToLinkedBasicFell(fell, baseUrl))
                .collect(Collectors.toCollection(LinkedHashSet::new)),
            ResultPageRequest.of(fellPage.getOffset(), fellPage.getLimit(), fellPage.getSort()),
            fellPage.getTotalItems(),
            baseUrl + "/fells",
            fellPage.getPrevResultPageRequest(),
            fellPage.getNextResultPageRequest());
    }

    private static LinkedBasicFell mapToLinkedBasicFell(final Fell fell, final String baseUrl) {
        return new LinkedBasicFell() {
            @Override
            public FellName getName() {
                return fell.getName();
            }

            @Override
            public Meters getHeightMeters() {
                return fell.getHeightMeters();
            }

            @Override
            public Feet getHeightFeet() {
                return fell.getHeightFeet();
            }

            @Override
            public EnumMap<LinkRel, Link> getLinks() {
                return new EnumMap<>(Map.of(
                    LinkRel.SELF,
                    new Link(LinkRel.SELF, baseUrl + "/fells/" + fell.getOsMapRef().toString())
                ));
            }
        };
    }
}
