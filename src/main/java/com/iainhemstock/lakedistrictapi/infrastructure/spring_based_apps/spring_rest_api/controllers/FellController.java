package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.controllers;

import com.iainhemstock.lakedistrictapi.application_interfaces.ApiClockService;
import com.iainhemstock.lakedistrictapi.application_interfaces.FellService;
import com.iainhemstock.lakedistrictapi.domain.Fell;
import com.iainhemstock.lakedistrictapi.domain.OsMapRef;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.configuration.ApiProperties;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.domain.LinkedFell;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.domain.SimpleLinkedFell;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.dtos.ErrorDTO;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.repository.FellNameAndOsMapRefOnly;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.repository.LinkedRepoPage;
import com.iainhemstock.lakedistrictapi.repository_interfaces.RepoPage;
import com.iainhemstock.lakedistrictapi.repository_interfaces.RepoPageMetaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class FellController {

    @Autowired private FellService fellService;
    @Autowired private ApiProperties apiProperties;
    @Autowired private ApiClockService apiClockService;

    @GetMapping(value = "/fells")
    public ResponseEntity<Object> getFells(
        @RequestParam(value = "offset", required = false) Integer offset,
        @RequestParam(value = "limit", required = false) Integer limit) {

        if (offset == null) offset = apiProperties.getPageOffset();
        if (limit == null) limit = apiProperties.getPageSize();

        RepoPage<FellNameAndOsMapRefOnly> fellPage = fellService.getFells(offset, limit, FellNameAndOsMapRefOnly.class);
        Set<SimpleLinkedFell> simpleLinkedFells = mapFellsToSimpleLinkedFells(fellPage);

        LinkedRepoPage<SimpleLinkedFell> linkedRepoPage = new LinkedRepoPage<>(
            simpleLinkedFells,
            RepoPageMetaData.of(offset, limit),
            fellPage.getTotalItems(),
            apiProperties.getBaseUrl() + "/fells");

        return new ResponseEntity<>(linkedRepoPage, HttpStatus.OK);
    }

    private Set<SimpleLinkedFell> mapFellsToSimpleLinkedFells(final RepoPage<FellNameAndOsMapRefOnly> fellResults) {
        return fellResults.getItems().stream()
            .map(fellNameAndOsMapRefOnly -> new SimpleLinkedFell(fellNameAndOsMapRefOnly, apiProperties.getBaseUrl()))
            .collect(Collectors.toSet());
    }

    @GetMapping("/fells/{id}")
    public ResponseEntity<Object> getFell(@PathVariable final String id) {
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
