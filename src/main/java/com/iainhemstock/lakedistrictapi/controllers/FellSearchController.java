package com.iainhemstock.lakedistrictapi.controllers;

import com.iainhemstock.lakedistrictapi.dtos.FellDetailedDTO;
import com.iainhemstock.lakedistrictapi.dtos.SearchDTO;
import com.iainhemstock.lakedistrictapi.entities.Fell;
import com.iainhemstock.lakedistrictapi.repositories.FellRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FellSearchController {

    @Autowired private FellRepository fellRepository;
    @Autowired private ModelMapper mapper;

    @GetMapping("/api/fells")
    public SearchDTO getFells(@RequestParam("search") String searchTerm) {
        String processedSearchTerm = searchTerm
            .replace('+', ' ')
            .replace('*', '%');
        List<Fell> fells = fellRepository.findByNameLikeIgnoreCase(processedSearchTerm);

        List<FellDetailedDTO> fellDetailedDTOS = fells.stream()
            .map(fell -> mapper.map(fell, FellDetailedDTO.class))
            .collect(Collectors.toList());

        return new SearchDTO(fellDetailedDTOS);
    }

}
