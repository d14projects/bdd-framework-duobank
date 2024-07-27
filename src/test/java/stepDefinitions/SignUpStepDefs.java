package stepDefinitions;

import io.cucumber.java.en.*;
import io.cucumber.java.eo.Se;
import org.junit.Assert;
import pages.SignUpPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.SeleniumUtils;

public class SignUpStepDefs {

    @Given("the user is on the login page")
    public void theUserIsOnTheHomepage() {
        Driver.getDriver().get(ConfigReader.getProperty("url"));
    }

    @When("the user clicks sign up link")
    public void the_user_clicks_sign_up_link() {
        new SignUpPage().getSignUpLink().click();
    }

    @When("enters firstName, lastName, emailAddress, password")
    public void enters_first_name_last_name_email_address_password() {
     new SignUpPage().createNewUser();
    }

    @Then("the user should see Registration Successful message")
    public void the_user_should_see_registration_successful_message() {
        Assert.assertTrue(Driver.getDriver().getPageSource().contains("Registration Successful"));
    }

}
