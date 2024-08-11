package pages;

import lombok.Data;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

@Data
public class LoginPageUS2 extends BasePage {

    @FindBy(id = "exampleInputEmail1")
    private WebElement emailField;

    @FindBy(id = "exampleInputPassword1")
    private WebElement passwordField;

    @FindBy(name = "login")
    private WebElement signInButton;

private String emailValid = "jglob@gmail.com";
private String passwordValid = "Password1";

    public void login(){
        emailField.sendKeys(emailValid);
        passwordField.sendKeys(passwordValid);
        signInButton.click();
    }

    public void login(String email, String password){
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        signInButton.click();
    }

    @FindBy(xpath = "//div[@class='card-header pb-1']")
    private WebElement loginFailedError;

}
