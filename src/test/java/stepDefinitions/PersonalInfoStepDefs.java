package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import pages.PersonalInformationPage;
import pages.PreapprovalProcessPageUS5;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.FrameworkConstants;
import utilities.SeleniumUtils;

public class PersonalInfoStepDefs {

    @Given("the user has completed the Preapproval Details page")
    public void theUserHasCompletedThePreapprovalDetailsPage() {
        Driver.getDriver().get(FrameworkConstants.HOMEPAGE_URL);
        Driver.getDriver().findElement(By.name("email")).sendKeys(ConfigReader.getProperty("email"),Keys.TAB,
                ConfigReader.getProperty("password"),Keys.ENTER);
        Driver.getDriver().findElement(By.linkText("Mortgage Application")).click();
        PreapprovalProcessPageUS5 preapproval = new PreapprovalProcessPageUS5();
        if(!preapproval.getRealtorYesCheckbox().isEnabled()){
            preapproval.getRealtorYesCheckbox().click();
        }
        preapproval.getRealtorNameAndPhoneField().sendKeys("Marcus Garcia 345-555-4545",Keys.ENTER);
        if(!preapproval.getLoanOfficerYesCheckbox().isEnabled()){
            preapproval.getLoanOfficerYesCheckbox().click();
        }
        preapproval.getEstimatedPurchasePriceField().sendKeys("550000",Keys.ENTER);
        preapproval.getDownPaymentField().sendKeys("60000",Keys.ENTER);

        preapproval.getAddtFundsField().sendKeys("12000", Keys.ENTER);

        preapproval.getNextButton().click();

    }

    @Given("the user is on the Personal Information Page")
    public void the_user_is_on_the_personal_information_page() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("the user sees the {string} field")
    public void the_user_sees_the_field(String string) {
        string = "ARE YOU APPLYING WITH A CO-BORROWER?";
    }

    @When("the user selects the Yes checkbox")
    public void the_user_selects_the_yes_checkbox() {
        PersonalInformationPage checkbox = new PersonalInformationPage();
       if(!checkbox.getCoBorrowerYesCheckbox().isEnabled()){
           checkbox.getCoBorrowerYesCheckbox().click();
       }
    }

    @Then("the Yes checkbox should be checked")
    public void the_yes_checkbox_should_be_checked() {
        Assert.assertTrue(new PersonalInformationPage().getCoBorrowerYesCheckbox().isEnabled());
    }

    @Then("the No checkbox should not be checked")
    public void the_no_checkbox_should_not_be_checked() {
        Assert.assertTrue(new PersonalInformationPage().getCoBorrowerNoCheckbox().isEnabled());
    }


}
