package com.iainhemstock.lakedistrictapi.config;

public class TestApiProperties extends ApiProperties {

    private static final String API_BASE_URL = "http://localhost:8080/api/v1";

    @Override
    public String getBaseUrl() {
        return API_BASE_URL;
    }
}
