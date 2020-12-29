package com.iainhemstock.lakedistrictapi.services;

import com.iainhemstock.lakedistrictapi.application_logic.LinkServiceImpl;
import com.iainhemstock.lakedistrictapi.domain.SimpleFell;
import com.iainhemstock.lakedistrictapi.entities.fells.HelvellynFellEntity;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_configuration.ApiProperties;
import com.iainhemstock.lakedistrictapi.config.TestApiProperties;
import com.iainhemstock.lakedistrictapi.domain.Link;
import com.iainhemstock.lakedistrictapi.domain.LinkRel;
import com.iainhemstock.lakedistrictapi.domain.Links;
import com.iainhemstock.lakedistrictapi.entities.fells.GreatGableFellEntity;
import com.iainhemstock.lakedistrictapi.entities.fells.ScafellPikeFellEntity;
import com.iainhemstock.lakedistrictapi.application_interfaces.LinkService;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.repository.SpringPageRepoPage;
import com.iainhemstock.lakedistrictapi.repository_interfaces.RepoPage;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

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
        RepoPage<SimpleFell> repoPage = SpringPageRepoPage.empty();
        Links expectedLinks = Links.empty();
        Links actualLinks = linkService.buildNavLinksForPageAndCollectionType(repoPage, "fells");
        assertThat(actualLinks, is(equalTo(expectedLinks)));
    }

    @Test
    @Parameters(method = "parameters")
    public void given_repo_result_when_building_nav_links_then_correct_links_are_built(final List<SimpleFell> items,
                                                                                                    final int offset,
                                                                                                    final int limit,
                                                                                                    final Links expectedLinks) {
        Page<SimpleFell> fellPage = new PageImpl<>(
            items,
            PageRequest.of(offset, limit),
            items.size());

        RepoPage<SimpleFell> repoPage = SpringPageRepoPage.from(fellPage);

        Links actualLinks = linkService.buildNavLinksForPageAndCollectionType(repoPage, "fells");

        assertThat(actualLinks, is(equalTo(expectedLinks)));
    }

    private Object[] parameters() {
        List<SimpleFell> items = List.of(
            new SimpleFell(new GreatGableFellEntity().getName(), new GreatGableFellEntity().getRegionEntity().getRegionName(), new Links(new Link(LinkRel.SELF, "http://localhost:8080/api/v1/fells/" + new GreatGableFellEntity().getOsMapRef().toString()))),
            new SimpleFell(new HelvellynFellEntity().getName(), new HelvellynFellEntity().getRegionEntity().getRegionName(), new Links(new Link(LinkRel.SELF, "http://localhost:8080/api/v1/fells/" + new HelvellynFellEntity().getOsMapRef().toString()))),
            new SimpleFell(new ScafellPikeFellEntity().getName(), new ScafellPikeFellEntity().getRegionEntity().getRegionName(), new Links(new Link(LinkRel.SELF, "http://localhost:8080/api/v1/fells/" + new ScafellPikeFellEntity().getOsMapRef().toString()))));
        return new Object[] {
            new Object[] {items, 0, 1, new Links(
                getLink(LinkRel.SELF, 0, 1),
                getLink(LinkRel.NEXT, 1, 1)) },
            new Object[] {items, 1, 1, new Links(
                getLink(LinkRel.PREV, 0, 1),
                getLink(LinkRel.SELF, 1, 1),
                getLink(LinkRel.NEXT, 2, 1)) },
            new Object[] {items, 2, 1, new Links(
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
