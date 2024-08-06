package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.datatable.DataTable;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.EmploymentAndIncomePageUS7;
import pages.ExpensesPageUS6;
import utilities.Driver;
import utilities.FrameworkConstants;
import utilities.SeleniumUtils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class EmploymentIncomeStepDefs {

    @Given("the user completed the Expenses Page")
    public void the_user_completed_the_expenses_page() {
         ExpensesPageUS6 expenses = new ExpensesPageUS6();
        if(! expenses.getCheckboxRent().isSelected()){
            expenses.getCheckboxRent().click();
        }
        expenses.getMonthlyRentalPaymentField().sendKeys("2200", Keys.ENTER);
        if (expenses.getNextButton().isDisplayed()) {
            expenses.getNextButton().click();

        }
        else{
        SeleniumUtils.waitForPageToLoad(5);
        expenses.getNextButton().click();}
       Assert.assertTrue(expenses.getTabEmploymentAndIncome().isEnabled());
    }

    @When("the user is on the Employment and Income Page")
    public void the_user_is_on_the_employment_and_income_page() {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(5));
        WebElement title =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[@class='py-50' and text()='Borrower Employment Information']")));
        Assert.assertTrue(new ExpensesPageUS6().getTabEmploymentAndIncome().isEnabled());
    }


    @Then("the page should contain the following fields")
    public void the_page_should_contain_the_following_fields(DataTable fields) {
        EmploymentAndIncomePageUS7 account = new EmploymentAndIncomePageUS7();
        SeleniumUtils.waitForPageToLoad(3);
        List<String> elements= fields.asList();

        for(String field : elements){
            WebElement eachField = null;


            switch (field) {
                case "Employer Name":
                    eachField = account.getEmployerNameField();
                    break;
                case "Position":
                    eachField = account.getPositionField();
                    break;
                case "City"   :
                    eachField = account.getCityField();
                    break;
                case "State":
                    eachField = account.getStateDropdown();
            }

            Assert.assertTrue(eachField.isDisplayed());
        }

    }

    @Then("the page should contain date pickers for")
    public void the_page_should_contain_date_pickers_for(DataTable pickers) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(5));
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='currentjob1']")));
        EmploymentAndIncomePageUS7 account = new EmploymentAndIncomePageUS7();
        if (account.getCurrentJobCheckbox().isSelected()) {

            account.getCurrentJobCheckbox().click();
            SeleniumUtils.waitFor(6);
        }

        SeleniumUtils.waitForPageToLoad(3);
        List<String> elements = pickers.asList();
        for (String element : elements) {
            WebElement var = null;
            switch (element) {
                case "Start Date":
                    var = account.getCityField();
                    break;
                case "End Date":
                    var = account.getStateDropdown();
            }
            Assert.assertTrue(var.isDisplayed());
        }
    }

        @Then("the Current Job checkbox should be unchecked")
        public void the_current_job_checkbox_should_be_unchecked() {

            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(5));

            WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='currentjob1']")));
            SeleniumUtils.waitFor(5);
            Assert.assertTrue(!new EmploymentAndIncomePageUS7().getCurrentJobCheckbox().isSelected());
        }

    }

