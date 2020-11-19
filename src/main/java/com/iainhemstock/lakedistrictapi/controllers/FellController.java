package com.iainhemstock.lakedistrictapi.controllers;

import com.iainhemstock.lakedistrictapi.services.ApiClock;
import com.iainhemstock.lakedistrictapi.services.FellDtoMapper;
import com.iainhemstock.lakedistrictapi.entities.FellEntity;
import com.iainhemstock.lakedistrictapi.exceptions.FellNotFoundException;
import com.iainhemstock.lakedistrictapi.repositories.FellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
public class FellController {

    @Autowired private ApiClock apiClock;
    @Autowired private FellRepository fellRepository;
    @Autowired private FellDtoMapper fellDTOMapper;

    @GetMapping("/api/fells/{id}")
    public ResponseEntity<Object> getFell(@PathVariable int id) throws FellNotFoundException {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Allow", "GET");

        FellEntity fellEntity = fellRepository.findById(id)
            .orElseThrow(() -> new FellNotFoundException(
                id,
                apiClock.now(),
                "GET",
                String.format("http://localhost:8080/api/fells/%d", id),
                responseHeaders));


        return new ResponseEntity<>(
            fellDTOMapper.createDto(fellEntity),
            responseHeaders,
            HttpStatus.OK);
    }

}
