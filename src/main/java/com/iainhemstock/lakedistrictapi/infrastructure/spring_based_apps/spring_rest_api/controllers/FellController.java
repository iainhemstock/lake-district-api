package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.controllers;

import com.iainhemstock.lakedistrictapi.application_interfaces.*;
import com.iainhemstock.lakedistrictapi.domain.*;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.domain.SimpleLinkableFell;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.dtos.ItemDTO;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.dtos.PagedCollectionDTO;
import com.iainhemstock.lakedistrictapi.repository_interfaces.RepoPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class FellController {

    @Autowired private FellService fellService;
    @Autowired private LinkService linkService;

    @GetMapping(value = "/fells")
    public ResponseEntity<Object>
    getFells(@RequestParam(value = "offset", required = false, defaultValue = "${api.pageable.default-page-offset}") Integer offset,
             @RequestParam(value = "limit", required = false, defaultValue = "${spring.data.web.pageable.default-page-size}") Integer limit) {

        RepoPage<Fell> fellResults = fellService.getFells(offset, limit);

        Set<SimpleLinkableFell> simpleLinkableFells = fellResults.getItems().stream()
            .map(fell -> new SimpleLinkableFell(
                fell,
                Map.of(LinkRel.SELF,
                    new Link(LinkRel.SELF, "http://localhost:8080/api/v1/fells/" + fell.getOsMapRef().toString()))))
            .collect(Collectors.toSet());

        Map<LinkRel, Link> navLinks = linkService.buildNavLinksForPageAndCollectionType(fellResults, "fells");

        PagedCollectionDTO<SimpleLinkableFell> pagedCollectionDTO = new PagedCollectionDTO<>(
            navLinks,
            simpleLinkableFells,
            offset,
            limit,
            fellResults.getTotalItemsAvailable());

        return new ResponseEntity<>(pagedCollectionDTO, HttpStatus.OK);
    }

    @GetMapping("/fells/{id}")
    public ResponseEntity<Object> getFell(@PathVariable final String id) {
        Fell fell = fellService.getById(new OsMapRef(id));
        Map<LinkRel, Link> links = Map.of(
            LinkRel.SELF, linkService.buildForResourceWithIdAndRel("fells", fell.getOsMapRef().toString(), LinkRel.SELF),
            LinkRel.PARENT, linkService.buildForResourceWithIdAndRel("fells", fell.getParentOsMapRef().toString(), LinkRel.PARENT));

        return new ResponseEntity<>(new ItemDTO<>(links, fell), HttpStatus.OK);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().build();
    }

}
