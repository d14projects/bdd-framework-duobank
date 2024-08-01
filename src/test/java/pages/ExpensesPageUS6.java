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
    private WebElement tabExpenses;

    @FindBy(xpath = "//h6[@class='py-50' and contains(text(),'Current Monthly Housing Expenses')]")
    private WebElement headTitle;

    @FindBy(id="monthlyrentalpayment")
    private WebElement monthlyRentalPaymentField;

    public WebElement getMonthlyrentalpaymentError() {
        return monthlyrentalpaymentError;
    }

    @FindBy(xpath = " //label[@id='monthlyrentalpayment-error']")
    private WebElement monthlyrentalpaymentError;

    @FindBy(linkText = "Next")
    private WebElement nextButton;
    @FindBy(xpath = "//div[@class='steps clearfix']//a[@id='steps-uid-0-t-3']")
    private WebElement tabEmploymentAndIncome;
}

