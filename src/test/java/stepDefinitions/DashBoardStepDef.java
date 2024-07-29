package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.ApplicationPage;
import pages.DashboardPage;
import utilities.ConfigReader;
import utilities.Driver;

import static org.junit.Assert.assertTrue;

public class DashBoardStepDef {
    @Given("I am logged into the application")
    public void i_am_logged_into_the_application() {
        Driver.getDriver().get(ConfigReader.getProperty("url"));

    }
    @When("I navigate to the dashboard page")
    public void i_navigate_to_the_dashboard_page() {
        new DashboardPage().login();
    }

    @Then("I should see the bank's logo in the top left corner of the page")
    public void i_should_see_the_bank_s_logo_in_the_top_left_corner_of_the_page() {
        assertTrue("Bank logo is not displayed", new DashboardPage().isLogoDisplayed());
    }

    @Then("I should see a link labeled {string}")
    public void iShouldSeeALinkLabeled(String linkText) {
        switch (linkText) {
            case "Mortgage Application":
                assertTrue("Mortgage Application link is not displayed", new DashboardPage().isMortgageApplicationLinkDisplayed());
                break;
            case "Application List":
                assertTrue("Application List link is not displayed", new DashboardPage().isApplicationListLinkDisplayed());
                break;
            default:
                throw new IllegalArgumentException("Unexpected link text: " + linkText);
        }
    }

    @When("I click on the {string} link")
    public void iClickOnTheLink(String arg0) {
        new DashboardPage().clickMortgageApplicationLink();
    }
    @Then("I should be taken to the Mortgage Application page")
    public void iShouldBeTakenToTheMortgageApplicationPage() {
        assertTrue("Not on the Mortgage Application page", new DashboardPage().isOnMortgageApplicationPage());

    }
    @When("I click on the next {string} link")
    public void iClickOnTheNextLink(String arg0) {
            new DashboardPage().clickApplicationListLink();
    }

    @Then("I should be taken to the Application List page")
    public void iShouldBeTakenToTheApplicationListPage() {
        assertTrue("Not on the Application List page", new DashboardPage().isOnApplicationListPage());
    }

    @Then("I should see my account information including my name and profile picture in the top right corner of the page")
    public void iShouldSeeMyAccountInformationIncludingMyNameAndProfilePictureInTheTopRightCornerOfThePage() {
        assertTrue("Name and profile picture are not dispayed", new DashboardPage().isaAccountInfoDisplayed());
    }

    @When("i  click on the profile picture")
    public void iClickOnTheProfilePicture() {
     new DashboardPage().profilePictureClick();
    }

    @Then("i should see logout button and click on it")
    public void iShouldSeeLogoutButton() {
        new DashboardPage().logout();

    }
    @And("i should be able to logout")
    public void iShouldBeAbleToLogout() {
        assertTrue(new DashboardPage().amILoggedOut());
    }
}
