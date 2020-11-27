package com.iainhemstock.lakedistrictapi.controllers;

import com.iainhemstock.lakedistrictapi.config.ApiProperties;
import com.iainhemstock.lakedistrictapi.entities.Fell;
import com.iainhemstock.lakedistrictapi.exceptions.FellNotFoundException;
import com.iainhemstock.lakedistrictapi.repositories.FellRepository;
import com.iainhemstock.lakedistrictapi.services.ApiClock;
import com.iainhemstock.lakedistrictapi.services.mappers.FellMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FellController {

    @Autowired private ApiClock apiClock;
    @Autowired private ApiProperties apiProperties;
    @Autowired private FellRepository fellRepository;
    @Autowired private FellMapper fellMapper;

    @GetMapping("/api/v1/fells/{id}")
    public ResponseEntity<Object> getFell(@PathVariable int id) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Allow", "GET");

        Fell fell = fellRepository.findById(id)
            .orElseThrow(() -> new FellNotFoundException(
                                    id, apiClock.now(), "GET",
                                    String.format("%s/fells/%d", apiProperties.getBaseUrl(), id),
                                    responseHeaders));


        return new ResponseEntity<>(
            fellMapper.map(fell),
            responseHeaders,
            HttpStatus.OK);
    }

}
