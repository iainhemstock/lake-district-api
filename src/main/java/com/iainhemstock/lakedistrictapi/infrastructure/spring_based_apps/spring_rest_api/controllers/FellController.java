package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.controllers;

import com.iainhemstock.lakedistrictapi.application_interfaces.ApiClockService;
import com.iainhemstock.lakedistrictapi.application_interfaces.FellService;
import com.iainhemstock.lakedistrictapi.domain.*;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.configuration.ApiProperties;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.domain.Link;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.domain.LinkRel;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.domain.LinkedBasicFell;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.domain.LinkedFell;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.dtos.ErrorDTO;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.repository.LinkedResultPage;
import com.iainhemstock.lakedistrictapi.repository_interfaces.ResultPage;
import com.iainhemstock.lakedistrictapi.repository_interfaces.ResultPageMetaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.EnumMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class FellController {

    @Autowired private FellService fellService;
    @Autowired private ApiProperties apiProperties;
    @Autowired private ApiClockService apiClockService;

    @GetMapping(value = "/fells")
    public ResponseEntity<LinkedResultPage<LinkedBasicFell>> getFells(
        @RequestParam(value = "offset", required = false) Integer offset,
        @RequestParam(value = "limit", required = false) Integer limit,
        @RequestParam(value = "sort", required = false) String sort) {

        if (offset == null) offset = apiProperties.getPageOffset();
        if (limit == null) limit = apiProperties.getPageSize();
        if (sort == null) sort = apiProperties.getPageSort();

        ResultPage<Fell> fellPage = fellService.getFells(offset, limit, sort);

        LinkedResultPage<LinkedBasicFell> linkedResultPage = new LinkedResultPage<>(
            fellPage.getItems().stream()
                .map(this::mapToLinkedBasicFell)
                .collect(Collectors.toCollection(LinkedHashSet::new)),
            ResultPageMetaData.of(offset, limit),
            fellPage.getTotalItems(),
            apiProperties.getBaseUrl() + "/fells");

        return new ResponseEntity<>(linkedResultPage, HttpStatus.OK);
    }

    private LinkedBasicFell mapToLinkedBasicFell(final Fell fell) {
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
                    new Link(LinkRel.SELF, apiProperties.getBaseUrl() + "/fells/" + fell.getOsMapRef().toString())
                ));
            }
        };
    }

    @GetMapping("/fells/{id}")
    public ResponseEntity<LinkedFell> getFell(@PathVariable final String id) {
        Fell fell = fellService.getById(new OsMapRef(id));
        LinkedFell linkedFell = new LinkedFell(fell, apiProperties.getBaseUrl());
        return new ResponseEntity<>(linkedFell, HttpStatus.OK);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgument(IllegalArgumentException ex) {
        ErrorDTO errorDTO = new ErrorDTO(
            HttpStatus.BAD_REQUEST.toString(),
            ex.getMessage(),
            apiClockService.now());
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

}
