package stepDefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.*;
import io.cucumber.java.eo.Se;
import lombok.Data;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.SourceType;
import pages.LoginPageUS2;
import pages.SignUpPageUS1;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.FrameworkConstants;
import utilities.SeleniumUtils;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
                case 1 -> new LoginPageUS2().getEmailField().sendKeys(new LoginPageUS2().getEmailAddress());
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
    public void the_password_field_should_be_masked_and_not_show_entered_characters() {

        String testPassword = "Password1";
        System.out.println("Test password: " + testPassword );

        String actualPassword = new LoginPageUS2().getPasswordField().getAttribute("value");
        System.out.println("Masked Text: " + actualPassword);

//        boolean isMasked = true;
//
//        for (char c : actualPassword.toCharArray()){
//            if (c != '*');
//            isMasked = false;
//            break;
//        }
//        SeleniumUtils.waitFor(10);
//
//        try {
//            Actions actions = new Actions(Driver.getDriver());
//            actions.click(new LoginPageUS2().getPasswordField())
//                    .keyDown(Keys.COMMAND)
//                    .sendKeys("a")
//                    .sendKeys("c")
//                    .keyUp(Keys.COMMAND)
//                    .perform();
//
//            SeleniumUtils.waitFor(5);
//
//            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
//            Transferable transferable = clipboard.getContents(null);
//
//            String copiedText = (String) transferable.getTransferData(DataFlavor.stringFlavor);
//            System.out.println("Copied Text: " + copiedText);
//
//        } catch (Throwable e) {
//            throw new RuntimeException(e);
//        }


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
