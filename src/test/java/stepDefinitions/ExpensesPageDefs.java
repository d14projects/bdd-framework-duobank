package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;
import pages.ExpensesPageUS6;
import pages.PersonalInformationPage;
import utilities.SeleniumUtils;

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
        Assert.assertTrue(accountExpenses.getTabExpenses().isEnabled());
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
        ExpensesPageUS6 account = new ExpensesPageUS6();
        SeleniumUtils.waitForPageToLoad(3);
        if (account.getCheckboxOwn().isSelected() && account.getCheckboxOwn().isDisplayed()) {
            account.getCheckboxOwn().click();
        }
        if (account.getCheckboxRent().isDisplayed()) {
            account.getCheckboxRent().click();
        }
    }


    @Then("the other checkbox is not selected")
    public void the_other_checkbox_is_not_selected() {
        ExpensesPageUS6 account = new ExpensesPageUS6();
        Assert.assertTrue(account.getCheckboxRent().isSelected());
        Assert.assertFalse(account.getCheckboxOwn().isSelected());
    }


}
