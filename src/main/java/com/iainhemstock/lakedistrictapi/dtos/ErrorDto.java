package com.iainhemstock.lakedistrictapi.dtos;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.net.HttpURLConnection;

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
