package com.iainhemstock.lakedistrictapi.controllers;

import com.iainhemstock.lakedistrictapi.dtos.FellDTO;
import com.iainhemstock.lakedistrictapi.services.FellDTOMapper;
import com.iainhemstock.lakedistrictapi.dtos.SearchDTO;
import com.iainhemstock.lakedistrictapi.entities.FellEntity;
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
    @Autowired private FellDTOMapper fellDTOMapper;

    @GetMapping("/fells")
    public SearchDTO getFells(@RequestParam("search") String searchTerm) {
        String processedSearchTerm = searchTerm
            .replace('+', ' ')
            .replace('*', '%');
        List<FellEntity> fellEntities = fellRepository.findByNameLikeIgnoreCase(processedSearchTerm);

        List<FellDTO> fellDTOS = fellEntities.stream()
            .map(entity -> fellDTOMapper.createDTO(entity))
            .collect(Collectors.toList());

        return new SearchDTO(fellDTOS);
    }

}
