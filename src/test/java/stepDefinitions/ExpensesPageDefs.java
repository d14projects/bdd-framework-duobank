package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.ExpensesPageUS6;
import pages.LoginPageUS2;
import pages.PersonalInformationPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.FrameworkConstants;
import utilities.SeleniumUtils;

import java.time.Duration;

public class ExpensesPageDefs {
    @Given("the user has completed the Personal Information Page")
    public void theUserHasCompletedThePersonalInformationPage() {
//        Driver.getDriver().get(FrameworkConstants.HOMEPAGE_URL);
//        Driver.getDriver().findElement(By.name("email")).sendKeys(ConfigReader.getProperty("email"), Keys.TAB,
//                ConfigReader.getProperty("password"), Keys.ENTER);

        PersonalInformationPage account = new PersonalInformationPage();
        if(!account.getCoBorrowerNoCheckbox().isSelected()){
            account.getCoBorrowerNoCheckbox().click();
        }
        //account.getCoBorrowerNoCheckbox().click();
        account.getFirstNameField().sendKeys("Will", Keys.ENTER);
        account.getLastNameField().sendKeys("Smith", Keys.ENTER);
        account.getEmailField().sendKeys("smith@yahoo.com", Keys.ENTER);
        account.getSsnField().sendKeys("989555652");
        SeleniumUtils.waitFor(2);

        Select dropdown = new Select(account.getMaritalStatusDropdown());
        dropdown.selectByVisibleText("Married");
        account.getCellphoneField().sendKeys("1235554488");


        if(!account.getPrivacyCheckbox().isSelected()){
            account.getPrivacyCheckbox().click();
        }
        account.getNextButton().click();
        SeleniumUtils.waitForPageToLoad(2);
    }


    @Given("the user is on the Expenses page")
    public void the_user_is_on_the_expenses_page() {
        SeleniumUtils.waitForPageToLoad(3);
        ExpensesPageUS6 accountExpenses=new ExpensesPageUS6();
        Assert.assertTrue(accountExpenses.getStepExpenses().isEnabled());
        SeleniumUtils.waitForVisibility(accountExpenses.getHeadTitle(),1);
        Assert.assertEquals("Current Monthly Housing Expenses",accountExpenses.getHeadTitle().getText());
    }
    @Given("sees two checkbox fields {string} and {string}")
    public void sees_two_checkbox_fields_and(String rent, String own) {
       SeleniumUtils.waitForPageToLoad(3);
       ExpensesPageUS6 accountExpenses = new ExpensesPageUS6();

   Assert.assertTrue(accountExpenses.getCheckboxRent().isEnabled());
   Assert.assertTrue(accountExpenses.getCheckboxOwn().isEnabled());

    }


    @Given("user can select only one checkbox at a time")
    public void user_can_select_only_one_checkbox_at_a_time() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("the other checkbox is not selected")
    public void the_other_checkbox_is_not_selected() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
