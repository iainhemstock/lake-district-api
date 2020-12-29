package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.controllers;

import com.iainhemstock.lakedistrictapi.application_interfaces.*;
import com.iainhemstock.lakedistrictapi.domain.Fell;
import com.iainhemstock.lakedistrictapi.domain.LinkRel;
import com.iainhemstock.lakedistrictapi.domain.Links;
import com.iainhemstock.lakedistrictapi.domain.OsMapRef;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.FellEntity;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.dtos.ItemDTO;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.dtos.LinksDTO;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.dtos.PagedCollectionDTO;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.dtos.SimpleFellsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class FellController {

    @Autowired private FellService fellService;
    @Autowired private FellDTOAssembler fellDTOAssembler;
    @Autowired private LinkService linkService;
    @Autowired private LinksDTOAssembler linksDTOAssembler;
    @Autowired private SimpleFellsDTOAssembler simpleFellsDTOAssembler;

    @GetMapping(value = "/fells")
    public ResponseEntity<Object>
    getFells(@RequestParam(value = "offset", required = false, defaultValue = "${api.pageable.default-page-offset}") Integer offset,
             @RequestParam(value = "limit", required = false, defaultValue = "${spring.data.web.pageable.default-page-size}") Integer limit) {
        Page<FellEntity> fellsPage = fellService.getFells(offset, limit);
        Links links = linkService.buildNavLinksForPageAndCollectionType(fellsPage, "fells");
        SimpleFellsDTO simpleFellsDTO = simpleFellsDTOAssembler.toDTO(fellsPage.toSet());
        LinksDTO linksDTO = linksDTOAssembler.toDTO(links);
        PagedCollectionDTO<SimpleFellsDTO> pagedCollectionDTO = new PagedCollectionDTO<>(
            linksDTO,
            simpleFellsDTO,
            String.valueOf(offset),
            String.valueOf(limit),
            String.valueOf(fellsPage.getTotalElements()));

        return new ResponseEntity<>(pagedCollectionDTO, HttpStatus.OK);
    }

    @GetMapping("/fells/{id}")
    public ResponseEntity<Object> getFell(@PathVariable final String id) {
        Fell fell = fellService.getById(new OsMapRef(id));
        Links links = new Links(
            linkService.buildForResourceWithIdAndRel("fells", fell.getOsMapRef().toString(), LinkRel.SELF),
            linkService.buildForResourceWithIdAndRel("fells", fell.getParentOsMapRef().toString(), LinkRel.PARENT));

        return new ResponseEntity<>(new ItemDTO<>(links, fell), HttpStatus.OK);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().build();
    }

}
