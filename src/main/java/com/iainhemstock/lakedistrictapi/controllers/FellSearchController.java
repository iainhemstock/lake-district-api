package com.iainhemstock.lakedistrictapi.controllers;

import com.iainhemstock.lakedistrictapi.dtos.FellDetailedDTO;
import com.iainhemstock.lakedistrictapi.services.mappers.FellDetailedMapper;
import com.iainhemstock.lakedistrictapi.dtos.SearchDTO;
import com.iainhemstock.lakedistrictapi.entities.Fell;
import com.iainhemstock.lakedistrictapi.repositories.FellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FellSearchController {

    @Autowired private FellRepository fellRepository;
    @Autowired private FellDetailedMapper fellDetailedMapper;

    @GetMapping("/api/fells")
    public SearchDTO getFells(@RequestParam("search") String searchTerm) {
        String processedSearchTerm = searchTerm
            .replace('+', ' ')
            .replace('*', '%');
        List<Fell> fellEntities = fellRepository.findByNameLikeIgnoreCase(processedSearchTerm);

        List<FellDetailedDTO> fellDetailedDTOS = fellEntities.stream()
            .map(entity -> fellDetailedMapper.map(entity))
            .collect(Collectors.toList());

        return new SearchDTO(fellDetailedDTOS);
    }

}
