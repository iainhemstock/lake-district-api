package com.iainhemstock.lakedistrictapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SimpleFellsDTO {
    private Set<SimpleFellDTO> fells;
}
