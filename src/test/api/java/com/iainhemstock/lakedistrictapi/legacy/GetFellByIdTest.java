package com.iainhemstock.lakedistrictapi.legacy;

import com.iainhemstock.lakedistrictapi.controllers.FellController;
import com.iainhemstock.lakedistrictapi.dtos.FleetwithPikeFellDto;
import com.iainhemstock.lakedistrictapi.dtos.ScafellPikeFellDto;
import com.iainhemstock.lakedistrictapi.entities.fells.FleetwithPikeFellEntity;
import com.iainhemstock.lakedistrictapi.entities.fells.ScafellPikeFellEntity;
import com.iainhemstock.lakedistrictapi.repositories.FellRepository;
import com.iainhemstock.lakedistrictapi.services.ApiClock;
import com.iainhemstock.lakedistrictapi.services.FellDtoMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(FellController.class)
public class GetFellByIdTest {

    @Autowired private MockMvc mockMvc;

    @MockBean private FellRepository fellRepository;
    @MockBean private FellDtoMapper fellDTOMapper;
    @MockBean private ApiClock apiClock;

    private ScafellPikeFellEntity scafellPikeFellEntity;
    private ScafellPikeFellDto scafellPikeFellDTO;

    @Before
    public void setUp() throws Exception {
        scafellPikeFellEntity = new ScafellPikeFellEntity();
        scafellPikeFellDTO = new ScafellPikeFellDto();
        mockFellRepositoryBehaviour();
        mockFellDTOMapperBehaviour();
    }

    private void mockFellRepositoryBehaviour() {
        when(fellRepository.findById(anyInt())).thenReturn(
            Optional.of(scafellPikeFellEntity));
    }

    private void mockFellDTOMapperBehaviour() {
        Mockito.when(fellDTOMapper.createDto(scafellPikeFellEntity))
            .thenReturn(scafellPikeFellDTO);
    }


    /*******************************************************************************************************************
     *
     * Positive tests
     *
     ******************************************************************************************************************/
    @Test
    public void given_fellExists_when_sendingGetRequestForFellById_then_responseWillContainItsName() throws Exception {
        mockMvc.perform(
            get("/fells/{id}", scafellPikeFellEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.name", is(scafellPikeFellDTO.getName())));
    }

    @Test
    public void given_fell_exists_when_sending_get_request_for_fell_by_id_then_response_will_contain_its_parent_peak() throws Exception {
        FleetwithPikeFellEntity fleetwithPikeFellEntity = new FleetwithPikeFellEntity();
        Mockito.when(fellRepository.findById(anyInt()))
            .thenReturn(Optional.of(fleetwithPikeFellEntity));
        Mockito.when(fellDTOMapper.createDto(new FleetwithPikeFellEntity()))
            .thenReturn(new FleetwithPikeFellDto());

        mockMvc.perform(
            get("/fells/{id}", fleetwithPikeFellEntity.getId()))
            .andExpect(
                status().isOk())
            .andExpect(
                content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                jsonPath("$.parent_peak", is(equalTo(new FleetwithPikeFellDto().getParentPeakUrl()))));
    }

    @Test
    public void given_fell_exists_and_has_no_parent_when_sending_get_request_for_fell_by_id_then_response_will_contain_blank_parent_peak() throws Exception {
        mockMvc.perform(
            get("/fells/{id}", scafellPikeFellEntity.getId()))
            .andExpect(
                status().isOk())
            .andExpect(
                content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                jsonPath("$.parent_peak", is(equalTo(""))));
    }

    @Test
    public void given_fell_exists_when_sending_get_request_for_fell_by_id_then_response_will_contain_its_url() throws Exception {
        mockMvc.perform(
            get("/fells/{id}", scafellPikeFellEntity.getId()))
            .andExpect(
                status().isOk())
            .andExpect(
                content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                jsonPath("$.url", is(equalTo(scafellPikeFellDTO.getUrl()))));
    }

    @Test
    public void given_fell_exists_when_sending_get_request_for_fell_by_id_then_response_will_contain_its_height() throws Exception {
        mockMvc.perform(
            get("/fells/{id}", scafellPikeFellEntity.getId()))
            .andExpect(
                status().isOk())
            .andExpect(
                content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                jsonPath("$.height.feet", is(equalTo(scafellPikeFellDTO.getHeight().getFeet()))))
            .andExpect(
                jsonPath("$.height.meters", is((equalTo(scafellPikeFellDTO.getHeight().getMeters())))));
    }

    @Test
    public void given_fell_exists_when_sending_get_request_for_fell_by_id_then_response_will_contain_its_prominence() throws Exception {
        mockMvc.perform(
            get("/fells/{id}", scafellPikeFellEntity.getId()))
            .andExpect(
                status().isOk())
            .andExpect(
                content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                jsonPath("$.prominence.feet", is(equalTo(scafellPikeFellDTO.getProminence().getFeet()))))
            .andExpect(
                jsonPath("$.prominence.meters", is((equalTo(scafellPikeFellDTO.getProminence().getMeters())))));
    }

    @Test
    public void given_fell_exists_when_sending_get_request_for_fell_by_id_then_response_will_contain_its_latitude_and_longitude() throws Exception {
        mockMvc.perform(
            get("/fells/{id}", scafellPikeFellEntity.getId()))
            .andExpect(
                status().isOk())
            .andExpect(
                content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                jsonPath("$.location.coords.decimal.latitude",
                    is(equalTo(scafellPikeFellDTO.getLocation().getDecimalCoords().getLatitude()))))
            .andExpect(
                jsonPath("$.location.coords.decimal.longitude",
                    is((equalTo(scafellPikeFellDTO.getLocation().getDecimalCoords().getLongitude())))));
    }

    @Test
    public void given_fell_exists_when_sending_get_request_for_fell_by_id_then_response_will_contain_its_dms_coord() throws Exception {
        mockMvc.perform(
            get("/fells/{id}", scafellPikeFellEntity.getId()))
            .andExpect(
                status().isOk())
            .andExpect(
                content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                jsonPath("$.location.coords.dms.y.degrees",
                    is(equalTo(scafellPikeFellDTO.getLocation().getDmsCoords().get(1).getDegrees()))))
            .andExpect(
                jsonPath("$.location.coords.dms.y.minutes",
                    is(equalTo(scafellPikeFellDTO.getLocation().getDmsCoords().get(1).getMinutes()))))
            .andExpect(
                jsonPath("$.location.coords.dms.y.seconds",
                    is(equalTo(scafellPikeFellDTO.getLocation().getDmsCoords().get(1).getSeconds()))))
            .andExpect(
                jsonPath("$.location.coords.dms.x.degrees",
                    is(equalTo(scafellPikeFellDTO.getLocation().getDmsCoords().get(0).getDegrees()))))
            .andExpect(
                jsonPath("$.location.coords.dms.x.minutes",
                    is(equalTo(scafellPikeFellDTO.getLocation().getDmsCoords().get(0).getMinutes()))))
            .andExpect(
                jsonPath("$.location.coords.dms.x.seconds",
                    is(equalTo(scafellPikeFellDTO.getLocation().getDmsCoords().get(0).getSeconds()))));
    }

    @Test
    public void given_fell_exists_when_sending_get_request_for_fell_by_id_then_response_will_contain_its_formatted_dms_coord() throws Exception {
        mockMvc.perform(
            get("/fells/{id}", scafellPikeFellEntity.getId()))
            .andExpect(
                status().isOk())
            .andExpect(
                content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                jsonPath("$.location.coords.dms.y.formatted",
                    is(equalTo(scafellPikeFellDTO.getLocation().getDmsCoords().get(1).getFormatted()))))
            .andExpect(
                jsonPath("$.location.coords.dms.x.formatted",
                    is(equalTo(scafellPikeFellDTO.getLocation().getDmsCoords().get(0).getFormatted()))));
    }

    @Test
    public void given_fell_exists_when_sending_get_request_for_fell_by_id_then_response_will_contain_its_region() throws Exception {
        mockMvc.perform(
            get("/fells/{id}", scafellPikeFellEntity.getId()))
            .andExpect(
                status().isOk())
            .andExpect(
                content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                jsonPath("$.location.region", is(equalTo("http://localhost:8080/api/regions/4"))));
    }

    @Test
    public void given_fell_exists_when_sending_get_request_for_fell_by_id_then_response_will_contain_its_classifications() throws Exception {
        mockMvc.perform(
            get("/fells/{id}", scafellPikeFellEntity.getId()))
            .andExpect(
                status().isOk())
            .andExpect(
                content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                jsonPath("$.classifications", containsInAnyOrder(
                    "http://localhost:8080/api/classifications/1",
                    "http://localhost:8080/api/classifications/2",
                    "http://localhost:8080/api/classifications/3",
                    "http://localhost:8080/api/classifications/4",
                    "http://localhost:8080/api/classifications/5",
                    "http://localhost:8080/api/classifications/7",
                    "http://localhost:8080/api/classifications/8",
                    "http://localhost:8080/api/classifications/9",
                    "http://localhost:8080/api/classifications/10",
                    "http://localhost:8080/api/classifications/11",
                    "http://localhost:8080/api/classifications/12",
                    "http://localhost:8080/api/classifications/13",
                    "http://localhost:8080/api/classifications/14",
                    "http://localhost:8080/api/classifications/15",
                    "http://localhost:8080/api/classifications/16")));
    }

    @Test
    public void given_fell_exists_when_sending_get_request_for_fell_by_id_then_response_will_contain_its_os_map_ref() throws Exception {
        mockMvc.perform(
            get("/fells/{id}", scafellPikeFellEntity.getId()))
            .andExpect(
                status().isOk())
            .andExpect(
                content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                jsonPath("$.location.os_map_ref", is(equalTo(scafellPikeFellDTO.getLocation().getOsMapRef()))));
    }

    @Test
    public void given_fell_exists_when_sending_get_request_for_fell_by_id_then_response_will_contain_its_os_maps() throws Exception {
        mockMvc.perform(
            get("/fells/{id}", scafellPikeFellEntity.getId()))
            .andExpect(
                status().isOk())
            .andExpect(
                content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.location.os_maps", containsInAnyOrder(
                "http://localhost:8080/api/maps/1",
                "http://localhost:8080/api/maps/2",
                "http://localhost:8080/api/maps/7")));
    }

    /*******************************************************************************************************************
     *
     * Negative tests with valid input
     *
     ******************************************************************************************************************/
    @Test
    public void given_fell_does_not_exist_when_sending_get_request_for_fell_by_id_then_404_status_will_be_returned() throws Exception {
        int invalidFellId = -1;
        when(fellRepository.findById(invalidFellId))
            .thenReturn(Optional.empty());
        mockMvc.perform(
            get("/fells/{id}", invalidFellId))
            .andExpect(
                status().is(404))
            .andExpect(
                content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void given_fell_does_not_exist_when_sending_get_request_for_fell_by_id_then_response_will_contain_error_status() throws Exception {
        int invalidFellId = -1;
        when(fellRepository.findById(invalidFellId))
            .thenReturn(Optional.empty());
        mockMvc.perform(
            get("/fells/{id}", invalidFellId))
            .andExpect(
                jsonPath("$.status", is(equalTo("NOT_FOUND"))))
            .andExpect(
                content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void given_fell_does_not_exist_when_sending_get_request_for_fell_by_id_then_response_will_contain_error_message() throws Exception {
        int invalidFellId = -1;
        when(fellRepository.findById(invalidFellId))
            .thenReturn(Optional.empty());
        mockMvc.perform(
            get("/fells/{id}", invalidFellId))
            .andExpect(
                jsonPath("$.message", is(equalTo(String.format("Fell was not found for {id=%d}", invalidFellId)))))
            .andExpect(
                content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void given_fell_does_not_exist_when_sending_get_request_for_fell_by_id_then_response_will_contain_path() throws Exception {
        int invalidFellId = -1;
        when(fellRepository.findById(invalidFellId))
            .thenReturn(Optional.empty());
        mockMvc.perform(
            get("/fells/{id}", invalidFellId))
            .andExpect(
                jsonPath("$.path", is(equalTo(String.format("/fells/%d", invalidFellId)))))
            .andExpect(
                content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void given_fell_does_not_exist_when_sending_get_request_for_fell_by_id_then_response_will_contain_timestamp() throws Exception {
        int invalidFellId = -1;
        when(fellRepository.findById(invalidFellId))
            .thenReturn(Optional.empty());
        String NOW = "25-03-2017 13:54:02";
        when(apiClock.now())
            .thenReturn(NOW);
        mockMvc.perform(
            get("/fells/{id}", invalidFellId))
            .andExpect(
                jsonPath("$.timestamp", is(equalTo(NOW))))
            .andExpect(
                content().contentType(MediaType.APPLICATION_JSON));
    }

    /*******************************************************************************************************************
     *
     * Security, authorization and permission tests
     *
     ******************************************************************************************************************/
    @Test
    public void given_endpoint_does_not_support_post_method_when_sending_post_request_to_url_then_405_status_will_be_returned() throws Exception {
        mockMvc.perform(post("/fells/3"))
            .andExpect(status().isMethodNotAllowed())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void given_endpoint_does_not_support_post_method_when_sending_post_request_to_url_then_response_will_contain_error_status() throws Exception {
        mockMvc.perform(post("/fells/3"))
            .andExpect(jsonPath("$.status", equalTo("METHOD_NOT_ALLOWED")))
            .andExpect(
                content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void given_endpoint_does_not_support_post_method_when_sending_post_request_to_url_then_response_will_contain_error_message() throws Exception {
        mockMvc.perform(post("/fells/3"))
            .andExpect(jsonPath("$.message", equalTo("Method 'POST' is not allowed")))
            .andExpect(
                content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void given_endpoint_does_not_support_post_method_when_sending_post_request_to_url_then_response_will_contain_error_path() throws Exception {
        mockMvc.perform(post("/fells/3"))
            .andExpect(jsonPath("$.path", equalTo("/fells/3")))
            .andExpect(
                content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void given_endpoint_does_not_support_post_method_when_sending_post_request_to_url_then_response_will_contain_error_timestamp() throws Exception {
        String NOW = "25-03-2016 13:54:06";
        when(apiClock.now())
            .thenReturn(NOW);
        mockMvc.perform(post("/fells/3"))
            .andExpect(jsonPath("$.timestamp", is(equalTo(NOW))))
            .andExpect(
                content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void given_endpoint_does_not_support_put_method_when_sending_put_request_to_url_then_405_status_will_be_returned() throws Exception {
        mockMvc.perform(put("/fells/3"))
            .andExpect(status().isMethodNotAllowed())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void given_endpoint_does_not_support_put_method_when_sending_put_request_to_url_then_response_will_contain_error_status() throws Exception {
        mockMvc.perform(put("/fells/3"))
            .andExpect(jsonPath("$.status", equalTo("METHOD_NOT_ALLOWED")))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void given_endpoint_does_not_support_put_method_when_sending_put_request_to_url_then_response_will_contain_error_message() throws Exception {
        mockMvc.perform(put("/fells/3"))
            .andExpect(jsonPath("$.message", equalTo("Method 'PUT' is not allowed")))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void given_endpoint_does_not_support_put_method_when_sending_put_request_to_url_then_response_will_contain_error_path() throws Exception {
        mockMvc.perform(put("/fells/3"))
            .andExpect(jsonPath("$.path", equalTo("/fells/3")))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void given_endpoint_does_not_support_put_method_when_sending_put_request_to_url_then_response_will_contain_error_timestamp() throws Exception {
        String NOW = "25-03-2016 13:54:06";
        when(apiClock.now())
            .thenReturn(NOW);
        mockMvc.perform(put("/fells/3"))
            .andExpect(jsonPath("$.timestamp", is(equalTo(NOW))))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void given_endpoint_does_not_support_patch_method_when_sending_patch_request_to_url_then_405_status_will_be_returned() throws Exception {
        mockMvc.perform(patch("/fells/3"))
            .andExpect(status().isMethodNotAllowed())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void given_endpoint_does_not_support_patch_method_when_sending_patch_request_to_url_then_response_will_contain_error_status() throws Exception {
        mockMvc.perform(patch("/fells/3"))
            .andExpect(jsonPath("$.status", equalTo("METHOD_NOT_ALLOWED")))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void given_endpoint_does_not_support_patch_method_when_sending_patch_request_to_url_then_response_will_contain_error_message() throws Exception {
        mockMvc.perform(patch("/fells/3"))
            .andExpect(jsonPath("$.message", equalTo("Method 'PATCH' is not allowed")))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void given_endpoint_does_not_support_patch_method_when_sending_patch_request_to_url_then_response_will_contain_error_path() throws Exception {
        mockMvc.perform(patch("/fells/3"))
            .andExpect(jsonPath("$.path", equalTo("/fells/3")))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void given_endpoint_does_not_support_patch_method_when_sending_patch_request_to_url_then_response_will_contain_error_timestamp() throws Exception {
        String NOW = "25-03-2016 13:54:06";
        when(apiClock.now())
            .thenReturn(NOW);
        mockMvc.perform(patch("/fells/3"))
            .andExpect(jsonPath("$.timestamp", is(equalTo(NOW))))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void given_endpoint_does_not_support_delete_method_when_sending_delete_request_to_url_then_405_status_will_be_returned() throws Exception {
        mockMvc.perform(delete("/fells/3"))
            .andExpect(status().isMethodNotAllowed())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void given_endpoint_does_not_support_delete_method_when_sending_delete_request_to_url_then_response_will_contain_error_status() throws Exception {
        mockMvc.perform(delete("/fells/3"))
            .andExpect(jsonPath("$.status", equalTo("METHOD_NOT_ALLOWED")))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void given_endpoint_does_not_support_delete_method_when_sending_delete_request_to_url_then_response_will_contain_error_message() throws Exception {
        mockMvc.perform(delete("/fells/3"))
            .andExpect(jsonPath("$.message", equalTo("Method 'DELETE' is not allowed")))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void given_endpoint_does_not_support_delete_method_when_sending_delete_request_to_url_then_response_will_contain_error_path() throws Exception {
        mockMvc.perform(delete("/fells/3"))
            .andExpect(jsonPath("$.path", equalTo("/fells/3")))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void given_endpoint_does_not_support_delete_method_when_sending_delete_request_to_url_then_response_will_contain_error_timestamp() throws Exception {
        String NOW = "25-03-2016 13:54:06";
        when(apiClock.now())
            .thenReturn(NOW);
        mockMvc.perform(delete("/fells/3"))
            .andExpect(jsonPath("$.timestamp", is(equalTo(NOW))))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
