package com.iainhemstock.lakedistrictapi.dtos;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class ErrorDTO {
    private String status;
    private String message;
    private String path;
    private String timestamp;
}
