package stepDefinitions.db;

import io.cucumber.java.en.Then;
import org.junit.Assert;
import utilities.DBUtils;
import utilities.SeleniumUtils;

import java.util.List;
import java.util.Map;

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


    @Then("{string} table should not duplicate {string}.")
    public void table_should_not_duplicate(String tableName, String key) {

        String query = String.format("SELECT %s from %s",
                key,
                tableName);

        System.out.println("Query: " + query);

        List<Map<String, Object>> queryResult = DBUtils.getQueryResultListOfMaps(query);
        System.out.println("Query Result: " + queryResult);

        List<String> duplicateString = SeleniumUtils.findDuplicateString(queryResult, key);

        System.out.println("Duplicate emails: " + duplicateString);

        Assert.assertTrue(duplicateString.isEmpty());


    }
}
