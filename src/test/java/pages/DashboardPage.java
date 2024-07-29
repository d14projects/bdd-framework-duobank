package pages;

import lombok.Data;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.ConfigReader;
import utilities.Driver;

@Data
public class DashboardPage {
    public DashboardPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//input[@type='email']")
    private WebElement email2;
    @FindBy(xpath = "//input[@type='password']")
    private WebElement password2;
    @FindBy(xpath = "//button[@name='login']")
    private WebElement signInButton;

    @FindBy(linkText = "DuoBank")
    private WebElement logo;
    @FindBy(linkText = "Mortgage Application")
    private WebElement mortgageApplicationLink;
    @FindBy(linkText = "Application List")
    private WebElement applicationListLink;

    @FindBy(linkText = "Yana Vlasyuk")
    private WebElement accountInfo;
    @FindBy(xpath = "//img[@alt='avatar']")
    private WebElement profilePic;
    @FindBy(css="a.dropdown-item[href='logout.php']")
    private WebElement logoutLink;


    public void login() {
        email2.sendKeys(ConfigReader.getProperty("email2"));
        password2.sendKeys(ConfigReader.getProperty("password2"));
        signInButton.click();
    }

    public boolean isLogoDisplayed() {
        return logo.isDisplayed();
    }

    public boolean isMortgageApplicationLinkDisplayed() {
        return mortgageApplicationLink.isDisplayed();
    }

    public boolean isApplicationListLinkDisplayed() {
        return applicationListLink.isDisplayed();
    }

    public void clickMortgageApplicationLink() {
        mortgageApplicationLink.click();
    }

    public void clickApplicationListLink() {
        applicationListLink.click();
    }

    public boolean isOnMortgageApplicationPage() {
        String expectedUrl = "http://qa-duobank.us-east-2.elasticbeanstalk.com/mortgage.php";
        String currentUrl = Driver.getDriver().getCurrentUrl();
        return currentUrl.contains(expectedUrl);
    }

    public boolean isOnApplicationListPage() {
        String expectedUrl = "http://qa-duobank.us-east-2.elasticbeanstalk.com/applications.php";
        String currentUrl = Driver.getDriver().getCurrentUrl();
        return currentUrl.contains(expectedUrl);
    }
    public boolean isaAccountInfoDisplayed() {
       return accountInfo.isDisplayed() && profilePic.isDisplayed();
    }
    public void profilePictureClick() {
        profilePic.click();
    }
    public void logout(){
            if (logoutLink.isDisplayed()) {
                logoutLink.click();
            } else {
                System.out.println("Logout link is not displayed.");
            }
    }
    public boolean amILoggedOut(){
            String currentUrl = Driver.getDriver().getCurrentUrl();
            String expectedUrl = "http://qa-duobank.us-east-2.elasticbeanstalk.com/index.php";
            return currentUrl.equals(expectedUrl);
    }
}

