package com.iainhemstock.lakedistrictapi.stepdefinitions.GetAllFellsStepDefs;

import com.iainhemstock.lakedistrictapi.attributes.PagedCollectionAttributes;
import com.iainhemstock.lakedistrictapi.common.CommonTestState;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.Matchers.is;
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
}
