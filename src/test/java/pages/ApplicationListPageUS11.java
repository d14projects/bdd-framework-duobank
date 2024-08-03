package pages;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ApplicationListPageUS11 extends BasePage{

    @FindBy(name = "DataTables_Table_0_length")
    private WebElement dropdownApplicationList;

    @FindBy(xpath = "//tbody//tr[@role='row']")
    private List<WebElement> rows;

    @FindBy(xpath = "//thead//tr[@role='row']")
    private WebElement headerColumns;

    @FindBy(xpath = "//input[@type='search']")
    private WebElement searchBox;

    @FindBy(xpath = "//a[@class='btn btn-info']")
    private List<WebElement> viewDetailsButtons;

    @FindBy(xpath = "//a[@href='dashboard.php']")
    private WebElement dashboardLink;

    @FindBy(xpath = "//td[@colspan='2']")
    private WebElement nameViewDetails;

    @FindBy(xpath = "//th[.='Loan ID']")
    private WebElement loanIDSort;

    @FindBy(xpath = "//th[.='Borrower Name']")
    private WebElement borrowerSort;

    @FindBy(xpath = "//th[.='Loan Amount']")
    private WebElement amountSort;



}
