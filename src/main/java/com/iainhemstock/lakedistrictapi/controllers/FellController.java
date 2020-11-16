package com.iainhemstock.lakedistrictapi.controllers;

import com.iainhemstock.lakedistrictapi.services.Clock;
import com.iainhemstock.lakedistrictapi.services.FellDtoMapper;
import com.iainhemstock.lakedistrictapi.dtos.FellDto;
import com.iainhemstock.lakedistrictapi.entities.FellEntity;
import com.iainhemstock.lakedistrictapi.exceptions.HttpMethodNotAllowedException;
import com.iainhemstock.lakedistrictapi.exceptions.FellNotFoundException;
import com.iainhemstock.lakedistrictapi.repositories.FellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;

@RestController
public class FellController {

    @Autowired private Clock clock;
    @Autowired private FellRepository fellRepository;
    @Autowired private FellDtoMapper fellDTOMapper;

    @GetMapping("/api/fells/{id}")
    public FellDto getFell(@PathVariable int id) {
        FellEntity fellEntity = fellRepository.findById(id)
            .orElseThrow(() -> new FellNotFoundException(id, clock.timestamp()));

        return fellDTOMapper.createDto(fellEntity);
    }

    @RequestMapping(
        value = "/api/fells/{id}",
        method = {
            RequestMethod.POST,
            RequestMethod.PUT,
            RequestMethod.PATCH,
            RequestMethod.DELETE})
    public void unsupportedHttpMethod(@PathVariable int id,  HttpMethod method) {
        throw new HttpMethodNotAllowedException("/fells/" + id, clock.timestamp(), method);
    }

}
