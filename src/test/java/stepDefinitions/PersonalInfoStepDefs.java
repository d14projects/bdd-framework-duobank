package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Keys;
import pages.PreapprovalDetailsPageUS5;

public class PersonalInfoStepDefs {

    @Given("the user filled out Preapproval Page")
    public void the_user_filled_out_preapproval_page() {
        PreapprovalDetailsPageUS5 userStory5 = new PreapprovalDetailsPageUS5();
        userStory5.getRealtorYesCheckbox().click();
        userStory5.getRealtorNameAndPhoneField().sendKeys("Marcus Garcia 345-555-4545", Keys.ENTER);
        userStory5.getLoanOfficerYesCheckbox().click();
        userStory5.getEstimatedPurchasePriceField().sendKeys("575000",Keys.ENTER);
        userStory5.getDownPaymentField().sendKeys("65000", Keys.ENTER);
        userStory5.getAddtFundsField().sendKeys("15000", Keys.ENTER);
        userStory5.getNextButton().click();

    }

    @Given("the user is on the Personal Information Page")
    public void the_user_is_on_the_personal_information_page() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("the user sees the {string} field")
    public void the_user_sees_the_field(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("the user selects the {string} checkbox")
    public void the_user_selects_the_checkbox(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("the {string} checkbox should be checked")
    public void the_checkbox_should_be_checked(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("the {string} checkbox should not be checked")
    public void the_checkbox_should_not_be_checked(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
