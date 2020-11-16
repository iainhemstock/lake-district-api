package com.iainhemstock.lakedistrictapi;

import com.iainhemstock.lakedistrictapi.dtos.FellDto;
import com.iainhemstock.lakedistrictapi.dtos.FleetwithPikeFellDto;
import com.iainhemstock.lakedistrictapi.dtos.SailFellDto;
import com.iainhemstock.lakedistrictapi.dtos.ScafellPikeFellDto;
import com.iainhemstock.lakedistrictapi.entities.fells.FleetwithPikeFellEntity;
import com.iainhemstock.lakedistrictapi.entities.fells.SailFellEntity;
import com.iainhemstock.lakedistrictapi.entities.fells.ScafellPikeFellEntity;
import com.iainhemstock.lakedistrictapi.services.Clock;
import io.restassured.http.ContentType;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.web.context.WebApplicationContext;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(JUnitParamsRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@TestPropertySource("/application.properties")
public class GetFellByIdAcceptanceTest {

    @ClassRule public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();
    @Rule public final SpringMethodRule springMethodRule = new SpringMethodRule();

    private static final int VALID_FELL_ID = 3;
    private static final int INVALID_FELL_ID = 999999;
    private static final String METHOD_NOT_ALLOWED = "METHOD_NOT_ALLOWED";
    private static final String NOT_FOUND = "NOT_FOUND";
    private static final String PATH = String.format("/fells/%d", VALID_FELL_ID);
    private static final String NOW = "25-03-2016 13:54:06";

    @Autowired WebApplicationContext webApplicationContext;

    @MockBean private Clock clock;

    @Before
    public void setUp() throws Exception {
        webAppContextSetup(webApplicationContext);
        Mockito.when(clock.timestamp()).thenReturn(NOW);
    }

    /*******************************************************************************************************************
     * POSITIVE TESTS
     ******************************************************************************************************************/
    @Test
    @Parameters({ "3", "4", "5" })
    public void when_sending_get_request_for_fell_by_id_then_response_body_will_conform_to_the_fell_json_schema(int validId) throws Exception {
        when()
            .get("/fells/{id}", validId)
        .then()
        .assertThat()
            .body(matchesJsonSchemaInClasspath("schemas/fell_schema.json"));
    }

    @Test
    @Parameters
    public void when_sending_get_request_for_fell_by_id_then_all_data_of_that_fell_will_be_returned(int validId, FellDto fellDTO) {
        when()
            .get("/fells/{id}", validId)
        .then().assertThat()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("name", is(fellDTO.getName()))
            .body("url", is(fellDTO.getUrl()))
            .body("parent_peak", is(fellDTO.getParentPeakUrl()))
            .body("classifications", containsInAnyOrder(fellDTO.getClassifications().toArray()))
            .root("height")
                .body("feet", is(fellDTO.getHeight().getFeet()))
                .body("meters", is(fellDTO.getHeight().getMeters()))
            .root("location")
                .body("region", is(fellDTO.getLocation().getRegionUri()))
                .body("os_map_ref", is(fellDTO.getLocation().getOsMapRef()))
                .body("os_maps", containsInAnyOrder(fellDTO.getLocation().getOsMaps().toArray()))
            .root("location.coords.decimal")
                .body("latitude", is(fellDTO.getLocation().getCoords().getDecimalCoords().getLatitude()))
                .body("longitude", is(fellDTO.getLocation().getCoords().getDecimalCoords().getLongitude()))
            .root("location.coords.dms.y")
                .body("formatted", is(fellDTO.getLocation().getCoords().getDmsCoords().getConvertedLongitude().getFormatted()))
                .body("degrees", is(fellDTO.getLocation().getCoords().getDmsCoords().getConvertedLongitude().getDegrees()))
                .body("minutes", is(fellDTO.getLocation().getCoords().getDmsCoords().getConvertedLongitude().getMinutes()))
                .body("seconds", is(fellDTO.getLocation().getCoords().getDmsCoords().getConvertedLongitude().getSeconds()))
                .body("hemisphere", is(fellDTO.getLocation().getCoords().getDmsCoords().getConvertedLongitude().getHemisphere()))
            .root("location.coords.dms.x")
                .body("formatted", is(fellDTO.getLocation().getCoords().getDmsCoords().getConvertedLatitude().getFormatted()))
                .body("degrees", is(fellDTO.getLocation().getCoords().getDmsCoords().getConvertedLatitude().getDegrees()))
                .body("minutes", is(fellDTO.getLocation().getCoords().getDmsCoords().getConvertedLatitude().getMinutes()))
                .body("seconds", is(fellDTO.getLocation().getCoords().getDmsCoords().getConvertedLatitude().getSeconds()))
                .body("hemisphere", is(fellDTO.getLocation().getCoords().getDmsCoords().getConvertedLatitude().getHemisphere()))
            .root("prominence")
                .body("feet", is(fellDTO.getProminence().getFeet()))
                .body("meters", is(fellDTO.getProminence().getMeters()));
    }

    private Object[] parametersForWhen_sending_get_request_for_fell_by_id_then_all_data_of_that_fell_will_be_returned() {
        return new Object[] {
            new Object[] { new ScafellPikeFellEntity().getId(), new ScafellPikeFellDto() },
            new Object[] { new FleetwithPikeFellEntity().getId(), new FleetwithPikeFellDto() },
            new Object[] { new SailFellEntity().getId(), new SailFellDto() }
        };
    }

    /*******************************************************************************************************************
     * EXTENDED POSITIVE TESTS
     ******************************************************************************************************************/


    /*******************************************************************************************************************
     * NEGATIVE TESTS WITH VALID INPUT
     ******************************************************************************************************************/

    @Test
    public void given_post_method_is_not_supported_when_attempting_to_send_post_request_to_url_then_response_body_will_contain_error_info() {
        when()
            .post("/fells/{id}", VALID_FELL_ID)
            .then()
            .assertThat()
            .statusCode(405) // METHOD_NOT_ALLOWED
            .contentType(ContentType.JSON)
            .body("status", equalTo(METHOD_NOT_ALLOWED))
            .body("message", equalTo("Method 'POST' is not allowed"))
            .body("path", equalTo(PATH))
            .body("timestamp", equalTo(NOW));

        verify(clock, times(1)).timestamp();
    }

    @Test
    public void given_put_method_is_not_supported_when_attempting_to_send_put_request_to_url_then_response_body_will_contain_error_info() {
        when()
            .put("/fells/{id}", VALID_FELL_ID)
            .then()
            .assertThat()
            .statusCode(405) // METHOD_NOT_ALLOWED
            .contentType(ContentType.JSON)
            .body("status", equalTo(METHOD_NOT_ALLOWED))
            .body("message", equalTo("Method 'PUT' is not allowed"))
            .body("path", equalTo(PATH))
            .body("timestamp", equalTo(NOW));

        verify(clock, times(1)).timestamp();
    }

    @Test
    public void given_patch_method_is_not_supported_when_attempting_to_send_patch_request_to_url_then_response_body_will_contain_error_info() {
        when()
            .patch("/fells/{id}", VALID_FELL_ID)
            .then()
            .assertThat()
            .statusCode(405) // METHOD_NOT_ALLOWED
            .contentType(ContentType.JSON)
            .body("status", equalTo(METHOD_NOT_ALLOWED))
            .body("message", equalTo("Method 'PATCH' is not allowed"))
            .body("path", equalTo(PATH))
            .body("timestamp", equalTo(NOW));

        verify(clock, times(1)).timestamp();
    }

    @Test
    public void given_delete_method_is_not_supported_when_attempting_to_send_delete_request_to_url_then_response_body_will_contain_error_info() {
        when()
            .delete("/fells/{id}", VALID_FELL_ID)
            .then()
            .assertThat()
            .statusCode(405) // METHOD_NOT_ALLOWED
            .contentType(ContentType.JSON)
            .body("status", equalTo(METHOD_NOT_ALLOWED))
            .body("message", equalTo("Method 'DELETE' is not allowed"))
            .body("path", equalTo(PATH))
            .body("timestamp", equalTo(NOW));

        verify(clock, times(1)).timestamp();
    }

    /*******************************************************************************************************************
     * NEGATIVE TESTS WITH INVALID INPUT
     ******************************************************************************************************************/

    @Test
    @Parameters({ "-1", "0", "99999" })
    public void given_id_that_doesnt_exist_when_sending_get_request_for_fell_by_id_then_response_body_will_contain_error_info(final int nonExistentId) {
        when().get("/fells/{id}", nonExistentId)
            .then().assertThat()
            .statusCode(404)
            .contentType(ContentType.JSON)
            .body("status", equalTo(NOT_FOUND))
            .body("message", equalTo(String.format("Fell was not found for {id=%d}", nonExistentId)))
            .body("path", equalTo(String.format("/fells/%d", nonExistentId)))
            .body("timestamp", equalTo(NOW));

        verify(clock, times(1)).timestamp();
    }

    /*******************************************************************************************************************
     * DESTRUCTIVE TESTS
     ******************************************************************************************************************/




}