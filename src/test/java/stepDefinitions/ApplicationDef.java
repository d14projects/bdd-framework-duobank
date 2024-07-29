package stepDefinitions;

import com.github.javafaker.App;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.ApplicationPage;
import pages.DashboardPage;
import utilities.ConfigReader;
import utilities.Driver;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ApplicationDef {
    @Given("I am logged to the Duobank")
    public void i_am_logged_to_the_duobank() {
        Driver.getDriver().get(ConfigReader.getProperty("url"));
        new DashboardPage().login();
    }
    @Then("i clicked to the Mortgage Application")
    public void i_clicked_to_the_mortgage_application() {
        new DashboardPage().clickMortgageApplicationLink();

    }
    @Then("I should see a checkbox for {string} with options {string} and {string}")
    public void i_should_see_a_checkbox_for_with_options_and(String string, String string2, String string3) {
        assertTrue("Items are not displayed", new ApplicationPage().checkBoxForRealtor());
    }

    @When("I select {string} for the Realtor checkbox")
    public void i_select_for_the_checkbox(String string) {
         new ApplicationPage().clickYesButton();


    }
    @Then("the {string} field should be enabled and required")
    public void the_field_should_be_enabled_and_required(String string) {
        assertTrue("Yes button does no lead to Realtor Info tab", new ApplicationPage().realtorInfoUnabled());

    }


    @When("I select {string} for the {string} checkbox")
    public void iSelectForTheCheckbox(String arg0, String arg1) {
        new ApplicationPage().clickNoButton();

    }

    @Then("the {string} field should be disabled")
    public void theFieldShouldBeDisabled(String arg0) {
        assertTrue("No button leads to Realtor Info tab", new ApplicationPage().realtorInfoDisabled());
    }

    @And("I should see a another checkbox for {string} with options {string} and {string}")
    public void iShouldSeeAAnotherCheckboxForWithOptionsAnd(String arg0, String arg1, String arg2) {
        assertTrue("Items are not displayed", new ApplicationPage().checkBoxForLoanOfficer());

    }

    @And("the {string} field should have a dropdown menu with options {string}, {string}, and {string}")
    public void theFieldShouldHaveADropdownMenuWithOptionsAnd(String arg0, String arg1, String arg2, String arg3) {
        List<String> expectedOptions = Arrays.asList(arg1, arg2, arg3);
        ApplicationPage applicationPage = new ApplicationPage();
        List<String> actualOptions = applicationPage.LoanFieldOptions();
        assertEquals("Dropdown options do not match expected options",
                expectedOptions,actualOptions);
    }

    @When("I enter {string} into the Estimated Purchase Price field")
    public void iEnterIntoTheEstimatedPurchasePriceField(String arg0) {
    new ApplicationPage().estimatedPurchasePrice(arg0);
    }

    @Then("Estimated Purchase Price field should accept the value {string}")
    public void estimatedPurchasePriceFieldShouldAcceptTheValue(String arg0) {
        String actualValue = new ApplicationPage().getEstimatedPrice();
        assertEquals("The Estimated Purchase Price field did not accept the expected value", arg0, actualValue);
    }

    @When("I enter {string} into the Down Payment Amount field")
    public void iEnterFifthThousandIntoTheDownPaymentAmountField(String arg0) {
        new ApplicationPage().downPayment(arg0);
    }

    @Then("Down Payment Amount field should accept the value {string}")
    public void downPaymentAmountFieldShouldAcceptTheValue(String arg0) {
        String actualValue2 = new ApplicationPage().getDownPayment();
        assertEquals("The Down Payment Amount field did not accept the expected value", arg0, actualValue2);
    }
    @And("You Loan Amount filed should automatically calculate the down payment percentage")
    public void youLoanAmountFiledShouldAutomaticallyCalculateTheDownPaymentPercentage() {
        double expected=new ApplicationPage().calculatedDownPaymentPercentage();
        double actual=new ApplicationPage().getDownPaymentPercentage();
        assertEquals(expected, actual, 0.01);
    }
    /* @And("the Source of Down Payment field should have the following options in the dropdown:{string},{string},{string}")
        public void theSourceOfDownPaymentFieldShouldHaveTheFollowingOptionsInTheDropdown(String arg0, String arg1, String arg2) {
            List<String> expectedOptions2 = Arrays.asList(arg0, arg1, arg2);
            ApplicationPage applicationPage = new ApplicationPage();
            List<String> actualOptions2 = applicationPage.sourceOfDownPaymentOptions();
            assertEquals("Dropdown options do not match expected options",
                    expectedOptions2,actualOptions2);
            ---And the Source of Down Payment field should have the following options in the dropdown:"Checking/Savings (most recent bank statement)","Equity on Pending Sale (executed sales contract)","Other type of Down Payment"
        }*/
    @When("I enter {string} into the Additional Funds field")
    public void iEnterIntoTheAdditionalFundsField(String arg0) {
        new ApplicationPage().additionalFunds(arg0);

    }

    @Then("Additional Funds  field should accept the value {string}")
    public void additionalFundsFieldShouldAcceptTheValue(String arg0) {
        String actualValue = new ApplicationPage().getAdditionalPrice();
        assertEquals("The Additional Price field did not accept the expected value", arg0, actualValue);
    }

    @When("i am on the Mortgage Application page")
    public void iAmOnTheMortgageApplicationPage() {
        new DashboardPage().clickMortgageApplicationLink();
    }
    @Then("I do not fill out all the required fields")
    public void iDoNotFillOutAllTheRequiredFields() {
       //leave empty
    }

    @And("I click the {string} button")
    public void iClickTheButton(String arg0) {
        new ApplicationPage().clickNextButton();
        
    }

    @And("I should not be able to proceed to the next page")
    public void iShouldNotBeAbleToProceedToTheNextPage() {
        assertTrue(new ApplicationPage().isNextPageLoaded());
    }

    @When("all information filled")
    public void allInformationFilled() {
        new ApplicationPage().fillOutForm();
        
    }

    @Then("it should lead me to the next page")
    public void itShouldLeadMeToTheNextPage() {
       assertTrue(new ApplicationPage().isNextPageLoaded2());
    }
}




