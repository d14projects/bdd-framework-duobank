package pages;

import lombok.Data;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

@Data
public class ExpensesPageUS6 {
    public ExpensesPageUS6(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//div[@class='checkbox checkbox-primary checkbox-glow']//input[@id='realtor1']")
    private WebElement checkboxRent;

    @FindBy(xpath = "//div[@class='checkbox checkbox-danger checkbox-glow']//input[@id='expense2']")
    private WebElement checkboxOwn;


}
