package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.controllers;

import com.iainhemstock.lakedistrictapi.application_interfaces.*;
import com.iainhemstock.lakedistrictapi.domain.*;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.configuration.ApiProperties;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.domain.Link;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.domain.LinkRel;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.domain.LinkedFell;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.domain.SimpleLinkedFell;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.repository.LinkedRepoPage;
import com.iainhemstock.lakedistrictapi.repository_interfaces.RepoPage;
import com.iainhemstock.lakedistrictapi.repository_interfaces.RepoPageMetaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class FellController {

    @Autowired private FellService fellService;
    @Autowired private LinkService linkService;
    @Autowired private ApiProperties apiProperties;

    @GetMapping(value = "/fells")
    public ResponseEntity<Object>
    getFells(@RequestParam(value = "offset", required = false, defaultValue = "${api.pageable.default-page-offset}") Integer offset,
             @RequestParam(value = "limit", required = false, defaultValue = "${spring.data.web.pageable.default-page-size}") Integer limit) {

        RepoPage<Fell> fellResults = fellService.getFells(offset, limit);

        Set<SimpleLinkedFell> simpleLinkedFells = fellResults.getItems().stream()
            .map(fell -> new SimpleLinkedFell(
                fell,
                Map.of(LinkRel.SELF,
                    new Link(LinkRel.SELF, "http://localhost:8080/api/v1/fells/" + fell.getOsMapRef().toString()))))
            .collect(Collectors.toSet());

        LinkedRepoPage<SimpleLinkedFell> linkedRepoPage = new LinkedRepoPage<>(
            simpleLinkedFells,
            RepoPageMetaData.of(offset, limit),
            fellResults.getTotalItemsAvailable(),
            apiProperties.getBaseUrl() + "/fells");

        return new ResponseEntity<>(linkedRepoPage, HttpStatus.OK);
    }

    @GetMapping("/fells/{id}")
    public ResponseEntity<Object> getFell(@PathVariable final String id) {
        Fell fell = fellService.getById(new OsMapRef(id));
        LinkedFell linkedFell = new LinkedFell(fell, apiProperties.getBaseUrl());
        return new ResponseEntity<>(linkedFell, HttpStatus.OK);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().build();
    }

}
