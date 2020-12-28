package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.dtos;

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
