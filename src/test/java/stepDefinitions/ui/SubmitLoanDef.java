package stepDefinitions.ui;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import java.util.List;
import java.util.Map;

import lombok.Data;
import pages.NavigationToEconsentPage;
import pages.PersonalInfoPageDB;
import stepDefinitions.SharedData;

@Data
public class SubmitLoanDef {
    SharedData sharedData;


    public SubmitLoanDef(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    @And("the user enters the following borrower information")

    public void theUserEntersTheFollowingBorrowerInformation(DataTable borrowerData) {
        List<Map<String, String>> data = borrowerData.asMaps(String.class, String.class);
        PersonalInfoPageDB page = new PersonalInfoPageDB(sharedData);
        for (Map<String, String> row : data) {
            page.enterPersonalInfo(row);
        }
    }

    @And("the user enters the following co-borrower information")
    public void theUserEntersTheFollowingCoBorrowerInformation(DataTable coBorrowerData) {
        List<Map<String, String>> data = coBorrowerData.asMaps(String.class, String.class);
        PersonalInfoPageDB page2 = new PersonalInfoPageDB(sharedData);
        for (Map<String, String> row : data) {
            page2.enterCoBorrowerInformation(row);

        }
    }
    @And("click the save application")
    public void clickTheSaveApplication() {
        new NavigationToEconsentPage().saveApplication();
    }
}
