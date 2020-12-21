package com.iainhemstock.lakedistrictapi.controllers;

import com.iainhemstock.lakedistrictapi.dtos.DetailedFellDTO;
import com.iainhemstock.lakedistrictapi.dtos.SearchDTO;
import com.iainhemstock.lakedistrictapi.entities.FellEntity;
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
//        String processedSearchTerm = searchTerm
//            .replace('+', ' ')
//            .replace('*', '%');
//        List<FellEntity> fells = fellRepository.findByNameLikeIgnoreCase(processedSearchTerm);
//
//        List<DetailedFellDTO> detailedFellDTOS = fells.stream()
//            .map(fell -> mapper.map(fell, DetailedFellDTO.class))
//            .collect(Collectors.toList());
//
//        return new SearchDTO(detailedFellDTOS);
        return new SearchDTO();
    }

}
