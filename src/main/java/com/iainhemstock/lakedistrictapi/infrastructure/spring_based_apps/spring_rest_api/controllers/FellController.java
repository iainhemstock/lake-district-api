package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.controllers;

import com.iainhemstock.lakedistrictapi.application_interfaces.ApiClockService;
import com.iainhemstock.lakedistrictapi.application_interfaces.FellService;
import com.iainhemstock.lakedistrictapi.domain.Fell;
import com.iainhemstock.lakedistrictapi.domain.OsMapRef;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.configuration.ApiProperties;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.domain.LinkedBasicFell;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.domain.LinkedFell;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.dtos.ErrorDTO;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.repository.LinkedBasicFellsResultPage;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.repository.LinkedResultPage;
import com.iainhemstock.lakedistrictapi.repository_interfaces.ResultPage;
import com.iainhemstock.lakedistrictapi.repository_interfaces.ResultPageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class FellController {

    @Autowired private FellService fellService;
    @Autowired private ApiProperties apiProperties;
    @Autowired private ApiClockService apiClockService;

    @GetMapping("/fells/{osMapRef}")
    public ResponseEntity<LinkedFell> getFell(@PathVariable final OsMapRef osMapRef) {
        Fell fell = fellService.getById(osMapRef);
        LinkedFell linkedFell = new LinkedFell(fell, apiProperties.getBaseUrl());
        return new ResponseEntity<>(linkedFell, HttpStatus.OK);
    }

    @GetMapping("/fells")
    public ResponseEntity<LinkedResultPage<LinkedBasicFell>> getFells(final ResultPageRequest pageRequest) {
        ResultPage<Fell> fellPage = fellService.getFells(pageRequest);
        return new ResponseEntity<>(new LinkedBasicFellsResultPage(fellPage, apiProperties.getBaseUrl()), HttpStatus.OK);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgument(IllegalArgumentException ex) {
        ErrorDTO errorDTO = new ErrorDTO(
            HttpStatus.BAD_REQUEST.toString(),
            ex.getMessage(),
            apiClockService.now());
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

}
