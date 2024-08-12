package pages;

import lombok.Data;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import stepDefinitions.SharedData;
import utilities.Driver;

import java.util.Map;
@Data
public class PersonalInfoPageDB {

        SharedData sharedData;

        public PersonalInfoPageDB(SharedData sharedData) {
            this.sharedData = sharedData;
            PageFactory.initElements(Driver.getDriver(), this);
        }


        @FindBy(xpath = "//label[@for='coborrower1']")
        private WebElement coBorrowerYesCheckbox;
        @FindBy(xpath = "//input[@id='b_firstName']")
        private WebElement firstNameField;
        @FindBy(xpath = "//input[@id='b_middleName']")
        private WebElement middleNameField;
        @FindBy(xpath = "//input[@id='b_lastName']")
        private WebElement lastNameField;
        @FindBy(xpath = "//input[@id='b_email']")
        private WebElement emailField;
        @FindBy(xpath = "//input[@id='b_dob']")
        private WebElement dateOfBirth;
        @FindBy(xpath = "//input[@id='b_ssn']")
        private WebElement ssnField;
        @FindBy(xpath = "(//span[@class='select2-selection select2-selection--single'])[4]")
        private WebElement maritalStatusOption;
        @FindBy(xpath = "//li[contains(@class, 'select2-results__option') and text()='Single']")
        private WebElement singleOption;
        @FindBy(xpath = "//input[@id='b_cell']")
        private WebElement cellphoneField;
        @FindBy(xpath = "//input[@id='b_home']")
        private WebElement homePhoneField;
        @FindBy(xpath = "//label[@for='privacypolicy']")
        private WebElement privacyCheckbox;


        @FindBy(xpath = "//input[@id='c_firstName']")
        private WebElement firstNameField2;
        @FindBy(xpath = "//input[@id='c_middleName']")
        private WebElement middleNameField2;
        @FindBy(xpath = "//input[@id='c_lastName']")
        private WebElement lastNameField2;
        @FindBy(xpath = "//input[@id='c_email']")
        private WebElement emailField2;
        @FindBy(xpath = "//input[@id='c_dob']")
        private WebElement dateOfBirth2;
        @FindBy(xpath = "//input[@id='c_ssn']")
        private WebElement ssnField2;
        @FindBy(xpath = "(//span[@class='select2-selection select2-selection--single'])[6]")
        private WebElement maritalStatusOption2;
        @FindBy(xpath = "//li[contains(@class, 'select2-results__option') and text()='Single']")
        private WebElement singleOption2;
        @FindBy(xpath = "//input[@id='c_cell']")
        private WebElement cellphoneField2;
        @FindBy(xpath = "//input[@id='c_home']")
        private WebElement homePhoneField2;
        @FindBy(linkText = "Next")
        private WebElement nextButton;

    private boolean isValidName(String name) {
        return name.matches("[a-zA-Z ]+");
    }

    // Validation for email format
    private boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    // Validation for marital status
    private boolean isValidMaritalStatus(String maritalStatus) {
        return maritalStatus.equals("Single") || maritalStatus.equals("Married") || maritalStatus.equals("Divorced");
    }

        public void enterPersonalInfo(Map<String, String> borrowerInfo) {
        String firstName = borrowerInfo.get("b_firstName");
        String middleName = borrowerInfo.get("b_middleName");
        String lastName = borrowerInfo.get("b_lastName");
        String email = borrowerInfo.get("b_email");
        String maritalStatus = borrowerInfo.get("b_marital");


            if (!isValidName(firstName) || !isValidName(middleName) || !isValidName(lastName)) {
                throw new IllegalArgumentException("Names must contain only alphabetical characters and spaces.");
            }

            if (!isValidEmail(email)) {
                throw new IllegalArgumentException("Invalid email format.");
            }
            if (!isValidMaritalStatus(maritalStatus)) {
                throw new IllegalArgumentException("Marital status must be 'Single', 'Married', or 'Divorced'.");
            }

            sharedData.setFirstName(firstName);
            sharedData.setMiddleName(middleName);
            sharedData.setLastName(lastName);
            sharedData.setEmail(email);
            sharedData.setDob(borrowerInfo.get("b_dob"));
            sharedData.setSsn(borrowerInfo.get("b_ssn"));
            sharedData.setMarital(borrowerInfo.get("b_marital"));
            sharedData.setCellPhone(borrowerInfo.get("b_cell"));
            sharedData.setHomePhone(borrowerInfo.get("b_home"));

            firstNameField.sendKeys(sharedData.getFirstName());
            middleNameField.sendKeys(sharedData.getMiddleName());
            lastNameField.sendKeys(sharedData.getLastName());
            emailField.sendKeys(sharedData.getEmail());
            dateOfBirth.sendKeys(sharedData.getDob());
            ssnField.sendKeys(sharedData.getSsn());
            maritalStatusOption.click();
            singleOption.click();
            cellphoneField.sendKeys(sharedData.getCellPhone());
            homePhoneField.sendKeys(sharedData.getHomePhone());
            privacyCheckbox.click();

        }

        public void enterCoBorrowerInformation(Map<String, String> coBorrowerInfo) {
            sharedData.setFirstName(coBorrowerInfo.get("c_firstName"));
            sharedData.setMiddleName(coBorrowerInfo.get("c_middleName"));
            sharedData.setLastName(coBorrowerInfo.get("c_lastName"));
            sharedData.setEmail(coBorrowerInfo.get("c_email"));
            sharedData.setDob(coBorrowerInfo.get("c_dob"));
            sharedData.setSsn(coBorrowerInfo.get("c_ssn"));
            sharedData.setMarital(coBorrowerInfo.get("c_marital"));
            sharedData.setCellPhone(coBorrowerInfo.get("c_cell"));
            sharedData.setHomePhone(coBorrowerInfo.get("c_home"));

            firstNameField2.sendKeys(sharedData.getFirstName());
            middleNameField2.sendKeys(sharedData.getMiddleName());
            lastNameField2.sendKeys(sharedData.getLastName());
            emailField2.sendKeys(sharedData.getEmail());
            dateOfBirth2.sendKeys(sharedData.getDob());
            ssnField2.sendKeys(sharedData.getSsn());
            maritalStatusOption2.click();
            singleOption2.click();
            cellphoneField2.sendKeys(sharedData.getCellPhone());
            homePhoneField2.sendKeys(sharedData.getHomePhone());
            nextButton.click();
        }
    }


