package pages;

import com.github.javafaker.Faker;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import utilities.Driver;
import utilities.SeleniumUtils;

import java.util.List;
import java.util.Random;

@EqualsAndHashCode(callSuper = true)
@Data
public class SummaryPageUS10 extends BasePage {

    @FindBy(xpath = "//span[@class='menu-item'][.='Mortgage Application']")
    private WebElement mortgageApplicationLink;

    @FindBy(id = "steps-uid-0-t-6")
    private WebElement summaryLink;

    @FindBy(id = "steps-uid-0-t-0")
    private WebElement preapprovalDetailsLink;

    @FindBy(id = "steps-uid-0-t-1")
    private WebElement personalInfoLink;

    @FindBy(id = "steps-uid-0-t-2")
    private WebElement expensesLink;

    @FindBy(id = "steps-uid-0-t-3")
    private WebElement employmentLink;

    @FindBy(id = "steps-uid-0-t-4")
    private WebElement creditReportLink;

    @FindBy(id = "steps-uid-0-t-5")
    private WebElement eConsentLink;


    @FindBy(xpath = "//label[@for='realtor2']")
    private WebElement realtorNoCheckbox;

    @FindBy(xpath = "//label[@for='loanofficer2']")
    private WebElement loanOfficerNoCheckbox;

    @FindBy(xpath = "//select[@class='select2 form-control select2-hidden-accessible']")
    private WebElement dropdownPreapprovalPage;

    @FindBy(name = "est_purchase_price")
    private WebElement estimatedPurchasePriceField;

    @FindBy(xpath = "//div[@class='form-group']//input[@id='downpayment']")
    private WebElement downPaymentField;

    @FindBy(name = "add_fund_available")
    private WebElement addtFundsField;

    @FindBy(linkText = "Next")
    private WebElement nextButtonPreapproval;

    @FindBy(xpath = "//label[@for='coborrower2']")
    private WebElement coBorrowerNoCheckbox;

    @FindBy(xpath = "//input[@id='b_firstName']")
    private WebElement firstNameField;

    @FindBy(xpath = "//input[@id='b_lastName']")
    private WebElement lastNameField;

    @FindBy(xpath = "//input[@id='b_email']")
    private WebElement emailField;

    @FindBy(id = "b_dob")
    private WebElement dobField;

    @FindBy(xpath = "//input[@id='b_ssn']")
    private WebElement ssnField;

    @FindBy(xpath = "//select[@id='b_marital']")
    private WebElement maritalStatusDropdown;

    @FindBy(xpath = "//input[@id='b_cell']")
    private WebElement cellphoneField;

    @FindBy(xpath = "//label[@for='privacypolicy']")
    private WebElement privacyCheckbox;

    @FindBy(linkText = "Next")
    private WebElement nextButtonPersonal;

    @FindBy(xpath = "//label[@for='expense1']")
    private WebElement checkboxRent;

    @FindBy(xpath = "//label[@for='expense2']")
    private WebElement checkboxOwn;

    @FindBy(id = "monthlyrentalpayment")
    private WebElement rentalPaymentField;

    @FindBy(id = "firtmortagagetotalpayment")
    private WebElement mortgagePaymentField;

    @FindBy(linkText = "Next")
    private WebElement nextButtonExpenses;

    @FindBy(id = "employername1")
    private WebElement employerNameField;

    @FindBy(id = "start_date1")
    private WebElement startDate;

    @FindBy(id = "grossmonthlyincome")
    private WebElement grossMonthlyIncomeField;

    @FindBy(linkText = "Next")
    private WebElement nextButtonEmployment;

    @FindBy(xpath = "//label[@for='creditreport2']")
    private WebElement checkboxNoCreditReport;

    @FindBy(linkText = "Next")
    private WebElement nextButtonCredit;

    @FindBy(id = "eConsentdeclarerFirstName")
    private WebElement eConsentFirstName;

    @FindBy(id = "eConsentdeclarerLastName")
    private WebElement eConsentLastName;

    @FindBy(id = "eConsentdeclarerEmail")
    private WebElement eConsentEmail;

    @FindBy(linkText = "Next")
    private WebElement nextButtonEConsent;

    public void fillOutTillEConsentPage() {
        preapprovalDetailsLink.click();
        realtorNoCheckbox.click();
        loanOfficerNoCheckbox.click();
        new Select(dropdownPreapprovalPage).selectByVisibleText("Purchase a Home");
        estimatedPurchasePriceField.sendKeys(""+(new Random().nextInt(500000)+300000));
        downPaymentField.sendKeys(""+(new Random().nextInt(50000)+20000));
        addtFundsField.sendKeys("0");
        nextButtonPreapproval.click();

        coBorrowerNoCheckbox.click();

        String firstName = new Faker().name().firstName();
        firstNameField.sendKeys(firstName);

        String lastName = new Faker().name().lastName();
        lastNameField.sendKeys(lastName);

        String email = new Faker().internet().emailAddress();
        emailField.sendKeys(email);

        dobField.sendKeys("01/01/1980");
        ssnField.sendKeys("234343456");
        new Select(maritalStatusDropdown).selectByVisibleText("Single");
        cellphoneField.sendKeys("5555555555");
        privacyCheckbox.click();
        nextButtonPersonal.click();

        checkboxOwn.click();
        mortgagePaymentField.sendKeys("2000");
        nextButtonExpenses.click();
        employerNameField.sendKeys("Global");
        startDate.sendKeys("10/4/2017");
        grossMonthlyIncomeField.sendKeys("7000");
        nextButtonEmployment.click();
        checkboxNoCreditReport.click();
        nextButtonCredit.click();

        eConsentFirstName.sendKeys(firstName);
        eConsentLastName.sendKeys(lastName);
        eConsentEmail.sendKeys(email);
        nextButtonEConsent.click();
        SeleniumUtils.waitFor(1);
    }

    public void fillOutTillEConsentPage(String price, String downPayment, String additionalFunds, String firstName,
                                        String lastName, String email, String dob, String ssn, String cell, String mortgagePaymentAmount,
                                        String employer, String emplStartDate, String monthlyIncome) {
        preapprovalDetailsLink.click();
        realtorNoCheckbox.click();
        loanOfficerNoCheckbox.click();
        new Select(dropdownPreapprovalPage).selectByVisibleText("Purchase a Home");
        estimatedPurchasePriceField.sendKeys(price);
        downPaymentField.sendKeys(downPayment);
        addtFundsField.sendKeys(additionalFunds);
        nextButtonPreapproval.click();
        coBorrowerNoCheckbox.click();
        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        emailField.sendKeys(email);
        dobField.sendKeys(dob);
        ssnField.sendKeys(ssn);
        new Select(maritalStatusDropdown).selectByVisibleText("Single");
        cellphoneField.sendKeys(cell);
        privacyCheckbox.click();
        nextButtonPersonal.click();
        checkboxOwn.click();
        mortgagePaymentField.sendKeys(mortgagePaymentAmount);
        nextButtonExpenses.click();
        employerNameField.sendKeys(employer);
        startDate.sendKeys(emplStartDate);
        grossMonthlyIncomeField.sendKeys(monthlyIncome);
        nextButtonEmployment.click();
        checkboxNoCreditReport.click();
        nextButtonCredit.click();
        eConsentFirstName.sendKeys(firstName);
        eConsentLastName.sendKeys(lastName);
        eConsentEmail.sendKeys(email);
        nextButtonEConsent.click();
    }

    @FindBy(xpath = "//h6[@class='pb-50']")
    private List<WebElement> expectedSections;

    public boolean isLabelDisplayed(String label) {
        List<WebElement> allElements = Driver.getDriver().
                findElements(By.xpath("//h6[contains(text(),'"+label+"')]"));
        for (WebElement each : allElements) {
            if (each.isDisplayed()) {
                return true;
            }
        }
        System.out.println("Label: \"" + label + "\" is not displayed");
        return false;
       }

    @FindBy(id = "PreApprovalEdit")
    private WebElement preApprovalEditButton;

    @FindBy(id = "PersonalnformationEdit")
    private WebElement personalInfoEditButton;

    @FindBy(id = "ExpenseEdit")
    private WebElement expenseEditButton;

    @FindBy(id = "EmploymentIncomeEdit")
    private WebElement employmentIncomeEditButton;

    @FindBy(id = "OrderCreditEdit")
    private WebElement creditReportEditButton;

    @FindBy(id = "eConsentEdit")
    private WebElement eConsentEditButton;

    @FindBy(linkText = "Save")
    private WebElement submitButton;

    @FindBy(xpath = "//div[.='Application Submiited Successfully']")
    private WebElement submitSuccessMessage;
}
