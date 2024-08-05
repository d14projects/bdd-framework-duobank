package pages;

import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertTrue;

@Data
public class NavigationToEconsentPage {
    public NavigationToEconsentPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//input[@id='b_firstName']")
    private WebElement firstNameField;

    @FindBy(xpath = "//input[@id='b_lastName']")
    private WebElement lastNameField;

    @FindBy(xpath = "//input[@id='b_email']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@id='b_ssn']")
    private WebElement ssnField;

    @FindBy(xpath = "(//span[@class='select2-selection select2-selection--single'])[4]")
    private WebElement maritalStatusOption;
    @FindBy(xpath = "//li[contains(@class, 'select2-results__option') and text()='Single']")
    private WebElement singleOption;

    @FindBy(xpath = "//input[@id='b_cell']")
    private WebElement cellphoneField;

    @FindBy(xpath = "//label[@for='privacypolicy']")
    private WebElement privacyCheckbox;

    @FindBy(linkText = "Next")
    private WebElement nextButton;


    @FindBy(id = "monthlyrentalpayment")
    private WebElement monthlyRentalPaymentField;
    @FindBy(linkText = "Next")
    private WebElement nextButton2;


    @FindBy(xpath = "//input[@id='employername1']")
    private WebElement employerNameField;
    @FindBy(xpath = "//input[@id='start_date1']")
    private WebElement startDate;
    @FindBy(xpath = "//input[@id='grossmonthlyincome']")
    private WebElement grossMonthlyIncomeField;
    @FindBy(linkText = "Next")
    private WebElement nextButton3;


    @FindBy(linkText = "Next")
    private WebElement nextButton4;


    @FindBy(id = "eConsentdeclarerFirstName")
    private WebElement firstName;
    @FindBy(id = "eConsentdeclarerLastName")
    private WebElement lastName;
    @FindBy(id = "eConsentdeclarerEmail")
    private WebElement email;
    @FindBy(linkText = "Next")
    private WebElement nextButton5;
    @FindBy(id = "eConsentdeclarerFirstName-error")
    private WebElement errorMessageForRequiredFields;
    @FindBy(id = "eConsentdeclarerEmail-error")
    private WebElement invalidEmailMessage;
    @FindBy(xpath = "//div[@class='eConsentTextdiv']")
    private WebElement disclosure;
    @FindBy(xpath = "//label[@class='custom-control-label' and @for='agree']")
    private WebElement agreeRadioButton;
    @FindBy(xpath = "//label[@class='custom-control-label' and @for='dontagree']")
    private WebElement disagreeRadioButton;
    @FindBy(xpath = "//a[@href='#next']")
    private WebElement nextButton6;
    @FindBy(xpath = "//label[@id='consentagree-error']")
    private WebElement errorMessage;

    public void personalInfo(Map<String, String> personalInfo) throws InterruptedException {
        firstNameField.sendKeys(personalInfo.get("First Name"));
        lastNameField.sendKeys(personalInfo.get("Last Name"));
        emailField.sendKeys(personalInfo.get("Email"));
        ssnField.sendKeys(personalInfo.get("SSN"));
        maritalStatusOption.click();
        singleOption.click();
        cellphoneField.sendKeys(personalInfo.get("Cell Phone"));
        privacyCheckbox.click();
        Thread.sleep(2000);
        nextButton.click();
    }

    public void expenses() throws InterruptedException {
        monthlyRentalPaymentField.sendKeys("3000");
        Thread.sleep(2000);
        nextButton2.click();
    }

    public void employment() {
        employerNameField.sendKeys("Bank");
        startDate.sendKeys("05/25/2020");
        grossMonthlyIncomeField.sendKeys("5000");
        nextButton3.click();
    }

    public void creditReport() {
        nextButton4.click();
    }

    public void submitFormWithoutRequiredFields() {
        nextButton5.click();
    }

    public boolean isErrorDisplayedForRequiredFields() {
        try {
            return errorMessageForRequiredFields.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void submitFormWithInvalidEmail(String invalidEmail) {
        firstName.sendKeys("John");
        lastName.sendKeys("Doe");
        email.sendKeys(invalidEmail);
    }

    public boolean isErrorDisplayedForEmail() {
        try {
            return invalidEmailMessage.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void econsentInfoEnter() {
        // firstName.sendKeys("Yana");
        //lastName.sendKeys("Vlas");
        email.sendKeys("janus@mail.ru");
    }

    public boolean isDisclosureDisplayed() {
        try {
            return disclosure.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isAgreeRadioButtonDisplayed() {
        try {
            return agreeRadioButton.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isDisagreeRadioButtonDisplayed() {
        try {
            return disagreeRadioButton.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isAgreeButtonSelected() {
        return agreeRadioButton.isSelected();
    }
    public void clickDissagreeButton() {
        disagreeRadioButton.click();
    }
    public boolean isNextButtonUnabled() {
        nextButton6.click();
        String currentUrl = Driver.getDriver().getCurrentUrl();
        String expectedUrl = "http://qa-duobank.us-east-2.elasticbeanstalk.com/mortgage.php";
        return currentUrl.equals(expectedUrl);
    }

    public boolean isErrorMessageDisplayed() {
        return errorMessage.isDisplayed();
    }

    public void clickAgree(){
        agreeRadioButton.click();
    }
    public void clickNext(){
        nextButton6.click();
    }

   public void clickDissagreeButton2() {
       disagreeRadioButton.click();
   }
    public boolean amIOnManePage(){
        String currentUrl = Driver.getDriver().getCurrentUrl();
        String expectedUrl = "http://qa-duobank.us-east-2.elasticbeanstalk.com/dashboard.php";
        return currentUrl.equals(expectedUrl);
    }
}

