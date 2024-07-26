package pages;

import lombok.Data;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;
@Data
public class PreapprovalProcessPageUS5 {
    public PreapprovalProcessPageUS5(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(id = "realtor1")
    private WebElement realtorYesCheckbox;

    @FindBy(id = "realtor1")
    private WebElement realtorNoCheckbox;

    @FindBy(id = "realtorinfo")
    private WebElement realtorNameAndPhoneField;

    @FindBy(id = "loanofficer1")
    private WebElement loanOfficerYesCheckbox;

    @FindBy(id = "loanofficer2")
    private WebElement loanOfficerNoCheckbox;

    @FindBy(xpath = "//option[@data-select2-id='3']")
    private WebElement purposePurchaseHome;

    @FindBy(name = "est_purchase_price")
    private WebElement estimatedPurchasePriceField;

    @FindBy(xpath = "//div[@class='form-group']//input[@id='downpayment']")
    private WebElement downPaymentField;

    @FindBy(name = "down_payment_percent")
    private WebElement downPaymentPercentageField;

    @FindBy(xpath = "//select[@name='src_down_payment']")
    private WebElement sourceOfDownPayment;

    @FindBy(name = "add_fund_available")
    private WebElement addtFundsField;

    @FindBy(linkText = "Next")
    private WebElement nextButton;

}
