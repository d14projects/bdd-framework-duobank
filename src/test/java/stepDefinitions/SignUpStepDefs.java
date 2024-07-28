package stepDefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import pages.SignUpPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.SeleniumUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
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

    List<String> firstName = new ArrayList<>();
    List<String> lastName = new ArrayList<>();
    List<String> email = new ArrayList<>();
    List<String> password = new ArrayList<>();
    @When("the user enters info on the {string} field")
    public void the_user_clicks_on_the_field(String field) {

        try (BufferedReader br = new BufferedReader(new FileReader("src/test/requiredSignupData.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                this.firstName.add(parts[0]);
                this.lastName.add(parts[1]);
                this.email.add(parts[2]);
                this.password.add(parts[3]);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Then("the entered info should meet the following conditions")
    public void the_field_should_meet_the_following_conditions(List<Map<String, String>> dataTable) {

        boolean isRequired;
        boolean isLetterAndSpace;
        int maxLength;
        int minLength;
        boolean isDuplicate;
        boolean isUppercase;
        boolean isLowercase;
        boolean isNumber;
        String input;

        for (Map<String, String> row : dataTable) {

            String field = row.get("field");

            switch (field) {
                case "firstName":
                    for (String value : firstName) {
                        new SignUpPage().getFirstNameField().clear();
                        new SignUpPage().getFirstNameField().sendKeys(value, Keys.TAB);

                        isRequired = Boolean.parseBoolean(row.get("required"));
                        isLetterAndSpace = Boolean.parseBoolean(row.get("hasLetterAndSpace"));
                        maxLength = Integer.parseInt(row.get("maxLength"));

                        input = new SignUpPage().getFirstNameField().getAttribute("value");

                        Assert.assertTrue(isRequired && !input.isEmpty());
                        Assert.assertTrue(isLetterAndSpace && SignUpPage.containsOnlyLetterAndSpace(input));
                        Assert.assertTrue(input.length() <= maxLength);
                    }
                    break;

                case "lastName":
                    for (String value : lastName) {
                        new SignUpPage().getLastNameField().clear();
                        new SignUpPage().getLastNameField().sendKeys(value, Keys.TAB);

                        isRequired = Boolean.parseBoolean(row.get("required"));
                        isLetterAndSpace = Boolean.parseBoolean(row.get("hasLetterAndSpace"));
                        maxLength = Integer.parseInt(row.get("maxLength"));

                        input = new SignUpPage().getLastNameField().getAttribute("value");

                        Assert.assertTrue(isRequired && !input.isEmpty());
                        Assert.assertTrue(isLetterAndSpace && SignUpPage.containsOnlyLetterAndSpace(input));
                        Assert.assertTrue(input.length() <= maxLength);
                    }
                    break;

                case "email":
                    for (String value : email) {
                        new SignUpPage().getEmailField().clear();
                        new SignUpPage().getEmailField().sendKeys(value, Keys.TAB);
                        SeleniumUtils.waitFor(1);

                        System.out.println(value);

                        isRequired = Boolean.parseBoolean(row.get("required"));
                        maxLength = Integer.parseInt(row.get("maxLength"));
                        isDuplicate = Boolean.parseBoolean(row.get("isDuplicate"));
                        String emailFormat = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

                        input = new SignUpPage().getEmailField().getAttribute("value");

                        Assert.assertTrue(isRequired && !input.isEmpty());
                        Assert.assertTrue(input.matches(emailFormat));
                        Assert.assertTrue(input.length() <= maxLength);
                        Assert.assertTrue(isDuplicate && !new SignUpPage().getEmailError().getText().contains("This email already used"));
                    }
                    break;

                case "password":
                    for (String value : password) {
                        new SignUpPage().getPasswordField().clear();
                        new SignUpPage().getPasswordField().sendKeys(value, Keys.TAB);

                        isRequired = Boolean.parseBoolean(row.get("required"));
                        maxLength = Integer.parseInt(row.get("maxLength"));
                        minLength = Integer.parseInt(row.get("minLength"));
                        isUppercase = Boolean.parseBoolean(row.get("hasUppercase"));
                        isLowercase = Boolean.parseBoolean(row.get("hasLowercase"));
                        isNumber = Boolean.parseBoolean(row.get("hasNumber"));

                        input = new SignUpPage().getPasswordField().getAttribute("value");

                        Assert.assertTrue(isRequired && !input.isEmpty());
                        Assert.assertTrue(input.length() <= maxLength);
                        Assert.assertTrue(input.length() >= minLength);
                        Assert.assertTrue(isUppercase && SignUpPage.containsUpperCase(input));
                        Assert.assertTrue(isLowercase && SignUpPage.containsLowerCase(input));
                        Assert.assertTrue(isNumber && SignUpPage.containsDigit(input));
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field");
            }
        }
    }
}





