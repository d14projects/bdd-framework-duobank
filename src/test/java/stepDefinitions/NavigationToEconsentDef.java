package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.SoftAssertions;
import org.checkerframework.checker.units.qual.N;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import pages.ApplicationPage;
import pages.DashboardPage;
import pages.NavigationToEconsentPage;
import utilities.ConfigReader;
import utilities.Driver;

import java.util.Map;

import static org.junit.Assert.assertTrue;

public class NavigationToEconsentDef {

    @Given("I am logging to the Duobank")
    public void i_am_logging_to_the_duobank() {
        Driver.getDriver().get(ConfigReader.getProperty("url"));
        new DashboardPage().login();
    }

    @When("i am clicking to the Mortgage Application")
    public void i_am_clicking_to_the_mortgage_application() {
        new DashboardPage().clickMortgageApplicationLink();
    }

    @Then("all information in preapproval details should be filled")
    public void all_information_in_preapproval_details_should_be_filled() {
        new ApplicationPage().fillOutForm();
    }

   @And("info in Personal Info page is filled as following")
    public void infoInPersonalInfoPageIsFilled(Map<String, String > map) throws InterruptedException {
   new NavigationToEconsentPage().personalInfo(map);
    }

    @And("all info in expenses page is filled")
    public void all_info_in_expenses_page_is_filled() throws InterruptedException {
        new NavigationToEconsentPage().expenses();
    }

    @And("all info in Employment and income page filled")
    public void all_info_in_employment_and_income_page_filled() {
        new NavigationToEconsentPage().employment();
    }

    @And("all info in Credit Report page filled")
    public void all_info_in_credit_report_page_filled() {
        new NavigationToEconsentPage().creditReport();
    }

    @When("i do not fill first, last name and email")
    public void iDoNotFillFirstLastNameAndEmail() {
        new NavigationToEconsentPage().submitFormWithoutRequiredFields();
    }

    @Then("i should see an error message")
    public void iShouldSeeAnErrorMessage() {
        boolean isErrorDisplayed = new NavigationToEconsentPage().isErrorDisplayedForRequiredFields();
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(isErrorDisplayed).isTrue();
        System.out.println("Error message for missing required fields is not displayed as expected.");
        softAssertions.assertAll();
    }
    @When("i enter invalid email{string}")
    public void iEnterInvalidEmail(String arg0) {
        new NavigationToEconsentPage().submitFormWithInvalidEmail(arg0);
    }

   /* @When("i enter invalid email")
    public void iEnterInvalidEmail() {
        new NavigationToEconsentPage().submitFormWithInvalidEmail();
    }*/

    @Then("i should see an error message for email")
    public void iShouldSeeAnErrorMessageForEmail() {
        boolean isErrorDisplayed2 = new NavigationToEconsentPage().isErrorDisplayedForEmail();
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(isErrorDisplayed2).isTrue();
        System.out.println("Error message for email field is not displayed as expected.");
        softAssertions.assertAll();
    }

    @And("i should be able to enter my name and correct email")
    public void iShouldBeAbleToEnterMyNameAndCorrectEmail() {
        new NavigationToEconsentPage().econsentInfoEnter();
    }

    @And("i should see clear text for disclosure")
    public void iShouldSeeClearTextForDisclosure() {
        boolean isDisclosureDisplayed = new NavigationToEconsentPage().isDisclosureDisplayed();
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(isDisclosureDisplayed).isTrue();
        System.out.println("Disclosure is not displayed as expected.");
        softAssertions.assertAll();

    }

    @And("i should see {string} and {string} buttons")
    public void iShouldSeeAndButtons(String arg0, String arg1) {
        boolean isAgreeRadioDisplayed = new NavigationToEconsentPage().isAgreeRadioButtonDisplayed();
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(isAgreeRadioDisplayed).isTrue();
        System.out.println("Agree radio button is not displayed as expected.");
        boolean isDisagreeRadioDisplayed = new NavigationToEconsentPage().isDisagreeRadioButtonDisplayed();
        softAssertions.assertThat(isDisagreeRadioDisplayed).isTrue();
        System.out.println("Disagree radio button is not displayed as expected.");
        softAssertions.assertAll();
    }

  /*  @And("{string} button should be selected by default")
    public void buttonShouldBeSelectedByDefault(String arg0) {
        boolean isAgreeRadioSelected = new NavigationToEconsentPage().isAgreeButtonSelected();
        softAssert.assertTrue(isAgreeRadioSelected, "Agree radio button is not selected");

    }*/

    @When("none of the buttons clicked")
    public void noneOfTheButtonsClicked() {
        new NavigationToEconsentPage().clickDissagreeButton();
        Alert alert = Driver.getDriver().switchTo().alert();
        alert.dismiss();
    }

    @Then("user cant submit")
    public void userCantSubmit() {
        boolean isNextButtonUnabled = new NavigationToEconsentPage().isNextButtonUnabled();
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(isNextButtonUnabled).isTrue();
        System.out.println("Next button is not disabled");
        softAssertions.assertAll();
    }

    @And("error message should be displayed")
    public void errorMessageShouldBeDisplayed() {
        boolean isErrorDisplayed = new NavigationToEconsentPage().isErrorMessageDisplayed();
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(isErrorDisplayed).isTrue();
        System.out.println("Error message is not displayed");
    }


    @When("user stays with on {string} button")
    public void userStaysWithOnButton(String arg0) {
        new NavigationToEconsentPage().clickAgree();

    }

    @Then("he is able to proceed to the next page")
    public void heIsAbleToProceedToTheNextPage() {
        new NavigationToEconsentPage().clickNext();
    }

    @When("user click on {string} button")
    public void userShouldBeAbleToClickButton(String arg0) {
        new NavigationToEconsentPage().clickDissagreeButton2();
    }

    @Then("alert message should pop up")
    public void alertMessageShouldPopUp() {
        Alert alert = Driver.getDriver().switchTo().alert();
        alert.accept();
    }

    @And("User will be redirected to mane page")
    public void userWillBeRedirectedToManePage() {
        new NavigationToEconsentPage().amIOnManePage();
    }



}

