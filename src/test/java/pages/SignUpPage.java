package pages;

import com.github.javafaker.Faker;
import lombok.Data;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

@Data
public class SignUpPage {

    public SignUpPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

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

    public void createNewUser(){
        firstNameField.sendKeys(new Faker().name().firstName());
        lastNameField.sendKeys(new Faker().name().lastName());
        emailField.sendKeys(new Faker().internet().emailAddress());
        passwordField.sendKeys(new Faker().internet().password(8, 10, true, false, true));
        registerButton.click();
    }







}
