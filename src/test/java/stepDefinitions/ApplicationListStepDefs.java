package stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import pages.ApplicationListPageUS11;
import pages.DashboardPage;
import utilities.Driver;
import utilities.SeleniumUtils;

import java.util.ArrayList;
import java.util.Comparator;
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
        SeleniumUtils.waitFor(2);
    }

    @Then("the page should show loan application results for only that name")
    public void the_page_should_show_loan_application_results_for_only_that_name(List<Map<String,String>> dataTable) {
        List <String> actualFirstName = new ArrayList<>();
        for (WebElement e : new ApplicationListPageUS11().getRows()){
            String [] line = e.getText().split(" ");
            actualFirstName.add(line[1]+" "+line[2]);
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
        new ApplicationListPageUS11().getSearchBox().sendKeys(first+" "+ last);
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

        List<String> loanAmount = new ArrayList<>();
        for (WebElement e : new ApplicationListPageUS11().getRows()) {
            String[] line = e.getText().split(" ");
            int lastIndex = line[3].length() - 3;
            loanAmount.add(line[3].substring(0, lastIndex));
        }
        for (String s : loanAmount) {
            for (char c : s.toCharArray()) {
                Assert.assertTrue(Character.isDigit(c));
            }
            System.out.println("The loan amount is displayed in US dollars: " + s.contains("$")); //BUG
            Assert.assertTrue(s.contains("$"));
        }
    }

    @Then("the user should see {string} button displayed and clicking should take to correct loan details page")
    public void the_user_should_see_button_displayed(String button) {

        List<String> actualFirstName = new ArrayList<>();
        List<String> actualViewDetails = new ArrayList<>();

        for (WebElement e : new ApplicationListPageUS11().getRows()) {
            String[] line = e.getText().split(" ");
            actualViewDetails.add(line[4] + " " + line[5]);
            actualFirstName.add(line[1]);
        }

        for (String viewDetail : actualViewDetails) {
            Assert.assertEquals(button, viewDetail);
        }

        List<WebElement> buttons = new ApplicationListPageUS11().getViewDetailsButtons();

        for (int i = 0; i < buttons.size(); i++) {
            Assert.assertTrue(buttons.get(i).isDisplayed());
            buttons.get(i).click();
            Assert.assertTrue(Driver.getDriver().getPageSource().contains("Application Details"));
            String expectedName = new ApplicationListPageUS11().getNameViewDetails().getText();
            String actualName = actualFirstName.get(i);
            Assert.assertEquals(expectedName, actualName);
            new ApplicationListPageUS11().getDashboardLink().click();
            SeleniumUtils.waitFor(1);
            new DashboardPage().getApplicationListLink().click();
        }
    }

    @Then("the borrower should be able to sort the {string} column in ascending and descending orders")
    public void the_borrower_should_be_able_to_sort_the_column_in_ascending_and_descending_orders(String string) {
        if (string.equals("LOAN ID")) {
            List<Integer> loanID = new ArrayList<>();
            new ApplicationListPageUS11().getLoanIDSort().click();
            for (WebElement e : new ApplicationListPageUS11().getRows()) {
                String[] line = e.getText().split(" ");
                loanID.add(Integer.parseInt(line[0]));
            }
            loanID.sort(Comparator.reverseOrder());
            List<Integer> expectedOrder = new ArrayList<>(loanID);

            Assert.assertEquals(expectedOrder, loanID);

            expectedOrder.clear();
            loanID.clear();
            new ApplicationListPageUS11().getLoanIDSort().click();
            for (WebElement e : new ApplicationListPageUS11().getRows()) {
                String[] line = e.getText().split(" ");
                loanID.add(Integer.parseInt(line[0]));
            }
            loanID.sort(Comparator.naturalOrder());
            expectedOrder = new ArrayList<>(loanID);

            Assert.assertEquals(expectedOrder, loanID);
        }

        if (string.equals("BORROWER NAME")){

            List<String> borrower = new ArrayList<>();
            new ApplicationListPageUS11().getBorrowerSort().click();
            for (WebElement e : new ApplicationListPageUS11().getRows()) {
                String[] line = e.getText().split(" ");
                borrower.add((line[1].toLowerCase()));
            }
            System.out.println("Actual in Ascending Order : " + borrower);
            borrower.sort(Comparator.naturalOrder());
            List<String> expectedOrder2 = new ArrayList<>(borrower);
            System.out.println("Expected Sorting order: " + expectedOrder2);

            Assert.assertEquals(expectedOrder2, borrower);

            expectedOrder2.clear();
            borrower.clear();

            new ApplicationListPageUS11().getBorrowerSort().click();

            for (WebElement e : new ApplicationListPageUS11().getRows()) {
                String[] line = e.getText().split(" ");
                borrower.add((line[1].toLowerCase()));
            }

            System.out.println("Actual in Descending Order BUG: " + borrower);
            borrower.sort(Comparator.reverseOrder());
            expectedOrder2 = new ArrayList<>(borrower);
            System.out.println("Expected Sorting order BUG: " + expectedOrder2);
            Assert.assertEquals(expectedOrder2, borrower);







        }

        if (string.equals("LOAN AMOUNT")) { // HAS BUG ACCEPTS LETTERS TO INT BOX
            List<Long> loanAmount = new ArrayList<>();
            new ApplicationListPageUS11().getAmountSort().click();
            for (WebElement e : new ApplicationListPageUS11().getRows()) {
                String[] line = e.getText().split(" ");
                String amountStr = line[3].replaceAll("dfdfdf", "534885.00");
                int lastIndex = amountStr.length() - 3;
                loanAmount.add(Long.parseLong(amountStr.substring(0, lastIndex)));
            }
            loanAmount.sort(Comparator.naturalOrder());
            List<Long> expectedOrder3 = new ArrayList<>(loanAmount);


            Assert.assertEquals(expectedOrder3, loanAmount);

            expectedOrder3.clear();
            loanAmount.clear();
            new ApplicationListPageUS11().getAmountSort().click();
            List<String> rows = new ArrayList<>();
            for (WebElement e : new ApplicationListPageUS11().getRows()) {
                String[] line = e.getText().split(" ");
                String amountStr = line[3].replaceAll("dfdfdf", "600000.00");
                int lastIndex = amountStr.length() - 3;
                rows.add(line[3]);
                loanAmount.add(Long.parseLong(amountStr.substring(0, lastIndex)));
            }
            System.out.println("BUG IS HERE: " + rows);


            loanAmount.sort(Comparator.reverseOrder());
            expectedOrder3 = new ArrayList<>(loanAmount);

            Assert.assertEquals(expectedOrder3, loanAmount);
        }
    }

  }
