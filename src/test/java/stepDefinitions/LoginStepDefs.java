package stepDefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.*;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import pages.LoginPageUS2;
import pages.SignUpPageUS1;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.SeleniumUtils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class LoginStepDefs {


    @Then("the user should see a Welcome Back! greeting message displayed on the page")
    public void the_user_should_see_a_welcome_message_displayed_on_the_page() {
        Assert.assertTrue(Driver.getDriver().getPageSource().contains("Welcome Back!"));
    }

    @Then("the user should see two input fields: one for email and one for password")
    public void the_user_should_see_two_input_fields_one_for_email_and_one_for_password() {
        Assert.assertTrue(new LoginPageUS2().getEmailField().isDisplayed());
        Assert.assertTrue(new LoginPageUS2().getPasswordField().isDisplayed());
    }

    @When("the user does not fill out both email and password fields")
    public void the_user_does_not_fill_out_both_email_and_password_fields() {

        List<Integer> options = Arrays.asList(1, 2, 3);
        for (Integer option : options) {
            new LoginPageUS2().getEmailField().clear();
            new LoginPageUS2().getPasswordField().clear();

            switch (option) {
                case 1 -> new LoginPageUS2().getEmailField().sendKeys(new LoginPageUS2().getEmailValid());
                case 2 -> new LoginPageUS2().getPasswordField().sendKeys(new LoginPageUS2().getPasswordValid());
                case 3 -> {
                    new LoginPageUS2().getEmailField().sendKeys("");
                    new LoginPageUS2().getPasswordField().sendKeys("");
                }
                default -> throw new RuntimeException("invalid entry");
            }
            new LoginPageUS2().getSignInButton().click();
        }
    }

    @Then("the user should not be able to submit the form to sign to the dashboard")
    public void the_user_should_not_be_able_to_submit_the_form_to_sign_to_the_dashboard() {
        String expectedLoginPageTitle = "Login - Duobank URLA (Uniform Residential Loan Application) Mortgage Application";
        Assert.assertEquals(expectedLoginPageTitle, Driver.getDriver().getTitle());
        SeleniumUtils.waitFor(5);
    }

    @When("the user enters email address to the email field")
    public void the_user_enters_email_address_to_the_email_field() {
        new LoginPageUS2().getEmailField().sendKeys("jglob@gmail.com");
    }

    @Then("the email should be in a valid email address format e.g. example@example.com")
    public void the_email_should_be_in_a_valid_email_address_format_e_g_example_example_com() {
        String actualEmail = new LoginPageUS2().getEmailField().getAttribute("value");
        String expectedEmailFormat = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Assert.assertTrue(actualEmail.matches(expectedEmailFormat));
    }

    @When("the user enters password to the password field")
    public void the_user_enters_password_to_the_password_field() {
        new LoginPageUS2().getPasswordField().sendKeys("Password1");
    }

    @Then("the password field should be masked and not show entered characters")
    public void the_password_field_should_be_masked_and_not_show_entered_characters() throws IOException, TesseractException {

        String actualType = new LoginPageUS2().getPasswordField().getAttribute("type");
        String expectedType = "password";
        Assert.assertEquals(expectedType, actualType);
    }


    @When("the user clicks Sign In button after filling some or none of the required fields")
    public void the_user_clicks_sign_in_button_after_filling_some_or_none_of_the_required_fields(List<Map<String, String>> dataTable) {
        for (Map<String, String> row : dataTable) {
            String field = row.get("field");

            new LoginPageUS2().getEmailField().clear();
            new LoginPageUS2().getPasswordField().clear();

            switch (field) {
                case "none" -> {
                }
                case "onlyEmail" -> new LoginPageUS2().getEmailField().sendKeys(new LoginPageUS2().getEmailValid());
                case "onlyPassword" ->
                        new LoginPageUS2().getPasswordField().sendKeys(new LoginPageUS2().getPasswordValid());
                default -> throw new RuntimeException("invalid entry");
            }
            new LoginPageUS2().getSignInButton().click();
            SeleniumUtils.waitFor(1);
        }
    }

    @Then("Sign in button should be disabled and user should remain on the Sign In page")
    public void sign_in_button_should_be_disabled_and_user_should_remain_on_the_sign_in_page() {
        String expectedLoginPageTitle = "Login - Duobank URLA (Uniform Residential Loan Application) Mortgage Application";
        Assert.assertEquals(expectedLoginPageTitle, Driver.getDriver().getTitle());
    }

    @When("the user clicks Sign In button by filling both email and password fields")
    public void the_user_clicks_sign_in_button_by_filling_both_email_and_password_fields() {
        new LoginPageUS2().getEmailField().clear();
        new LoginPageUS2().getPasswordField().clear();
        new LoginPageUS2().getEmailField().sendKeys(new LoginPageUS2().getEmailValid());
        new LoginPageUS2().getPasswordField().sendKeys(new LoginPageUS2().getPasswordValid());
        new LoginPageUS2().getSignInButton().click();
        SeleniumUtils.waitFor(1);
    }

    private String expectedErrorMessage1;
    private String expectedErrorMessage2;
    private String expectedErrorMessage3;

    @When("the user enters incorrect credentials and clicks Sign In button")
    public void the_user_enters_incorrect_credentials_and_clicks_sign_in_button(List<Map<String, String>> dataTable) {
        Faker faker = new Faker();
        Driver.quitDriver();

        for (Map<String, String> row : dataTable) {
            String field = row.get("field");

            Driver.getDriver().get(ConfigReader.getProperty("url"));
            Driver.getDriver().manage().window().maximize();
            Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

            new LoginPageUS2().getEmailField().clear();
            new LoginPageUS2().getPasswordField().clear();

            switch (field) {
                case "IncorrectEmail-IncorrectPassword" -> {
                    new LoginPageUS2().getEmailField().sendKeys(faker.internet().emailAddress());
                    new LoginPageUS2().getPasswordField().sendKeys(faker.internet().password());
                    new LoginPageUS2().getSignInButton().click();
                    if (SeleniumUtils.elementExists(new LoginPageUS2().getLoginFailedError(), 1)) {
                        expectedErrorMessage1 = new LoginPageUS2().getLoginFailedError().getText();
                    } else {
                        expectedErrorMessage1 = "searched element does not exist";
                    }
                    Driver.quitDriver();
                }
                case "CorrectEmail-IncorrectPassword" -> {
                    new LoginPageUS2().getEmailField().sendKeys(new LoginPageUS2().getEmailValid());
                    new LoginPageUS2().getPasswordField().sendKeys(faker.internet().password());
                    new LoginPageUS2().getSignInButton().click();
                    if (SeleniumUtils.elementExists(new LoginPageUS2().getLoginFailedError(), 1)) {
                        expectedErrorMessage2 = new LoginPageUS2().getLoginFailedError().getText();
                    } else {
                        expectedErrorMessage2 = "searched element does not exist";
                    }
                    Driver.quitDriver();
                }
                case "IncorrectEmail-CorrectPassword" -> {
                    new LoginPageUS2().getEmailField().sendKeys(faker.internet().emailAddress());
                    new LoginPageUS2().getPasswordField().sendKeys(new LoginPageUS2().getPasswordValid());
                    new LoginPageUS2().getSignInButton().click();
                    if (SeleniumUtils.elementExists(new LoginPageUS2().getLoginFailedError(), 1)) {
                        expectedErrorMessage3 = new LoginPageUS2().getLoginFailedError().getText();
                    } else {
                        expectedErrorMessage3 = "searched element does not exist";
                    }
                    Driver.quitDriver();
                }
                default -> throw new RuntimeException("invalid entry");
            }
        }
    }

    @Then("the user should see error message \"Login Failed\" indicating email or password are incorrect")
    public void the_user_should_see_error_message_login_failed_indicating_email_or_password_are_incorrect() {

        Assert.assertTrue(expectedErrorMessage1.contains("Login Failed"));
        Assert.assertTrue(expectedErrorMessage2.contains("Login Failed"));
        Assert.assertTrue(expectedErrorMessage3.contains("Login Failed"));
    }


    @When("the user enters correct credentials and clicks Sign In button")
    public void the_user_enters_correct_credentials_and_clicks_sign_in_button() {
        new LoginPageUS2().login();
    }

    @Then("the user is redirected to the mortgage account dashboard")
    public void the_user_is_redirected_to_the_mortgage_account_dashboard() {

        String actualPageTitle = "Duobank Mortgage Application";
        String expectedPageTitle = Driver.getDriver().getTitle();
        Assert.assertEquals(expectedPageTitle, actualPageTitle);
    }

}
