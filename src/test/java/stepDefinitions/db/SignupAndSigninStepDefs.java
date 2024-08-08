package stepDefinitions.db;

import io.cucumber.java.en.Then;
import org.junit.Assert;
import utilities.DBUtils;

import java.util.List;

public class SignupAndSigninStepDefs {

    @Then("{string} table should have the following columns:")
    public void table_should_have_the_following_columns(String tableName, List<String> expectedColumnNames) {

        String query = String.format("SELECT * from %s", tableName);
        System.out.println("Query: " + query);

        List<String> actualColumnNames = DBUtils.getColumnNames(query);
        System.out.println("Expected Column Names: " + expectedColumnNames);

        System.out.println("Expected Column Names: "+ actualColumnNames);
        Assert.assertEquals(expectedColumnNames, actualColumnNames);

    }
}
