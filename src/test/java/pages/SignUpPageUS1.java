package pages;

import com.github.javafaker.Faker;
import lombok.Data;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;
import utilities.SeleniumUtils;

@Data
public class SignUpPageUS1 extends BasePage {

    @FindBy (linkText = "Sign up")
    private WebElement signUpLink;

    @FindBy (id = "inputfirstname4")
    private WebElement firstNameField;

    @FindBy (id = "inputlastname4")
    private WebElement lastNameField;

    @FindBy (id = "email")
    private WebElement emailField;

    @FindBy (id = "exampleInputPassword1")
    private WebElement passwordField;

    @FindBy (id = "register")
    private WebElement registerButton;

    @FindBy (id = "emailerror")
    private WebElement emailError;

    public void fillAllFields(){
        firstNameField.sendKeys(new Faker().name().firstName());
        lastNameField.sendKeys(new Faker().name().lastName());
        emailField.sendKeys(new Faker().internet().emailAddress());
        passwordField.sendKeys(new Faker().internet().password(8, 10, true, false, true));
    }

    public void fillAllFields(String first, String last, String email, String password){
        firstNameField.sendKeys(first);
        lastNameField.sendKeys(last);
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
    }


private String signInPageTitle = "Login - Duobank URLA (Uniform Residential Loan Application) Mortgage Application";
private String signUpPageTitle = "Register - Create an Account";

    @FindBy (xpath = "//small [.='Already have an account?']//parent::div")
    private WebElement alreadyHaveAccountField;

    @FindBy (xpath = "//div[@class='text-center']//a")
    private WebElement alreadyHaveAccountLink;




}
