package stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import pages.ApplicationListPageUS11;
import pages.DashboardPage;
import utilities.SeleniumUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ApplicationListStepDefs {

    @When("the user clicks on Application List link")
    public void the_user_clicks_on_application_list_link() {
        new DashboardPage().getApplicationListLink().click();
    }

    @Then("the dropdown menu should show options for {word}, {word}, {word} and {word} entries")
    public void the_dropdown_menu_should_show_options_for_and_entries(String o10, String o25, String o50, String o100) {

        String optionsText = new ApplicationListPageUS11().getDropdownApplicationList().getText();
        String [] optionsArr = optionsText.split("\\r?\\n");
        List <String> actualDropdownOptions = new ArrayList<>();
        for (String s : optionsArr) {
            actualDropdownOptions.add(s);
        }
        List <String> expectedDropdownOptions = List.of(o10, o25, o50, o100);
        Assert.assertEquals(expectedDropdownOptions, actualDropdownOptions);
    }

    @When("the user enters the {string} term in the search field and that name exists in the database")
    public void the_user_enters_the_borrower_s_in_the_search_field(String searchTerm) {
        new ApplicationListPageUS11().getSearchBox().sendKeys(searchTerm);
        SeleniumUtils.waitFor(1);
    }

    @Then("the page should show loan application results for only that name")
    public void the_page_should_show_loan_application_results_for_only_that_name(List<Map<String,String>> dataTable) {
        List <String> actualFirstName = new ArrayList<>();
        for (WebElement e : new ApplicationListPageUS11().getRows()){
            String [] line = e.getText().split(" ");
            actualFirstName.add(line[1]);
        }
        for (String s : actualFirstName) {
            Assert.assertTrue(s.equalsIgnoreCase(dataTable.get(0).get("name")));
        }
    }

    @Then("the user should see the Application List table headers in the following order")
    public void the_user_should_see_the_application_list_table_headers_in_the_following_order(List<String> expectedHeaders) {

        String headerText = new ApplicationListPageUS11().getHeaderColumns().getText().toLowerCase();
        String[] each = headerText.split(" ");

        List <String> actualHeaders = new ArrayList<>();

        actualHeaders.add(each[0] + " " + each[1]); // loan id
        actualHeaders.add(each[2] + " " + each[3]); // borrower name
        actualHeaders.add(each[4] + " " + each[5]); // borrower name
        actualHeaders.add(each[6]);                 // details

        Assert.assertEquals(expectedHeaders, actualHeaders);
     }

    @Then("the loan id should be consist only of digits")
    public void the_loan_id_should_be_consist_only_of_digits() {
        List<String> loanID = new ArrayList<>();
        for (WebElement e : new ApplicationListPageUS11().getRows()) {
            String[] line = e.getText().split(" ");
            loanID.add(line[0]);
        }
        for (String s : loanID) {
            for (char c : s.toCharArray()) {
                Assert.assertTrue(Character.isDigit(c));
            }
        }
    }

    @Then("the borrower name should be equal to {string}, {string} format")
    public void the_borrower_name_should_be_equal_to_format(String first, String last, List<Map<String,String>> dataTable) {

        String expectedFormat = first + " " + last;
        new ApplicationListPageUS11().getSearchBox().sendKeys(first);
        SeleniumUtils.waitFor(1);

        List<String> actualFormat = new ArrayList<>();
        for (WebElement e : new ApplicationListPageUS11().getRows()) {
            String[] line = e.getText().split(" ");
            actualFormat.add(line[1] + " " + line[2]);
        }
        for (String actual : actualFormat) {
            Assert.assertEquals(expectedFormat, actual);
        }
    }


    @Then("the loan amount should be consist only of digits and contain $ sign")
    public void the_loan_amount_should_be_consist_only_of_digits_and_contain_$_sign() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }


}
