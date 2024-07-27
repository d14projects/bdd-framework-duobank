package pages;
import lombok.Data;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;
@Data
public class PersonalInformationPage {
    public PersonalInformationPage(){PageFactory.initElements(Driver.getDriver(),this); }

    @FindBy(xpath = "//label[@for='proposalTitle1']")
    private WebElement proposalTitle1;

    @FindBy(xpath = "//label[@for='coborrower1']")
    private WebElement coBorrowerYesCheckbox;
    @FindBy(id = "coborrower2")
    private WebElement coBorrowerNoCheckbox;
    @FindBy(className = "co-borrower")
    private WebElement coborrowerSection;

    @FindBy (xpath = "//label[@id='b_firstName-error']")
    private WebElement firstNameError;

    @FindBy(xpath = "//input[@id='b_firstName']")
    private WebElement firstNameField;

    @FindBy(xpath = "//input[@id='b_lastName']")
    private WebElement lastNameField;

    @FindBy (xpath = "//label[@id='b_lastName-error']")
    private WebElement lastNameError;

    @FindBy (xpath = "//input[@id='b_email']")
    private WebElement emailField;

    @FindBy (xpath = "//label[@id='b_email-error']")
    private WebElement emailFieldError;

    @FindBy (xpath = "//label[@id='b_marital-error']")
    private WebElement maritalStatusDropdownError;

    @FindBy (xpath = "//select[@id='b_marital']")
    private WebElement maritalStatusDropdown;

    @FindBy (xpath = "//label[@id='b_cell']")
    private WebElement cellphoneFieldError;

    @FindBy (xpath = "//input[@id='b_cell']")
    private WebElement cellphoneField;



    @FindBy (xpath = "//label[@id='c_firstName-error']")
    private WebElement cb_firstNameError;

    @FindBy(xpath = "//input[@id='c_firstName']")
    private WebElement cb_firstNameField;

    @FindBy(xpath = "//input[@id='c_lastName']")
    private WebElement cb_lastNameField;

    @FindBy (xpath = "//label[@id='c_lastName-error']")
    private WebElement cb_lastNameError;

    @FindBy (xpath = "//input[@id='c_email']")
    private WebElement cb_emailField;

    @FindBy (xpath = "//label[@id='c_email-error']")
    private WebElement cb_emailFieldError;

    @FindBy (xpath = "//label[@id='c_marital-error']")
    private WebElement cb_maritalStatusDropdownError;

    @FindBy (xpath = "//select[@id='c_marital']")
    private WebElement cb_maritalStatusDropdown;

    @FindBy (xpath = "//label[@id='c_cell']")
    private WebElement cb_cellphoneFieldError;

    @FindBy (xpath = "//input[@id='c_cell']")
    private WebElement cb_cellphoneField;

    @FindBy(xpath = "//input[@id='b_ssn']")
    private WebElement ssnField;

    @FindBy(xpath = "//input[@id='c_ssn']")
    private WebElement cb_ssnField;

    @FindBy(xpath = "//label[@id='b_ssn-error']")
    private WebElement ssnFieldError;
    @FindBy(xpath = "//label[@id='c_ssn-error']")
    private WebElement cb_ssnFieldError;

    @FindBy(linkText = "Next")
    private WebElement nextButton;



}
