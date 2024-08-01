package pages;

import io.cucumber.java.en.Then;
import lombok.Data;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utilities.Driver;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;


@Data
public class ApplicationPage {
    public ApplicationPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//label[@for='firstName12']")
    private WebElement realtor;
    @FindBy(xpath = "//label[@for='realtor1']")
    private WebElement yesButton;
    @FindBy(xpath = "//label[@for='realtor2']")
    private WebElement noButton;

    @FindBy(id = "realtorinfo")
    private WebElement realtorInfoLine;

    @FindBy(xpath = "//label[@for='firstName12']")
    private WebElement loanOfficer;
    @FindBy(xpath = "//label[@for='loanofficer1']")
    private WebElement yesButton2;
    @FindBy(xpath = "//label[@for='loanofficer2']")
    private WebElement noButton2;


    @FindBy(css = ".select2-selection")
    private WebElement loanField;

    @FindBy(id = "estimatedprice")
    private WebElement estimatedPrice;
    @FindBy(id = "downpayment")
    private WebElement downPayment;
    @FindBy(id = "downpaymentpercentage")
    private WebElement downPaymentPercentage;

    //@FindBy(css = ".select2-selection")
    @FindBy(xpath = "(//span[@class='select2-selection select2-selection--single'])[2]")
    private WebElement sourceOfDownPaymentOptionsTab;

    @FindBy(id = "additionalfunds")
    private WebElement additionalPrice;

    @FindBy(xpath = "//a[@role='menuitem' and contains(@class, 'btn-light-primary') and text()='Next']")
    private WebElement nextButton;

    @FindBy(xpath = "//label[@for='proposalTitle1']")
    private WebElement firstNameVisible;

    public boolean checkBoxForRealtor() {
        return realtor.isDisplayed() && yesButton.isDisplayed() && noButton.isDisplayed();
    }

    public void clickYesButton() {
        yesButton.click();
    }

    public boolean realtorInfoUnabled() {
        return realtorInfoLine.isEnabled();
    }

    public void clickNoButton() {
        noButton.click();
    }

    public boolean realtorInfoDisabled() {
        return !realtorInfoLine.isSelected();
    }

    public boolean checkBoxForLoanOfficer() {
        return loanOfficer.isDisplayed() && yesButton2.isDisplayed() && noButton2.isDisplayed();
    }

    public List<String> LoanFieldOptions() {
        loanField.click();
        List<WebElement> options = Driver.getDriver().findElements(By.xpath(("//ul[contains(@class, 'select2-results__options')]/li")));
        List<String> optionsText = new ArrayList<>();
        for (WebElement option : options) {
            optionsText.add(option.getText());
        }

        return optionsText;
    }


    public void estimatedPurchasePrice(String amount) {
        estimatedPrice.sendKeys(amount);
    }

    public String getEstimatedPrice() {
        return estimatedPrice.getAttribute("value");
    }

    public void downPayment(String amount2) {
        downPayment.sendKeys(amount2);
    }

    public String getDownPayment() {
        return downPayment.getAttribute("value");

    }

    public double getDownPaymentPercentage() {
        String downPaymentText = downPaymentPercentage.getAttribute("value");
        return Double.parseDouble(downPaymentText);
    }

    public double calculatedDownPaymentPercentage() {
        double downPaymentAmount = Double.parseDouble(getDownPayment());
        double estimatedPriceAmount = Double.parseDouble(getEstimatedPrice());
        return (downPaymentAmount / estimatedPriceAmount) * 100;
    }


public List<String> sourceOfDownPaymentOptions() throws InterruptedException {
        sourceOfDownPaymentOptionsTab.click();
        List<WebElement> options2 = Driver.getDriver().findElements(By.xpath(("//ul[contains(@class, 'select2-results__options')]/li")));
       List<String> optionsText2 = new ArrayList<>();
       for (WebElement option2 : options2) {
            optionsText2.add(option2.getText());
        }
    Thread.sleep(3000);
        return optionsText2;
    }
    public void additionalFunds(String amount) {
        additionalPrice.sendKeys(amount);
    }

    public String getAdditionalPrice() {
        return additionalPrice.getAttribute("value");
    }

    public void clickNextButton() {
        nextButton.click();
    }

    public boolean isNextPageLoaded() {
        return realtor.isDisplayed();
        //String currentUrl = Driver.getDriver().getCurrentUrl();
        //String expectedUrl = "http://qa-duobank.us-east-2.elasticbeanstalk.com/mortgage.php";
        // return currentUrl.equals(expectedUrl);

    }

    public void fillOutForm() {
        noButton.click();
        yesButton2.click();
        estimatedPrice.sendKeys("5000.00");
        downPayment.sendKeys("1000.00");
        additionalPrice.sendKeys("1000.00");
        nextButton.click();
    }

    public boolean isNextPageLoaded2() {
        return firstNameVisible.isDisplayed();
        //String currentUrl=Driver.getDriver().getCurrentUrl();
        // String expectedUrl="http://qa-duobank.us-east-2.elasticbeanstalk.com/mortgage.php";
        // return currentUrl.equals(expectedUrl);
    }

}





