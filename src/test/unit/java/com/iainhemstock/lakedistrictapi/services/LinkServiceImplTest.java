package com.iainhemstock.lakedistrictapi.services;

import com.iainhemstock.lakedistrictapi.entities.fells.HelvellynFellEntity;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_configuration.ApiProperties;
import com.iainhemstock.lakedistrictapi.config.TestApiProperties;
import com.iainhemstock.lakedistrictapi.domain.Link;
import com.iainhemstock.lakedistrictapi.domain.LinkRel;
import com.iainhemstock.lakedistrictapi.domain.Links;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.FellEntity;
import com.iainhemstock.lakedistrictapi.entities.fells.GreatGableFellEntity;
import com.iainhemstock.lakedistrictapi.entities.fells.ScafellPikeFellEntity;
import com.iainhemstock.lakedistrictapi.application_interfaces.LinkService;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class LinkServiceImplTest {

    private ApiProperties apiProperties;
    private LinkService linkService;

    @Before
    public void setUp() {
        apiProperties = new TestApiProperties();
        linkService = new LinkServiceImpl(apiProperties);
    }

    @Test
    public void will_generate_link_for_resource_with_id() {
        assertThat(linkService.buildForResourceWithIdAndRel("fells", "NY123456", LinkRel.SELF),
            is(equalTo(new Link(LinkRel.SELF, apiProperties.getBaseUrl() + "/fells/NY123456"))));
    }

    @Test
    public void will_throw_when_attempting_to_generate_link_for_null_resource() {
        try {
            linkService.buildForResourceWithIdAndRel(null, "NY123456", LinkRel.SELF);
            fail("Expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Argument 'resource' cannot be null")));
        }
    }

    @Test
    public void will_throw_when_attempting_to_generate_link_with_invalid_resource_id() {
        try {
            linkService.buildForResourceWithIdAndRel("resource", "", LinkRel.SELF);
            fail("Expected method under test to throw IllegalArgumentException but it didn't");
        }
        catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), is(equalTo("Argument 'resourceId' cannot be blank")));
        }
    }

    @Test
    public void given_no_source_links_then_no_links_will_be_built() {
        Page page = new PageImpl(Collections.EMPTY_LIST, Pageable.unpaged(), 0);
        Links expectedLinks = Links.empty();
        Links actualLinks = linkService.buildNavLinksForPageAndCollectionType(page, "fells");
        assertThat(actualLinks, is(equalTo(expectedLinks)));
    }

    @Test
    @Parameters(method = "parameters")
    public void given_page_of_fells_when_building_nav_links_then_correct_links_are_built(final List<FellEntity> fellsList,
                                                                                                    final int offset,
                                                                                                    final int limit,
                                                                                                    final Links expectedLinks) {
        Page<FellEntity> page = new PageImpl<>(
            fellsList,
            PageRequest.of(offset, limit),
            fellsList.size());

        Links actualLinks = linkService.buildNavLinksForPageAndCollectionType(page, "fells");

        assertThat(actualLinks, is(equalTo(expectedLinks)));
    }

    private Object[] parameters() {
        List<FellEntity> fellsList = List.of(new GreatGableFellEntity(), new HelvellynFellEntity(), new ScafellPikeFellEntity());
        return new Object[] {
            new Object[] {fellsList, 0, 1, new Links(
                getLink(LinkRel.SELF, 0, 1),
                getLink(LinkRel.NEXT, 1, 1)) },
            new Object[] {fellsList, 1, 1, new Links(
                getLink(LinkRel.PREV, 0, 1),
                getLink(LinkRel.SELF, 1, 1),
                getLink(LinkRel.NEXT, 2, 1)) },
            new Object[] {fellsList, 2, 1, new Links(
                getLink(LinkRel.PREV, 1, 1),
                getLink(LinkRel.SELF, 2, 1)) }
        };
    }

    private Link getLink(final LinkRel rel, final int offset, final int limit) {
        return new Link(rel, getHref(offset, limit));
    }

    private String getHref(final int offset, final int limit) {
        return String.format("%s/fells?offset=%d&limit=%d", new TestApiProperties().getBaseUrl(), offset, limit);
    }
}
