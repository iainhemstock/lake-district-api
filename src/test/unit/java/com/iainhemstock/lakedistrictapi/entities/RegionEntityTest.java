package com.iainhemstock.lakedistrictapi.entities;

import org.junit.Assert;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class RegionEntityTest {

    private static String API_BASE_URL = "http://localhost:8080/api";

    @Test
    public void region_entity_creates_url_from_id() throws MalformedURLException {
        RegionEntity regionEntity = new RegionEntity(5, "Great Gable");
        URL expectedUrl = new URL(API_BASE_URL + "/region/5");

        Assert.assertEquals(expectedUrl, regionEntity.getUrl());
    }
}
