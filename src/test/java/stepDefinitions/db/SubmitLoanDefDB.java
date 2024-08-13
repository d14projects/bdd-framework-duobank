package stepDefinitions.db;

import io.cucumber.java.en.Then;
import io.cucumber.messages.types.DataTable;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import pages.NavigationToEconsentPage;
import stepDefinitions.SharedData;
import utilities.DBUtils;

import java.util.List;
import java.util.Map;

public class SubmitLoanDefDB {
    SharedData sharedData;

    public SubmitLoanDefDB(SharedData sharedData) {
        this.sharedData = sharedData;
    }
    private boolean isValidName(String name) {
        return name.matches("[a-zA-Z ]+");
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }


    @Then("the borrower and co-borrower information should be stored in the {string} table")
    public void theBorrowerAndCoBorrowerInformationShouldBeStoredInTheTable(String tableName) {

        if (!isValidName(sharedData.getFirstName()) || !isValidName(sharedData.getMiddleName()) || !isValidName(sharedData.getLastName())) {
            throw new IllegalArgumentException("Names must contain only alphabetical characters and spaces.");
        }
        if (!isValidEmail(sharedData.getEmail())) {
            throw new IllegalArgumentException("Invalid email format.");
        }

        DBUtils.createConnection();
         // String query = String.format("select * from %s where b_email='%s'",tableName,  sharedData.getEmail());
            String query="SELECT  b_firstName, b_middleName, b_lastName, b_email, b_dob, b_ssn, b_marital, b_cell, b_home FROM tbl_mortagage where b_email='john.doe11@email.com'";
        System.out.println("Query: " + query);
        //String query="SELECT  * FROM tbl_mortagage where b_firstName='Johnn'";
        System.out.println("Query: " + query);

            List<Map<String, Object>> queryResultListOfMaps = DBUtils.getQueryResultListOfMaps(query);
            System.out.println("Actual result: " + queryResultListOfMaps);

            SoftAssertions softAssertions = new SoftAssertions();
            Map<String, Object> data=queryResultListOfMaps.get(0);
            //Assert.assertFalse(queryResultListOfMaps.isEmpty());
        softAssertions.assertThat(data.get("b_firstName")).isEqualTo(sharedData.getFirstName());
        softAssertions.assertThat(data.get("b_middleName")).isEqualTo(sharedData.getMiddleName());
        softAssertions.assertThat(data.get("b_lastName")).isEqualTo(sharedData.getLastName());
        softAssertions.assertThat(data.get("b_email")).isEqualTo(sharedData.getEmail());
        softAssertions.assertThat(data.get("b_dob")).isEqualTo(sharedData.getDob());
        softAssertions.assertThat(data.get("b_ssn")).isEqualTo(sharedData.getSsn());
        softAssertions.assertThat(data.get("b_marital")).isEqualTo(sharedData.getMarital());
        softAssertions.assertThat(data.get("b_cell")).isEqualTo(sharedData.getCellPhone());
        softAssertions.assertThat(data.get("b_home")).isEqualTo(sharedData.getHomePhone());
        softAssertions.assertAll();
       DBUtils.close();
    }
}
