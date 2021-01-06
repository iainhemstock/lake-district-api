package com.iainhemstock.lakedistrictapi.stepdefinitions.GetAllFellsStepDefs;

import com.iainhemstock.lakedistrictapi.attributes.LinksAttributes;
import com.iainhemstock.lakedistrictapi.attributes.PagedCollectionAttributes;
import com.iainhemstock.lakedistrictapi.common.CommonTestState;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class LinksStepDefs {

    @Autowired
    private CommonTestState commonState;

    @Then("^the body will contain the following (.*), (.*) and (.*) links$")
    public void pageWillContainFirstPrevCurrentNextAndLastLinks(final String expectedPrevLink,
                                                                final String expectedSelfLink,
                                                                final String expectedNextLink) throws Exception {

        if (expectedPrevLink.isBlank()) assertLinkDoesNotExistInResponse(PagedCollectionAttributes.PREV_HREF);
        else assertLinkDoesExistInResponse(PagedCollectionAttributes.PREV_HREF, expectedPrevLink);

        if (expectedSelfLink.isBlank()) assertLinkDoesNotExistInResponse(PagedCollectionAttributes.SELF_HREF);
        else assertLinkDoesExistInResponse(PagedCollectionAttributes.SELF_HREF, expectedSelfLink);

        if (expectedNextLink.isBlank()) assertLinkDoesNotExistInResponse(PagedCollectionAttributes.NEXT_HREF);
        else assertLinkDoesExistInResponse(PagedCollectionAttributes.NEXT_HREF, expectedNextLink);
    }

    private void assertLinkDoesNotExistInResponse(final PagedCollectionAttributes pageObjectLink) throws Exception {
        commonState.getResult().andExpect(jsonPath(pageObjectLink.value()).doesNotExist());
    }

    private void assertLinkDoesExistInResponse(final PagedCollectionAttributes pageObjectLink, final String expectedLink) throws Exception {
        commonState.getResult()
            .andExpect(jsonPath(pageObjectLink.value()).exists());
        commonState.getResult()
            .andExpect(jsonPath(pageObjectLink.value(), is(expectedLink)));
    }

    @Then("^the (prev|self|next) href will contain an offset (.*)$")
    public void theHrefWillContainAnOffset(final String rel, final String expectedOffset) throws Exception {
        switch (rel) {
            case "prev":
                commonState.getResult().andExpect(jsonPath("$.links.prev.href", containsString(expectedOffset)));
                break;
            case "self":
                commonState.getResult().andExpect(jsonPath("$.links.self.href", containsString(expectedOffset)));
                break;
            case "next":
                commonState.getResult().andExpect(jsonPath("$.links.next.href", containsString(expectedOffset)));
                break;
        }

    }

    @Then("a pref href will not exist")
    public void aPrefHrefWillNotExist() throws Exception {
        commonState.getResult().andExpect(jsonPath(LinksAttributes.PREV_HREF.value()).doesNotExist());
    }

    @Then("a next href will not exist")
    public void aNextHrefWillNotExist() throws Exception {
        commonState.getResult().andExpect(jsonPath(LinksAttributes.NEXT_HREF.value()).doesNotExist());
    }

    @And("^the self href will contain a limit (limit=([0-9]*))$")
    public void theSelfHrefWillContainAnLimitLimit(final String expectedLimit) throws Exception {
        commonState.getResult().andExpect(jsonPath(LinksAttributes.SELF_HREF.value(), containsString(expectedLimit)));
    }

    @And("^the next href will contain a limit (limit=([0-9]*))$")
    public void theNextHrefWillContainAnLimitLimit(final String expectedLimit) throws Exception {
        commonState.getResult().andExpect(jsonPath(LinksAttributes.NEXT_HREF.value(), containsString(expectedLimit)));
    }

    @Then("^the prev href will contain a limit (limit=([0-9]*)$)")
    public void thePrevHrefWillContainAnLimitLimit(final String expectedLimit) throws Exception {
        commonState.getResult().andExpect(jsonPath(LinksAttributes.NEXT_HREF.value(), containsString(expectedLimit)));
    }

}
