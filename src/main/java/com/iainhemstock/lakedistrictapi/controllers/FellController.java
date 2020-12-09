package com.iainhemstock.lakedistrictapi.controllers;

import com.iainhemstock.lakedistrictapi.config.ApiProperties;
import com.iainhemstock.lakedistrictapi.dtos.FellCollectionDto;
import com.iainhemstock.lakedistrictapi.entities.Fell;
import com.iainhemstock.lakedistrictapi.exceptions.FellNotFoundException;
import com.iainhemstock.lakedistrictapi.repositories.FellRepository;
import com.iainhemstock.lakedistrictapi.services.ApiClock;
import com.iainhemstock.lakedistrictapi.services.mappers.FellCollectionMapper;
import com.iainhemstock.lakedistrictapi.services.mappers.FellMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1")
public class FellController {

    @Autowired private ApiClock apiClock;
    @Autowired private ApiProperties apiProperties;
    @Autowired private FellRepository fellRepository;
    @Autowired private FellMapper fellMapper;
    @Autowired private FellCollectionMapper fellCollectionMapper;

    @GetMapping(value = "/fells")
    public ResponseEntity<FellCollectionDto> getFells(final Pageable pageable) {
        Page<Fell> fellPage = fellRepository.findAll(pageable);
        FellCollectionDto collectionDto = fellCollectionMapper.map(fellPage);
        return new ResponseEntity<>(collectionDto, HttpStatus.OK);
    }

    @GetMapping("/fells/{id}")
    public ResponseEntity<Object> getFell(@PathVariable final String id) {
        Fell fell = fellRepository.findById(id)
            .orElseThrow(() -> new FellNotFoundException(
                id,
                apiClock.now(),
                HttpMethod.GET.name(),
                String.format("%s/fells/%s", apiProperties.getBaseUrl(), id)));
        return new ResponseEntity<>(fellMapper.map(fell), HttpStatus.OK);
    }

}
