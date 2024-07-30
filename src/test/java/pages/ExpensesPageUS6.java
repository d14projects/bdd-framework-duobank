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

    @FindBy(xpath = "//div[@class='steps clearfix']//a[@id='steps-uid-0-t-2']")
    private WebElement stepExpenses;

    @FindBy(xpath = "//h6[@class='py-50' and contains(text(),'Current Monthly Housing Expenses')]")
    private WebElement headTitle;

}
