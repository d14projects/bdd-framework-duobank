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

    @FindBy(xpath = "//input[@id='coborrower1']")
    private WebElement coBorrowerYesCheckbox;
    @FindBy(id = "coborrower2")
    private WebElement coBorrowerNoCheckbox;

}
