package com.iainhemstock.lakedistrictapi.legacy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iainhemstock.lakedistrictapi.controllers.FellSearchController;
import com.iainhemstock.lakedistrictapi.dtos.*;
import com.iainhemstock.lakedistrictapi.entities.FellEntity;
import com.iainhemstock.lakedistrictapi.entities.fells.FleetwithPikeFellEntity;
import com.iainhemstock.lakedistrictapi.entities.fells.ScafellFellEntity;
import com.iainhemstock.lakedistrictapi.entities.fells.ScafellPikeFellEntity;
import com.iainhemstock.lakedistrictapi.repositories.FellRepository;
import com.iainhemstock.lakedistrictapi.services.FellDTOMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(FellSearchController.class)
public class FellSearchControllerApiTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @Autowired private WebApplicationContext webApplicationContext;

    @MockBean private FellRepository fellRepository;
    @MockBean private FellDTOMapper fellDTOMapper;

    @Value("${api.search-results.page-size}")
    private int MAX_RESULTS_PER_PAGE;

    @Test
    public void given_search_term_that_will_have_no_matches_when_sending_search_request_then_status_is_ok_and_content_type_is_json() throws Exception {
        System.out.println(MAX_RESULTS_PER_PAGE);
        whenFellRepositoryThenReturn(Collections.emptyList());
        mockMvc.perform(get("/fells?search=abc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        verifyFellRepositoryIsCalledOnlyOnce();
    }

    @Test
    public void given_search_term_that_will_have_no_matches_when_sending_search_request_then_response_will_contain_empty_results() throws Exception {
        whenFellRepositoryThenReturn(Collections.emptyList());
        mockMvc.perform(get("/fells?search=abc"))
                .andExpect(jsonPath("$.results", hasSize(0)));
        verifyFellRepositoryIsCalledOnlyOnce();
    }

    @Test
    public void given_search_term_that_will_have_no_matches_when_sending_search_request_then_result_count_will_be_zero() throws Exception {
        whenFellRepositoryThenReturn(Collections.emptyList());
        mockMvc.perform(get("/fells?search=abc"))
            .andExpect(jsonPath("$.count", is(0)));
        verifyFellRepositoryIsCalledOnlyOnce();
    }

    @Test
    public void given_search_term_that_will_have_no_matches_when_sending_search_request_then_previous_url_will_be_null() throws Exception {
        whenFellRepositoryThenReturn(Collections.emptyList());
        mockMvc.perform(get("/fells?search=abc"))
            .andExpect(jsonPath("$.previous", is(nullValue())));
        verifyFellRepositoryIsCalledOnlyOnce();
    }

    @Test
    public void given_search_term_that_will_have_no_matches_when_sending_search_request_then_next_url_will_be_null() throws Exception {
        whenFellRepositoryThenReturn(Collections.emptyList());
        mockMvc.perform(get("/fells?search=abc"))
            .andExpect(jsonPath("$.next", is(nullValue())));
        verifyFellRepositoryIsCalledOnlyOnce();
    }

    @Test
    public void given_exact_fell_name_when_sending_search_request_then_response_count_will_be_one() throws Exception {
        whenFellRepositoryThenReturn(Collections.singletonList(new ScafellPikeFellEntity()));
        Mockito.when(fellDTOMapper.createDTO(any()))
            .thenReturn(new ScafellPikeFellDTO());

        mockMvc.perform(get("/fells?search=scafell+pike"))
            .andExpect(jsonPath("$.count", is(1)));
        verifyFellRepositoryIsCalledOnlyOnce();
    }

    @Test
    public void given_exact_fell_name_when_sending_search_request_then_previous_uri_will_be_null() throws Exception {
        whenFellRepositoryThenReturn(Collections.singletonList(new ScafellPikeFellEntity()));
        mockMvc.perform(get("/fells?search=scafell+pike"))
            .andExpect(jsonPath("$.previous", is(nullValue())));
        verifyFellRepositoryIsCalledOnlyOnce();
    }

    @Test
    public void given_exact_fell_name_when_sending_search_request_then_next_uri_will_be_null() throws Exception {
        whenFellRepositoryThenReturn(Collections.singletonList(new ScafellPikeFellEntity()));
        mockMvc.perform(get("/fells?search=scafell+pike"))
            .andExpect(jsonPath("$.next", is(nullValue())));
        verifyFellRepositoryIsCalledOnlyOnce();
    }

    @Test
    public void given_exact_fell_name_when_sending_search_request_then_response_will_contain_a_single_result() throws Exception {
        whenFellRepositoryThenReturn(Collections.singletonList(new ScafellPikeFellEntity()));
        Mockito.when(fellDTOMapper.createDTO(new ScafellPikeFellEntity()))
            .thenReturn(new ScafellPikeFellDTO());

        SearchDTO searchDTO = mapResponseToDTO(mockMvc.perform(get("/fells?search=scafell+pike")));
        assertThat(searchDTO.getResults().size(), is(1));
        assertThat(searchDTO.getResults().get(0), is(equalTo(new ScafellPikeFellDTO())));
        verifyFellRepositoryIsCalledOnlyOnce();
    }

    @Test
    public void given_multiple_word_search_term_when_sending_search_request_then_controller_will_replace_plus_char_with_space() throws Exception {
        whenFellRepositoryThenReturn(Collections.singletonList(new ScafellPikeFellEntity()));
        mockMvc.perform(get("/fells?search=scafell+pike"));
        verify(fellRepository).findByNameLikeIgnoreCase("scafell pike");
        verifyFellRepositoryIsCalledOnlyOnce();
    }

    @Test
    public void given_search_term_prefixed_with_wildcard_when_sending_search_request_then_status_is_ok_and_content_type_is_json() throws Exception {
        whenFellRepositoryThenReturn(List.of(new ScafellPikeFellEntity(), new FleetwithPikeFellEntity()));
        mockMvc.perform(get("/fells?search=*pike"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void given_search_term_prefixed_with_wildcard_when_sending_search_request_then_response_will_contain_result_count() throws Exception {
        whenFellRepositoryThenReturn(List.of(new ScafellPikeFellEntity(), new FleetwithPikeFellEntity()));
        Mockito.when(fellDTOMapper.createDTO(any()))
            .thenReturn(new ScafellPikeFellDTO(), new FleetwithPikeFellDTO());

        mockMvc.perform(get("/fells?search=*pike"))
            .andExpect(jsonPath("$.count", is(2)));
        verifyFellRepositoryIsCalledOnlyOnce();
    }

    @Test
    public void given_search_term_prefixed_with_wildcard_when_sending_search_request_then_response_will_contain_matching_fells_whose_names_end_with_search_term() throws Exception {
        whenFellRepositoryThenReturn(List.of(new ScafellPikeFellEntity(), new FleetwithPikeFellEntity()));
        Mockito.when(fellDTOMapper.createDTO(any()))
            .thenReturn(new ScafellPikeFellDTO(), new FleetwithPikeFellDTO());

        SearchDTO searchDTO = mapResponseToDTO(mockMvc.perform(get("/fells?search=*pike")));

        assertThat(searchDTO.getResults(),
            containsInAnyOrder(new ScafellPikeFellDTO(), new FleetwithPikeFellDTO()));

        verifyFellRepositoryIsCalledOnlyOnce();
    }

    @Test
    public void given_search_term_prefixed_with_wildcard_when_sending_search_request_that_returns_less_than_a_page_of_results_then_previous_uri_will_be_null() throws Exception {
        whenFellRepositoryThenReturn(List.of(new ScafellPikeFellEntity(), new FleetwithPikeFellEntity()));
        mockMvc.perform(get("/fells?search=*pike"))
            .andExpect(jsonPath("$.count", lessThan(MAX_RESULTS_PER_PAGE)))
            .andExpect(jsonPath("$.previous", nullValue()));

        verifyFellRepositoryIsCalledOnlyOnce();
    }

    @Test
    public void given_search_term_prefixed_with_wildcard_when_sending_search_request_that_returns_less_than_a_page_of_results_then_next_uri_will_be_null() throws Exception {
        whenFellRepositoryThenReturn(List.of(new ScafellPikeFellEntity(), new FleetwithPikeFellEntity()));
        mockMvc.perform(get("/fells?search=*pike"))
            .andExpect(jsonPath("$.count", lessThan(MAX_RESULTS_PER_PAGE)))
            .andExpect(jsonPath("$.next", nullValue()));

        verifyFellRepositoryIsCalledOnlyOnce();
    }

    @Test
    public void given_search_term_suffixed_with_wildcard_when_sending_search_request_then_status_is_ok_and_content_type_is_json() throws Exception {
        whenFellRepositoryThenReturn(List.of(new ScafellPikeFellEntity(), new ScafellFellEntity()));
        mockMvc.perform(get("/fells?search=sca*"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void given_search_term_suffixed_with_wildcard_when_sending_search_request_then_response_will_contain_result_count() throws Exception {
        whenFellRepositoryThenReturn(List.of(new ScafellPikeFellEntity(), new ScafellFellEntity()));
        Mockito.when(fellDTOMapper.createDTO(any()))
            .thenReturn(new ScafellPikeFellDTO(), new ScafellFellDTO());

        mockMvc.perform(get("/fells?search=sca*"))
            .andExpect(jsonPath("$.count", is(2)));
        verifyFellRepositoryIsCalledOnlyOnce();
    }

    @Test
    public void given_search_term_suffixed_with_wildcard_when_sending_search_request_then_response_will_contain_matching_fells_whose_names_end_with_search_term() throws Exception {
        whenFellRepositoryThenReturn(List.of(new ScafellPikeFellEntity(), new ScafellFellEntity()));
        Mockito.when(fellDTOMapper.createDTO(any()))
            .thenReturn(new ScafellPikeFellDTO(), new ScafellFellDTO());

        SearchDTO searchDTO = mapResponseToDTO(mockMvc.perform(get("/fells?search=sca*")));

        assertThat(searchDTO.getResults(),
            containsInAnyOrder(new ScafellPikeFellDTO(), new ScafellFellDTO()));

        verifyFellRepositoryIsCalledOnlyOnce();
    }

    @Test
    public void given_search_term_suffixed_with_wildcard_when_sending_search_request_that_returns_less_than_a_page_of_results_then_previous_uri_will_be_null() throws Exception {
        whenFellRepositoryThenReturn(List.of(new ScafellPikeFellEntity(), new ScafellFellEntity()));
        mockMvc.perform(get("/fells?search=sca*"))
            .andExpect(jsonPath("$.count", lessThan(MAX_RESULTS_PER_PAGE)))
            .andExpect(jsonPath("$.previous", nullValue()));

        verifyFellRepositoryIsCalledOnlyOnce();
    }

    @Test
    public void given_search_term_suffixed_with_wildcard_when_sending_search_request_that_returns_less_than_a_page_of_results_then_next_uri_will_be_null() throws Exception {
        whenFellRepositoryThenReturn(List.of(new ScafellPikeFellEntity(), new ScafellFellEntity()));
        mockMvc.perform(get("/fells?search=sca*"))
            .andExpect(jsonPath("$.count", lessThan(MAX_RESULTS_PER_PAGE)))
            .andExpect(jsonPath("$.next", nullValue()));

        verifyFellRepositoryIsCalledOnlyOnce();
    }

    @Test
    public void given_search_term_is_blank_when_sending_search_request_then_zero_results_are_returned() throws Exception {
        mockMvc.perform(get("/fells?search="))
            .andExpect(jsonPath("$.count", is(0)))
            .andExpect(jsonPath("$.previous", is(nullValue())))
            .andExpect(jsonPath("$.next", is(nullValue())))
            .andExpect(jsonPath("$.results", hasSize(0)));
    }

    private void whenFellRepositoryThenReturn(final List<FellEntity> fellEntities) {
        Mockito.when(fellRepository.findByNameLikeIgnoreCase(anyString()))
            .thenReturn(fellEntities);
    }

    private void verifyFellRepositoryIsCalledOnlyOnce() {
        Mockito.verify(fellRepository, times(1)).findByNameLikeIgnoreCase(anyString());
    }

    private SearchDTO mapResponseToDTO(final ResultActions resultActions) throws java.io.IOException {
        return objectMapper.readValue(
            resultActions.andReturn().getResponse().getContentAsByteArray(),
            SearchDTO.class);
    }

}
