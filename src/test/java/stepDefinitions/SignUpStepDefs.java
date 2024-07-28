package stepDefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import pages.SignUpPageUS1;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.FrameworkConstants;
import utilities.SeleniumUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SignUpStepDefs {

    @Given("the user is on the login page")
    public void theUserIsOnTheHomepage() {
        Driver.getDriver().get(ConfigReader.getProperty("url"));
    }

    @When("the user clicks sign up link")
    public void the_user_clicks_sign_up_link() {
        new SignUpPageUS1().getSignUpLink().click();
    }

    @Then("the user should see First Name, Last Name, Email Address, and Password fields")
    public void the_user_should_see_first_name_last_name_email_address_and_password_fields() {
        Assert.assertTrue(new SignUpPageUS1().getFirstNameField().isDisplayed());
        Assert.assertTrue(new SignUpPageUS1().getLastNameField().isDisplayed());
        Assert.assertTrue(new SignUpPageUS1().getEmailField().isDisplayed());
        Assert.assertTrue(new SignUpPageUS1().getPasswordField().isDisplayed());
    }


    @Then("the info entered on the field should meet the following conditions")
    public void the_field_should_meet_the_following_conditions(List<Map<String, String>> dataTable) {

        List<String> firstName = new ArrayList<>();
        List<String> lastName = new ArrayList<>();
        List<String> email = new ArrayList<>();
        List<String> password = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FrameworkConstants.SIGNUP_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                firstName.add(parts[0]);
                lastName.add(parts[1]);
                email.add(parts[2]);
                password.add(parts[3]);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        boolean isRequiredExpected;
        boolean hasLetterAndSpaceExpected;
        int maxLengthExpected;
        int minLengthExpected;
        boolean isDuplicateExpected;
        boolean hasUppercaseExpected;
        boolean hasLowercaseExpected;
        boolean hasNumberExpected;
        String formatExpected;
        String input;
        int count = 0;

        for (Map<String, String> row : dataTable) {

            String field = row.get("field");

            switch (field) {
                case "firstName":
                    for (String value : firstName) {
                        new SignUpPageUS1().getFirstNameField().clear();
                        new SignUpPageUS1().getFirstNameField().sendKeys(value, Keys.TAB);

                        isRequiredExpected = Boolean.parseBoolean(row.get("required"));
                        hasLetterAndSpaceExpected = Boolean.parseBoolean(row.get("hasLetterAndSpace"));
                        maxLengthExpected = Integer.parseInt(row.get("maxLength"));

                        input = new SignUpPageUS1().getFirstNameField().getAttribute("value");
                        count++;

                        Assert.assertTrue(isRequiredExpected && !input.isEmpty());

                        boolean actualLetterAndSpace = SeleniumUtils.containsOnlyLetterAndSpace(input);
                        Assert.assertEquals(hasLetterAndSpaceExpected, actualLetterAndSpace);

                        Assert.assertTrue(input.length() <= maxLengthExpected);
                    }
                    System.out.println("Quantity of first name options checked: " + count);
                    break;

                case "lastName":
                    for (String value : lastName) {
                        new SignUpPageUS1().getLastNameField().clear();
                        new SignUpPageUS1().getLastNameField().sendKeys(value, Keys.TAB);

                        isRequiredExpected = Boolean.parseBoolean(row.get("required"));
                        hasLetterAndSpaceExpected = Boolean.parseBoolean(row.get("hasLetterAndSpace"));
                        maxLengthExpected = Integer.parseInt(row.get("maxLength"));

                        input = new SignUpPageUS1().getLastNameField().getAttribute("value");
                        count++;

                        Assert.assertTrue(isRequiredExpected && !input.isEmpty());

                        boolean actualLetterAndSpace = SeleniumUtils.containsOnlyLetterAndSpace(input);
                        Assert.assertEquals(hasLetterAndSpaceExpected, actualLetterAndSpace);

                        Assert.assertTrue(input.length() <= maxLengthExpected);
                    }
                    System.out.println("Quantity of last name options checked: " + count);
                    break;

                case "email":
                    for (String value : email) {
                        new SignUpPageUS1().getEmailField().clear();
                        new SignUpPageUS1().getEmailField().sendKeys(value, Keys.TAB);
                        SeleniumUtils.waitFor(1);

                        isRequiredExpected = Boolean.parseBoolean(row.get("required"));
                        maxLengthExpected = Integer.parseInt(row.get("maxLength"));
                        isDuplicateExpected = Boolean.parseBoolean(row.get("isDuplicate"));
                        formatExpected = row.get("format");
                        formatExpected = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

                        input = new SignUpPageUS1().getEmailField().getAttribute("value");
                        count++;

                        Assert.assertTrue(isRequiredExpected && !input.isEmpty());
                        Assert.assertTrue(input.matches(formatExpected));
                        Assert.assertTrue(input.length() <= maxLengthExpected);

                        boolean duplicateActual = !new SignUpPageUS1().getEmailError().getText().contains("This email already used");
                        Assert.assertEquals(isDuplicateExpected, duplicateActual);
                    }
                    System.out.println("Quantity of email options checked: " + count);
                    break;

                case "password":
                    for (String value : password) {
                        new SignUpPageUS1().getPasswordField().clear();
                        new SignUpPageUS1().getPasswordField().sendKeys(value, Keys.TAB);

                        isRequiredExpected = Boolean.parseBoolean(row.get("required"));
                        maxLengthExpected = Integer.parseInt(row.get("maxLength"));
                        minLengthExpected = Integer.parseInt(row.get("minLength"));
                        hasUppercaseExpected = Boolean.parseBoolean(row.get("hasUppercase"));
                        hasLowercaseExpected = Boolean.parseBoolean(row.get("hasLowercase"));
                        hasNumberExpected = Boolean.parseBoolean(row.get("hasNumber"));

                        input = new SignUpPageUS1().getPasswordField().getAttribute("value");
                        count++;

                        Assert.assertTrue(isRequiredExpected && !input.isEmpty());
                        Assert.assertTrue(input.length() <= maxLengthExpected);
                        Assert.assertTrue(input.length() >= minLengthExpected);

                        boolean upperCaseActual = SeleniumUtils.containsUpperCase(input);
                        Assert.assertEquals(hasUppercaseExpected, upperCaseActual);

                        boolean lowerCaseActual = SeleniumUtils.containsLowerCase(input);
                        Assert.assertEquals(hasLowercaseExpected, lowerCaseActual);

                        boolean numberActual = SeleniumUtils.containsDigit(input);
                        Assert.assertEquals(hasNumberExpected, numberActual);
                    }
                    System.out.println("Quantity of password options checked: " + count);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field");
            }
        }
    }

    @When("the user clicks Sign Up button after filling some of the required fields")
    public void the_user_clicks_sign_up_button_after_filling_out_required_field_s(List<Map<String, String>> dataTable) {

        for (Map<String, String> row : dataTable) {
            String field = row.get("field");

            new SignUpPageUS1().getFirstNameField().clear();
            new SignUpPageUS1().getLastNameField().clear();
            new SignUpPageUS1().getEmailField().clear();
            new SignUpPageUS1().getPasswordField().clear();

            switch (field) {
                case "0":
                    break;
                case "1":
                    new SignUpPageUS1().getFirstNameField().sendKeys(new Faker().name().firstName());
                    break;
                case "2":
                    new SignUpPageUS1().getFirstNameField().sendKeys(new Faker().name().firstName());
                    new SignUpPageUS1().getLastNameField().sendKeys(new Faker().name().lastName());
                    break;
                case "3":
                    new SignUpPageUS1().getFirstNameField().sendKeys(new Faker().name().firstName());
                    new SignUpPageUS1().getLastNameField().sendKeys(new Faker().name().lastName());
                    new SignUpPageUS1().getEmailField().sendKeys(new Faker().internet().emailAddress());
                    break;
            }
            new SignUpPageUS1().getRegisterButton().click();
            SeleniumUtils.waitFor(3);
        }
    }

    @Then("Sign up button should be non-functional and user should remain on the Sign Up page")
    public void sign_up_button_should_be_non_functional() {
        String expectedPageTitle = new SignUpPageUS1().getSignUpPageTitle();
        String actualPageTitle = Driver.getDriver().getTitle();
        Assert.assertEquals(expectedPageTitle, actualPageTitle);
    }

    @Then("the user clicks Sign Up button by filling all required input fields")
    public void the_user_clicks_sign_up_button_by_filling_all_required_input_fields() {
        new SignUpPageUS1().fillAllFields();
        new SignUpPageUS1().getRegisterButton().click();
    }

    @Then("the user should see Registration Successful message and be redirected to the Sign In Page")
    public void the_user_should_see_registration_successful_message() {
        Assert.assertTrue(Driver.getDriver().getPageSource().contains("Registration Successful"));
        SeleniumUtils.waitFor(3);

        String expectedPageTitle = new SignUpPageUS1().getSignInPageTitle();
        String actualPageTitle = Driver.getDriver().getTitle();
        Assert.assertEquals(expectedPageTitle, actualPageTitle);
    }

    @When("the user enters an email address that is already associated with an existing account")
    public void the_user_enters_an_email_address_that_is_already_associated_with_an_existing_account() {
        new SignUpPageUS1().getEmailField().sendKeys("jglob13@gmail.com", Keys.TAB);
        SeleniumUtils.waitFor(2);
    }
    @Then("an error message “This email already used” should be displayed.")
    public void an_error_message_this_email_already_used_should_be_displayed() {
        Assert.assertTrue(new SignUpPageUS1().getEmailError().getText().contains("This email already used"));
    }


    @When("the user confirms \"Already have an account? Sign in\" link is displayed")
    public void the_user_confirms_already_have_an_account_sign_in_link_is_displayed() {

        String expectedLinkText = "Already have an account?Sign in"; // fix the space on the webpage
        String actualLinkText = new SignUpPageUS1().getAlreadyHaveAccountField().getText();

        Assert.assertEquals(expectedLinkText, actualLinkText);
        Assert.assertTrue(new SignUpPageUS1().getAlreadyHaveAccountField().isDisplayed());
    }

    @When("the user clicks on \"Already have an account? Sign in\" link")
    public void the_user_clicks_on_already_have_an_account_sign_in_link() {
        new SignUpPageUS1().getAlreadyHaveAccountLink().click();
    }
    @Then("the user should be redirected to the Sign In Page")
    public void the_user_should_be_redirected_to_the_sign_in_page() {

        String expectedPageTitle = new SignUpPageUS1().getSignInPageTitle();
        String actualPageTitle = Driver.getDriver().getTitle();
        Assert.assertEquals(expectedPageTitle, actualPageTitle);
    }



}





