package com.iainhemstock.lakedistrictapi.controllers;

import com.iainhemstock.lakedistrictapi.config.ApiProperties;
import com.iainhemstock.lakedistrictapi.dtos.FellDto;
import com.iainhemstock.lakedistrictapi.services.ApiClock;
import com.iainhemstock.lakedistrictapi.services.FellDtoMapper;
import com.iainhemstock.lakedistrictapi.entities.FellEntity;
import com.iainhemstock.lakedistrictapi.exceptions.FellNotFoundException;
import com.iainhemstock.lakedistrictapi.repositories.FellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class FellController {

    @Autowired private ApiClock apiClock;
    @Autowired private ApiProperties apiProperties;
    @Autowired private FellRepository fellRepository;
    @Autowired private FellDtoMapper fellDTOMapper;

    @GetMapping("/api/v1/fells/{id}")
    public ResponseEntity<Object> getFell(@PathVariable int id) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Allow", "GET");

        FellEntity fellEntity = fellRepository.findById(id)
            .orElseThrow(() -> new FellNotFoundException(
                id,
                apiClock.now(),
                "GET",
                String.format("%s/fells/%d", apiProperties.getBaseUrl(), id),
                responseHeaders));

        return new ResponseEntity<>(
            fellDTOMapper.createDto(fellEntity),
            responseHeaders,
            HttpStatus.OK);
    }

}
