package org.nsw.gov.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.nsw.gov.framework.BaseClass;
import org.nsw.gov.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;


public class NSWAssessmentTask1 extends BaseClass {
    public MediaRelease mediaRelease;
    public NewPage newPage;
    @Override
    @Before
    public void setup() throws Exception{
        System.out.println("TC TEST");
        super.setup();
        System.out.println("Setup Done");
    }
    @Given("I am on the media release page")
    public void i_am_on_the_media_release_page() throws Exception{
        //Launch the application
        mediaRelease =  commonMethods.launchSFApplication();
    }

    @Override
    @After
    public void tearDown(){
        System.out.println(getDriver());
        super.tearDown(); //
    }


    @When("I click on the {string}")
    public void i_click_on_the_ministers_name(String ministerName) throws InterruptedException {
        mediaRelease.clickOnMinisterCheckboxes(ministerName);
    }
    @When("Apply the filter")
    public void apply_the_filter() {
        try {
            mediaRelease.clickOnApplyFilter();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Then("Select one of the media card to check if it is pertaining to the corresponding minister")
    public void select_one_of_the_media_card_to_check_if_it_is_pertaining_to_the_corresponding_minister() throws InterruptedException {
        newPage = mediaRelease.openTheArticleForCorrespondingMinister();
        boolean ActualMinisterName = newPage.validateTheMinisterNameOnArticleMatchesWithFilterApplied("Treasurer");
        Assert.assertTrue(ActualMinisterName, "The Minster Name Does Not Match");
        System.out.println(getDriver());
    }

    @Then("I can valid the minister name in the Url {string}")
    public void i_can_valid_the_minister_name_in_the_url(String ministerNames) throws UnsupportedEncodingException {
        String currentUrl = getDriver().getCurrentUrl();
        String decodedUrl = URLDecoder.decode(currentUrl, StandardCharsets.UTF_8.name());
        System.out.println("the decoded value of Url: " +decodedUrl);
        String ministerName = decodedUrl.substring(decodedUrl.indexOf("ministers=") + 10).replace("%20", " ").trim();
        System.out.println("the minsiters name in the URL: " +ministerName);
        Assert.assertEquals(ministerName, ministerNames, "The extracted minister's name does not match the expected name.");
    }

    @Then("I can valid the minster name is not present in the Url and the Url matches with the original url")
    public void iCanValidTheMinsterNameIsNotPresentInTheUrlAndTheUrlMatchesWithTheOriginalUrl() throws InterruptedException {
        String totalPagesOnMediaReleasePageAfterApplyingFilter = mediaRelease.totalPagesOnMediaRelease();
        int aggregatePagesOnMediaReleasePageAfterApplyingFilter= Integer.parseInt(totalPagesOnMediaReleasePageAfterApplyingFilter);
        System.out.println("The total page when fitler is applied: "+aggregatePagesOnMediaReleasePageAfterApplyingFilter);
        mediaRelease.clickOnClearAllFilterButton();
        mediaRelease.scrollToTotalPagesOnMediaRelease();

        String totalPagesOnMediaReleasePage=mediaRelease.totalPagesOnMediaRelease();
        int aggregatePagesOnMediaRelease = Integer.parseInt(totalPagesOnMediaReleasePage);
        System.out.println("The total count of the pages when fitler is cleared: "+aggregatePagesOnMediaRelease);
        Assert.assertTrue((aggregatePagesOnMediaReleasePageAfterApplyingFilter < aggregatePagesOnMediaRelease), "The Filter applied has more pages");

        String currentUrl = getDriver().getCurrentUrl();
        String updatedCurrentUrl = currentUrl.replaceAll("[?,]", "");
        System.out.println("The updated Url after removing ?: "+updatedCurrentUrl);
        //If the Filter is actually removed then I am verifying this by fetching
        //the Url are removing the "?" at the end of the Url and comparing it with the media-release url
        Assert.assertEquals(updatedCurrentUrl, "https://www.nsw.gov.au/media-releases");
    }
}
