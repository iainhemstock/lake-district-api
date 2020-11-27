package com.iainhemstock.lakedistrictapi.dtos;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class ErrorDto {
    private String status;
    private String message;
    private String path;
    private String timestamp;
}
