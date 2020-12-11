package com.iainhemstock.lakedistrictapi.controllers;

import com.iainhemstock.lakedistrictapi.config.ApiProperties;
import com.iainhemstock.lakedistrictapi.dtos.FellSimplifiedDTO;
import com.iainhemstock.lakedistrictapi.dtos.PagedCollectionDTO;
import com.iainhemstock.lakedistrictapi.entities.Fell;
import com.iainhemstock.lakedistrictapi.exceptions.FellNotFoundException;
import com.iainhemstock.lakedistrictapi.repositories.FellRepository;
import com.iainhemstock.lakedistrictapi.services.ApiClock;
import com.iainhemstock.lakedistrictapi.services.mappers.FellSimplifiedPagedCollectionMapper;
import com.iainhemstock.lakedistrictapi.services.mappers.FellDetailedMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class FellController {

    @Autowired private ApiClock apiClock;
    @Autowired private ApiProperties apiProperties;
    @Autowired private FellRepository fellRepository;
    @Autowired private FellDetailedMapper fellDetailedMapper;
    @Autowired private FellSimplifiedPagedCollectionMapper pagedCollectionMapper;

    @GetMapping(value = "/fells")
    public ResponseEntity<PagedCollectionDTO> getFells(@RequestParam(value = "offset", required = false, defaultValue = "${api.pageable.default-page-offset}") Integer offset,
                                                       @RequestParam(value = "limit", required = false, defaultValue = "${spring.data.web.pageable.default-page-size}") Integer limit) {
        if (offset < 0) {
            throw new IllegalArgumentException("Param 'offset' cannot be negative");
        }

        Page<Fell> fellPage = fellRepository.findAll(PageRequest.of(offset, limit));
        PagedCollectionDTO<FellSimplifiedDTO> pagedCollectionDTO = pagedCollectionMapper.map(fellPage);
        return new ResponseEntity<>(pagedCollectionDTO, HttpStatus.OK);
    }

    @GetMapping("/fells/{id}")
    public ResponseEntity<Object> getFell(@PathVariable final String id) {
        Fell fell = fellRepository.findById(id)
            .orElseThrow(() -> new FellNotFoundException(
                id,
                apiClock.now(),
                HttpMethod.GET.name(),
                String.format("%s/fells/%s", apiProperties.getBaseUrl(), id)));
        return new ResponseEntity<>(fellDetailedMapper.map(fell), HttpStatus.OK);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().build();
    }

}
