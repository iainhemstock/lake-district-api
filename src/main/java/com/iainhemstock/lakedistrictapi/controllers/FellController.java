package com.iainhemstock.lakedistrictapi.controllers;

import com.iainhemstock.lakedistrictapi.services.Clock;
import com.iainhemstock.lakedistrictapi.services.FellDTOMapper;
import com.iainhemstock.lakedistrictapi.dtos.FellDTO;
import com.iainhemstock.lakedistrictapi.entities.FellEntity;
import com.iainhemstock.lakedistrictapi.exceptions.HttpMethodNotAllowedException;
import com.iainhemstock.lakedistrictapi.exceptions.FellNotFoundException;
import com.iainhemstock.lakedistrictapi.repositories.FellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class FellController {

    public static final double METERS_TO_FEET_CONVERSION = 3.2808;

    @Autowired private Clock clock;
    @Autowired private FellRepository fellRepository;
    @Autowired private FellDTOMapper fellDTOMapper;

    @GetMapping("/fells/{id}")
    public FellDTO getFell(@PathVariable int id) {
        FellEntity fellEntity = fellRepository.findById(id)
            .orElseThrow(() -> new FellNotFoundException(id, clock.timestamp()));

        return fellDTOMapper.createDTO(fellEntity);
    }

    @RequestMapping(
        value = "/fells/{id}",
        method = {
            RequestMethod.POST,
            RequestMethod.PUT,
            RequestMethod.PATCH,
            RequestMethod.DELETE})
    public void unsupportedHttpMethod(@PathVariable int id,  HttpMethod method) {
        throw new HttpMethodNotAllowedException("/fells/" + id, clock.timestamp(), method);
    }

}
