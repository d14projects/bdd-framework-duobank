package stepDefinitions;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import pages.SignUpPage;
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

    @Then("the user should see First Name, Last Name, Email Address, and Password fields")
    public void the_user_should_see_first_name_last_name_email_address_and_password_fields() {
        Assert.assertTrue(new SignUpPage().getFirstNameField().isDisplayed());
        Assert.assertTrue(new SignUpPage().getLastNameField().isDisplayed());
        Assert.assertTrue(new SignUpPage().getEmailField().isDisplayed());
        Assert.assertTrue(new SignUpPage().getPasswordField().isDisplayed());
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
                        new SignUpPage().getFirstNameField().clear();
                        new SignUpPage().getFirstNameField().sendKeys(value, Keys.TAB);

                        isRequiredExpected = Boolean.parseBoolean(row.get("required"));
                        hasLetterAndSpaceExpected = Boolean.parseBoolean(row.get("hasLetterAndSpace"));
                        maxLengthExpected = Integer.parseInt(row.get("maxLength"));

                        input = new SignUpPage().getFirstNameField().getAttribute("value");
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
                        new SignUpPage().getLastNameField().clear();
                        new SignUpPage().getLastNameField().sendKeys(value, Keys.TAB);

                        isRequiredExpected = Boolean.parseBoolean(row.get("required"));
                        hasLetterAndSpaceExpected = Boolean.parseBoolean(row.get("hasLetterAndSpace"));
                        maxLengthExpected = Integer.parseInt(row.get("maxLength"));

                        input = new SignUpPage().getLastNameField().getAttribute("value");
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
                        new SignUpPage().getEmailField().clear();
                        new SignUpPage().getEmailField().sendKeys(value, Keys.TAB);
                        SeleniumUtils.waitFor(1);

                        isRequiredExpected = Boolean.parseBoolean(row.get("required"));
                        maxLengthExpected = Integer.parseInt(row.get("maxLength"));
                        isDuplicateExpected = Boolean.parseBoolean(row.get("isDuplicate"));
                        formatExpected = row.get("format");
                        formatExpected = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

                        input = new SignUpPage().getEmailField().getAttribute("value");
                        count++;

                        Assert.assertTrue(isRequiredExpected && !input.isEmpty());
                        Assert.assertTrue(input.matches(formatExpected));
                        Assert.assertTrue(input.length() <= maxLengthExpected);

                        boolean duplicateActual = !new SignUpPage().getEmailError().getText().contains("This email already used");
                        Assert.assertEquals(isDuplicateExpected, duplicateActual);
                    }
                    System.out.println("Quantity of email options checked: " + count);
                    break;

                case "password":
                    for (String value : password) {
                        new SignUpPage().getPasswordField().clear();
                        new SignUpPage().getPasswordField().sendKeys(value, Keys.TAB);

                        isRequiredExpected = Boolean.parseBoolean(row.get("required"));
                        maxLengthExpected = Integer.parseInt(row.get("maxLength"));
                        minLengthExpected = Integer.parseInt(row.get("minLength"));
                        hasUppercaseExpected = Boolean.parseBoolean(row.get("hasUppercase"));
                        hasLowercaseExpected = Boolean.parseBoolean(row.get("hasLowercase"));
                        hasNumberExpected = Boolean.parseBoolean(row.get("hasNumber"));

                        input = new SignUpPage().getPasswordField().getAttribute("value");
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
}





