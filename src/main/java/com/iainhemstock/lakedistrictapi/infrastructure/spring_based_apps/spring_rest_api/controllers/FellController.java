package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.controllers;

import com.iainhemstock.lakedistrictapi.domain.Fell;
import com.iainhemstock.lakedistrictapi.domain.LinkRel;
import com.iainhemstock.lakedistrictapi.domain.Links;
import com.iainhemstock.lakedistrictapi.domain.OsMapRef;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.FellEntity;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.dtos.*;
import com.iainhemstock.lakedistrictapi.application_interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

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

        Map<String, String> linksMap = new HashMap<>();
        links.forEach(link -> linksMap.put(link.getRel().toString(), link.getHref()));
        LinksDTO linksDTO = new LinksDTO();
        linksDTO.setLinks(linksMap);

        FellDTO fellDTO = new FellDTO();
        fellDTO.setHeightMeters(fell.getHeightMeters().toString());
        fellDTO.setHeightFeet(fell.getHeightFeet().toString());
        fellDTO.setProminenceMeters(fell.getProminenceMeters().toString());
        fellDTO.setProminenceFeet(fell.getProminenceFeet().toString());
        fellDTO.setOsMapRef(fell.getOsMapRef().toString());
        fellDTO.setLatitude(fell.getLatitude().toString());
        fellDTO.setLongitude(fell.getLongitude().toString());
        fellDTO.setLatitudeAsDms(Map.of(
            "degrees", fell.getConvertedLatitude().getDegrees().toString(),
            "minutes", fell.getConvertedLatitude().getMinutes().toString(),
            "seconds", fell.getConvertedLatitude().getSeconds().toString(),
            "hemisphere", fell.getConvertedLatitude().getHemisphere().toString()));
        fellDTO.setLongitudeAsDms(Map.of(
            "degrees", fell.getConvertedLongitude().getDegrees().toString(),
            "minutes", fell.getConvertedLongitude().getMinutes().toString(),
            "seconds", fell.getConvertedLongitude().getSeconds().toString(),
            "hemisphere", fell.getConvertedLongitude().getHemisphere().toString()));
        fellDTO.setRegion(fell.getRegionName().toString());

        Set<String> osMapNames = new LinkedHashSet<>();
        fell.getOsMapNames().forEach(osMapName -> osMapNames.add(osMapName.toString()));
        fellDTO.setOsMapNames(osMapNames);

        Set<String> classificationNames = new LinkedHashSet<>();
        fell.getClassificationNames().forEach(classificationName -> classificationNames.add(classificationName.toString()));
        fellDTO.setClassificationNames(classificationNames);

        fellDTO.setName(fell.getName().toString());


        return new ResponseEntity<>(new ItemDTO(linksDTO, fellDTO), HttpStatus.OK);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().build();
    }

}
