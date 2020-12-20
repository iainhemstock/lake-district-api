package com.iainhemstock.lakedistrictapi.controllers;

import com.iainhemstock.lakedistrictapi.domain.DetailedFell;
import com.iainhemstock.lakedistrictapi.domain.OsMapRef;
import com.iainhemstock.lakedistrictapi.dtos.DetailedFellDTO;
import com.iainhemstock.lakedistrictapi.dtos.PagedCollectionDTO;
import com.iainhemstock.lakedistrictapi.dtos.SummarisedFellDTO;
import com.iainhemstock.lakedistrictapi.services.FellService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class FellController {

    @Autowired private FellService fellService;
    @Autowired private ModelMapper mapper;


    @GetMapping(value = "/fells")
    public ResponseEntity<PagedCollectionDTO<SummarisedFellDTO>> getFells(@RequestParam(value = "offset", required = false, defaultValue = "${api.pageable.default-page-offset}") Integer offset,
                                                                          @RequestParam(value = "limit", required = false, defaultValue = "${spring.data.web.pageable.default-page-size}") Integer limit) {
        PagedCollectionDTO<SummarisedFellDTO> pagedCollectionDTO = fellService.getSummarisedFells(offset, limit);
        return new ResponseEntity<>(pagedCollectionDTO, HttpStatus.OK);
    }

    @GetMapping("/fells/{id}")
    public ResponseEntity<Object> getFell(@PathVariable final String id) {
        DetailedFell detailedFell = fellService.getDetailedFell(new OsMapRef(id));
        DetailedFellDTO dto = mapper.map(detailedFell, DetailedFellDTO.class);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().build();
    }

}
