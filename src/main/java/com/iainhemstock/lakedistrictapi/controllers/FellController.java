package com.iainhemstock.lakedistrictapi.controllers;

import com.iainhemstock.lakedistrictapi.domain.LinkRel;
import com.iainhemstock.lakedistrictapi.domain.Links;
import com.iainhemstock.lakedistrictapi.domain.OsMapRef;
import com.iainhemstock.lakedistrictapi.dtos.*;
import com.iainhemstock.lakedistrictapi.entities.Fell;
import com.iainhemstock.lakedistrictapi.serviceinterfaces.FellDTOAssembler;
import com.iainhemstock.lakedistrictapi.serviceinterfaces.FellService;
import com.iainhemstock.lakedistrictapi.serviceinterfaces.LinkService;
import com.iainhemstock.lakedistrictapi.serviceinterfaces.LinksDTOAssembler;
import com.iainhemstock.lakedistrictapi.services.FellServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class FellController {

    @Autowired private FellService fellService;
    @Autowired private FellDTOAssembler fellDTOAssembler;
    @Autowired private LinksDTOAssembler linksDTOAssembler;
    @Autowired private LinkService linkService;

    @GetMapping(value = "/fells")
    public ResponseEntity<PagedCollectionDTO<SummarisedFellDTO>> getFells(@RequestParam(value = "offset", required = false, defaultValue = "${api.pageable.default-page-offset}") Integer offset,
                                                                          @RequestParam(value = "limit", required = false, defaultValue = "${spring.data.web.pageable.default-page-size}") Integer limit) {
//======================================================================================================================
//        Page<FellEntity> fellPage = fellEntityService.getWithOffsetAndLimit(offset, limit);
//        SummarisedFells summarisedFells = summarisedFellsAssembler.toSummarisedFells(fellPage.toList());
//======================================================================================================================
//        Items<SummarisedFell> items = new Items<>();
//        for (SummarisedFell fell : summarisedFells) {
//            OsMapRef osMapRef = fell.getOsMapRef();
//            Link self = endpointService.buildForResourceWithIdAndRel("fells", osMapRef, LinkRel.SELF);
//            // any other link we want ...
//            Item item = itemAssembler(fell, List.of(self));
//            items.add(item);
//        }
//======================================================================================================================
//        Items<SummarisedFell> items = itemsAssembler.toItems(summarisedFells.toList());
//        List<Link> links = List.of(
//            new Link(LinkRel.SELF, "http:localhost"),
//            new Link(LinkRel.NEXT, "http:localhost"));
//        PagedCollectionDTO<SummarisedFell> pagedCollectionDTO = pagedCollectionAssembler.toDTO(links, items);
//
//        return new ResponseEntity<SummarisedFell>(pagedCollectionDTO, HttpStatus.OK);
//======================================================================================================================
        PagedCollectionDTO<SummarisedFellDTO> pagedCollectionDTO = fellService.getSummarisedFells(offset, limit);
        return new ResponseEntity<>(pagedCollectionDTO, HttpStatus.OK);
    }
//
    @GetMapping("/fells/{id}")
    public ResponseEntity<Object> getFell(@PathVariable final String id) {
        Fell fell = fellService.getById(new OsMapRef(id));
        Links links = new Links(
            linkService.buildForResourceWithIdAndRel("fells", fell.getOsMapRef().toString(), LinkRel.SELF),
            linkService.buildForResourceWithIdAndRel("fells", fell.getParentPeak().getOsMapRef().toString(), LinkRel.PARENT));
        FellDTO fellDTO = fellDTOAssembler.toDTO(fell);
        LinksDTO linksDTO = linksDTOAssembler.toDTO(links);
        ItemDTO itemDTO = new ItemDTO(linksDTO, fellDTO);

        return new ResponseEntity<>(itemDTO, HttpStatus.OK);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().build();
    }

}
