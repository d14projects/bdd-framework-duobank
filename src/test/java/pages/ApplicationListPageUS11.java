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

}
