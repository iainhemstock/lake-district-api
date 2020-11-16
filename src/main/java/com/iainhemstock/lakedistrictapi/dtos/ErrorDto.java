package com.iainhemstock.lakedistrictapi.dtos;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ErrorDto {
    private HttpStatus status;
    private String message;
    private String path;
    private String timestamp;
}
