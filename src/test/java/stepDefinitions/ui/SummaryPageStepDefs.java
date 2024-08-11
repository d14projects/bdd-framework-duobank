package stepDefinitions.ui;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import pages.SummaryPageUS10;
import utilities.Driver;
import utilities.SeleniumUtils;

import java.util.List;
import java.util.Map;

public class SummaryPageStepDefs {

    @When("the user clicks on Mortgage Application link and fills out the form till e-Consent inclusive")
    public void the_user_clicks_on_mortgage_application_link_and_completes_till_ECONSENT_inclusive() {
        new SummaryPageUS10().getMortgageApplicationLink().click();
        new SummaryPageUS10().fillOutTillEConsentPage();
    }

    @Then("The Summary page should be displayed and contain edit buttons")
    public void the_summary_page_should_be_displayed() {

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(new SummaryPageUS10().getSummaryLink().isDisplayed()).isTrue();
        softAssertions.assertThat(Driver.getDriver().getPageSource().contains("Edit")).isTrue();
        softAssertions.assertAll();
    }

    @Then("the page should display all the information that the user has previously entered in the in each section")
    public void the_page_should_display_all_the_info_the_user_has_previously_entered(List<Map<String, String>> map) {

        for (WebElement el : new SummaryPageUS10().getExpectedSections()) {
            for (Map<String, String> each : map) {
                if (each.get("section").equals(el.getText())) {
                    Assert.assertTrue(el.isDisplayed());
                    System.out.println("Actual element: " + el.getText());
                    System.out.println("Expected element: " + each.get("section"));
                }
            }
        }
    }

    @Then("each section should be clearly labeled")
    public void each_section_should_be_clearly_labeled(Map<String, String> dataTable) {

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(new SummaryPageUS10().isLabelDisplayed(dataTable.get("label1"))).isTrue();
        softAssertions.assertThat(new SummaryPageUS10().isLabelDisplayed(dataTable.get("label2"))).isTrue();
        softAssertions.assertThat(new SummaryPageUS10().isLabelDisplayed(dataTable.get("label3"))).isTrue();
        softAssertions.assertThat(new SummaryPageUS10().isLabelDisplayed(dataTable.get("label4"))).isTrue();
        softAssertions.assertThat(new SummaryPageUS10().isLabelDisplayed(dataTable.get("label5"))).isTrue();
        softAssertions.assertThat(new SummaryPageUS10().isLabelDisplayed(dataTable.get("label6"))).isTrue();

        softAssertions.assertAll();
    }

    @Then("each section should have {string} button displayed")
    public void each_section_should_have_button_displayed(String string) {

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(new SummaryPageUS10().getPreApprovalEditButton().getText()).isEqualTo(string);
        softAssertions.assertThat(new SummaryPageUS10().getPersonalInfoEditButton().getText()).isEqualTo(string);
        softAssertions.assertThat(new SummaryPageUS10().getExpenseEditButton().getText()).isEqualTo(string);
        softAssertions.assertThat(new SummaryPageUS10().getEmploymentIncomeEditButton().getText()).isEqualTo(string);
        softAssertions.assertThat(new SummaryPageUS10().getCreditReportEditButton().getText()).isEqualTo(string);
        softAssertions.assertThat(new SummaryPageUS10().getEConsentEditButton().getText()).isEqualTo(string);

        softAssertions.assertAll();
    }


    @When("the user clicks on the Edit button for a {string} section")
    public void the_user_clicks_on_the_edit_button_for_a_section(String sectionTitle) {

        if (sectionTitle.equals("PreApproval Details")) {
            new SummaryPageUS10().getPreApprovalEditButton().click();
            SeleniumUtils.waitFor(1);
        }
        if (sectionTitle.equals("Personal Information")) {
            new SummaryPageUS10().getPersonalInfoEditButton().click();
            SeleniumUtils.waitFor(1);

        }
        if (sectionTitle.equals("Expenses")) {
            new SummaryPageUS10().getExpenseEditButton().click();
            SeleniumUtils.waitFor(1);

        }
        if (sectionTitle.equals("Employment and Income")) {
            new SummaryPageUS10().getEmploymentIncomeEditButton().click();
            SeleniumUtils.waitFor(1);

        }
        if (sectionTitle.equals("Credit Report")) {
            new SummaryPageUS10().getCreditReportEditButton().click();
            SeleniumUtils.waitFor(1);

        }
        if (sectionTitle.equals("eConsent")) {
            new SummaryPageUS10().getEConsentEditButton().click();
            SeleniumUtils.waitFor(1);
        }
    }

    @Then("the user is taken to the PreApproval page of the application")
    public void the_user_is_taken_to_the_preApproval_page_of_the_application() {
        Assert.assertTrue(new SummaryPageUS10().getRealtorNoCheckbox().isDisplayed());
        new SummaryPageUS10().getSummaryLink().click();
        SeleniumUtils.waitFor(1);
    }

    @Then("the user is taken to the Personal Information page of the application")
    public void the_user_is_taken_to_the_personalInfo_page_of_the_application() {
        Assert.assertTrue(new SummaryPageUS10().getCoBorrowerNoCheckbox().isDisplayed());
        new SummaryPageUS10().getSummaryLink().click();
        SeleniumUtils.waitFor(1);
    }

    @Then("the user is taken to the Expenses page of the application")
    public void the_user_is_taken_to_the_expenses_page_of_the_application() {
        Assert.assertTrue(new SummaryPageUS10().getCheckboxRent().isDisplayed());
        new SummaryPageUS10().getSummaryLink().click();
        SeleniumUtils.waitFor(1);
    }

    @Then("the user is taken to the Employment and Income page of the application")
    public void the_user_is_taken_to_the_employment_and_income_page_of_the_application() {
        Assert.assertTrue(new SummaryPageUS10().getEmployerNameField().isDisplayed());
        new SummaryPageUS10().getSummaryLink().click();
        SeleniumUtils.waitFor(1);
    }

    @Then("the user is taken to the Credit Report page of the application")
    public void the_user_is_taken_to_the_credit_report_page_of_the_application() {
        Assert.assertTrue(new SummaryPageUS10().getCheckboxNoCreditReport().isDisplayed());
        new SummaryPageUS10().getSummaryLink().click();
        SeleniumUtils.waitFor(1);
    }

    @Then("the user is taken to the E-Consent page of the application")
    public void the_user_is_taken_to_the_e_consent_page_of_the_application() {
        Assert.assertTrue(new SummaryPageUS10().getEConsentEmail().isDisplayed());
        new SummaryPageUS10().getSummaryLink().click();
        SeleniumUtils.waitFor(1);
    }

    @Then("the user is able to see the Submit button")
    public void the_user_is_able_to_see_the_submit_button() {
        Assert.assertTrue(new SummaryPageUS10().getSubmitButton().isDisplayed());
    }

    @When("the user clicks on the Submit button")
    public void the_user_clicks_on_the_submit_button() {
        new SummaryPageUS10().getSubmitButton().click();
        SeleniumUtils.waitFor(2);
    }
    @Then("the user should see Application Submitted Successfully message")
    public void the_user_should_see_application_submitted_successfully_message() {
//        System.out.println(Driver.getDriver().getPageSource().contains("Application Submiited Successfully"));
//        System.out.println(new SummaryPageUS10().getSubmitSuccessMessage().getText());
        Assert.assertTrue(new SummaryPageUS10().getSubmitSuccessMessage().isDisplayed());
    }

}
