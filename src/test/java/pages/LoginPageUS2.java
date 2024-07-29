package pages;

import lombok.Data;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

@Data
public class LoginPageUS2 {

    public LoginPageUS2(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id = "exampleInputEmail1")
    private WebElement emailField;

    @FindBy(id = "exampleInputPassword1")
    private WebElement passwordField;

    @FindBy(name = "login")
    private WebElement signInButton;

private String emailAddress = "jglob@gmail.com";
private String passwordValid = "Password1";

    public void login(){
        emailField.sendKeys(emailAddress);
        passwordField.sendKeys(passwordValid);
        signInButton.click();
    }

}
