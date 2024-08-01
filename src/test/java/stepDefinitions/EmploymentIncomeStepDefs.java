package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import pages.EmploymentAndIncomePageUS7;
import pages.ExpensesPageUS6;
import utilities.Driver;
import utilities.FrameworkConstants;
import utilities.SeleniumUtils;

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
        expenses.getNextButton().click();
        Assert.assertTrue(expenses.getTabEmploymentAndIncome().isEnabled());
    }

    @When("the user is on the Employment and Income Page")
    public void the_user_is_on_the_employment_and_income_page() {
        Assert.assertTrue(new ExpensesPageUS6().getTabEmploymentAndIncome().isEnabled());
    }
    @Then("the page should contain the following fields")
    public void the_page_should_contain_the_following_fields(DataTable fields) {
        EmploymentAndIncomePageUS7 account = new EmploymentAndIncomePageUS7();
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
                case "City":
                    eachField = account.getCityField();
                    break;
                // Add other cases as needed
                default:
                    Assert.fail("Field " + field + " is not recognized.");
            }

            Assert.assertTrue("Field " + field + " is not displayed.", eachField.isDisplayed());
        }


    }
    @Then("the page should contain date pickers for")
    public void the_page_should_contain_date_pickers_for(io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        throw new io.cucumber.java.PendingException();
    }

}
