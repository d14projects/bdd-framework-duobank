package pages;

import lombok.Data;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;
@Data
public class EmploymentAndIncomePageUS7 {
    public EmploymentAndIncomePageUS7(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//h6[@class='py-50' and text()='Borrower Employment Information']")
    private  WebElement headerText;
    public String getHeaderText(){
       return headerText.getText();

    }
    @FindBy(xpath = "//div[@class='Employer']//a[@id='clear1']")
    private WebElement clearButton;

    public WebElement getEmployerNameField() {
        return employerNameField;
    }

    @FindBy(xpath = "//input[@id='employername1']")
    private WebElement employerNameField;

    @FindBy(xpath = "//input[@id='position1']")
    private WebElement positionField;


    @FindBy(id = "city")
    private WebElement cityField;
    @FindBy(xpath = "//select[@id='state1']")
    private WebElement stateDropdown;

    public WebElement getStartDate() {
        return startDate;
    }

    public WebElement getEndDate() {
        return endDate;
    }

    @FindBy(xpath = "//input[@id='start_date1']")
    private WebElement startDate;
    @FindBy(xpath = "//input[@id='end_date1']")
    private WebElement endDate;


    @FindBy(xpath = "//input[@id='grossmonthlyincome']")
    private WebElement grossMonthlyIncomeField;

    @FindBy(xpath = "//input[@id='//input[@id='monthlyovertime']']")
    private WebElement monthlyOvertimeField;

    @FindBy(xpath = "//input[@id='monthlybonuses']")
    private WebElement monthlyBonusField;

    @FindBy(xpath = "//input[@id='monthlycommission']")
    private WebElement monthlyCommissionsField;

    @FindBy(xpath = "//input[@id='monthlydividents']")
    private WebElement monthlyDividentsField;

@FindBy(xpath = "//select[@id='incomesource1']")
private WebElement incomeSourceDropdown1;

    @FindBy(xpath = "//select[@id='incomesource1']")
    private WebElement incomeSourceDropdown2;

    @FindBy(xpath = "//select[@id='incomesource3']")
    private WebElement incomeSourceDropdown3;

    @FindBy(xpath = "//input[@id='amount1']")
    private WebElement amountField1;

    @FindBy(xpath = "//input[@id='amount2']")
    private WebElement amountField2;

    @FindBy(xpath = "//input[@id='amount3']")
    private WebElement amountField3;

    @FindBy(linkText = "Next")
    private WebElement nextButton;


    @FindBy(linkText = "Previous")
    private WebElement previousButton;

@FindBy(xpath = "//button[@id='addemployer']")
    private WebElement addAnotherEmployerButton;

    public WebElement getCurrentJobCheckbox() {
        return currentJobCheckbox;
    }

    @FindBy(xpath = "//label[@for='currentjob1']//following-sibling::input[@id='currentjobsls']")
    private WebElement currentJobCheckbox;

}
