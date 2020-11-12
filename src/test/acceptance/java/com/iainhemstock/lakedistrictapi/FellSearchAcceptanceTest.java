/*
 * I want to search for a fell.
 *
 * test that exact search term produces a single result
 * test that partial search term containing wildcard produces multiple results
 * test that empty search term produces all results
 * test that non matching search term produces no results
 * test that response contains correct result count, next page url, previous page url and matching results
 */

package com.iainhemstock.lakedistrictapi;

import com.iainhemstock.lakedistrictapi.dtos.*;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@TestPropertySource("/test.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public final class FellSearchAcceptanceTest {

    @Autowired private WebApplicationContext webApplicationContext;

    private SearchDTO searchDTO;

    @Before
    public void setUp() {
        webAppContextSetup(webApplicationContext);
    }

    @Test
    public void when_searching_for_fell_then_response_will_conform_to_json_schema() {
        when().get("/fells?search=abc")
            .then().assertThat()
            .body(matchesJsonSchemaInClasspath("schemas/fell_search_response_schema.json"));
    }

    @Test
    public void given_search_term_with_no_wildcard_when_searching_for_specific_fell_then_response_results_will_contain_that_fell() {
        searchDTO = when().get("/fells?search=scafell+pike")
            .then().assertThat()
            .status(HttpStatus.OK)
            .contentType(ContentType.JSON)
            .body("count", is(1))
            .body("previous", is(nullValue()))
            .body("next", is(nullValue()))
            .body("results", hasSize(1))
            .extract().response().as(SearchDTO.class);

        assertThat(searchDTO.getResults().get(0),
            is(equalTo(new ScafellPikeFellDTO())));

    }

    @Test
    public void given_search_term_beginning_with_wildcard_when_sending_search_request_then_response_will_contain_matching_fells() {
        searchDTO = when().get("/fells?search=*pike")
            .then().assertThat()
            .status(HttpStatus.OK)
            .contentType(ContentType.JSON)
            .body("count", is(2))
            .body("previous", is(nullValue()))
            .body("next", is(nullValue()))
            .body("results", hasSize(2))
            .extract().response().as(SearchDTO.class);
        assertThat(searchDTO.getResults(),
            containsInAnyOrder(new ScafellPikeFellDTO(), new FleetwithPikeFellDTO()));
    }

    @Test
    public void given_search_term_ending_with_wildcard_when_sending_search_request_then_response_will_contain_matching_fells() {
        searchDTO = when().get("/fells?search=sca*")
            .then().assertThat()
            .status(HttpStatus.OK)
            .contentType(ContentType.JSON)
            .body("count", is(2))
            .body("previous", is(nullValue()))
            .body("next", is(nullValue()))
            .body("results", hasSize(2))
            .extract().response().as(SearchDTO.class);

        assertThat(searchDTO.getResults(),
            containsInAnyOrder(new ScafellPikeFellDTO(), new ScafellFellDTO()));
    }

    @Test
    public void given_search_term_contains_embedded_wildcard_when_sending_search_request_then_response_will_contain_matching_fells() {
        searchDTO = when().get("/fells?search=s*l")
            .then().assertThat()
            .status(HttpStatus.OK)
            .contentType(ContentType.JSON)
            .body("count", is(2))
            .body("previous", is(nullValue()))
            .body("next", is(nullValue()))
            .body("results", hasSize(2))
            .extract().response().as(SearchDTO.class);

        assertThat(searchDTO.getResults(),
            containsInAnyOrder(new ScafellFellDTO(), new SailFellDTO()));
    }
}
