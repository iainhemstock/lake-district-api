package com.iainhemstock.lakedistrictapi.controllers;

import com.iainhemstock.lakedistrictapi.dtos.FellDto;
import com.iainhemstock.lakedistrictapi.services.mappers.FellMapper;
import com.iainhemstock.lakedistrictapi.dtos.SearchDto;
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
    @Autowired private FellMapper fellMapper;

    @GetMapping("/api/fells")
    public SearchDto getFells(@RequestParam("search") String searchTerm) {
        String processedSearchTerm = searchTerm
            .replace('+', ' ')
            .replace('*', '%');
        List<Fell> fellEntities = fellRepository.findByNameLikeIgnoreCase(processedSearchTerm);

        List<FellDto> fellDtos = fellEntities.stream()
            .map(entity -> fellMapper.map(entity))
            .collect(Collectors.toList());

        return new SearchDto(fellDtos);
    }

}
