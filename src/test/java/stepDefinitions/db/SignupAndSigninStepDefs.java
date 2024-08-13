package stepDefinitions.db;

import com.github.javafaker.Faker;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.codec.digest.DigestUtils;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import pages.LoginPageUS2;
import pages.SignUpPageUS1;
import pages.SummaryPageUS10;
import stepDefinitions.SharedData;
import utilities.DBUtils;
import utilities.Driver;
import utilities.SeleniumUtils;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SignupAndSigninStepDefs {

    SharedData sharedData;

    public SignupAndSigninStepDefs(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    @Then("{string} table should have the following columns:")
    public void table_should_have_the_following_columns(String tableName, List<String> expectedColumnNames) {

        String query = String.format("SELECT * from %s", tableName);
//        System.out.println("Query: " + query);

        List<String> actualColumnNames = DBUtils.getColumnNames(query);
//        System.out.println("Expected Column Names: " + expectedColumnNames);
//
//        System.out.println("Expected Column Names: "+ actualColumnNames);
        Assert.assertEquals(expectedColumnNames, actualColumnNames);
    }


    @Then("{string} table should not contain duplicate {string} addresses")
    public void table_should_not_duplicate(String tableName, String key) {

        String query = String.format("SELECT %s from %s",
                key,
                tableName);

//        System.out.println("Query: " + query);

        List<Map<String, Object>> queryResult = DBUtils.getQueryResultListOfMaps(query);
//        System.out.println("Query Result: " + queryResult);

        List<String> duplicateString = SeleniumUtils.findDuplicateString(queryResult, key);

//        System.out.println("Duplicate emails: " + duplicateString);

        Assert.assertTrue(duplicateString.isEmpty());
    }

    @When("the user creates a new user record in {string} table with the already existing email address {string}")
    public void the_user_creates_a_new_user_record_with_the_already_existing_email_address(String tableName, String email) throws SQLException {

        Faker faker = new Faker();
        sharedData.setEmailDBUS1(email);
        sharedData.setFirstNameDBUS1(faker.name().firstName());

        String query =
String.format("INSERT INTO %s (email, password, first_name, last_name, phone, image, type, created_at, modified_at,  zone_id, church_id, country_id, active)\n" +
                                     "VALUES ('%s', '2ac9cb7dc02b3c0083eb70898e549b63', '%s','Starling', '555-555-5555', 'assets/images/profile-pics/head_', '1', '%s', '', '0', '0', '0', '1')",
        tableName, sharedData.getEmailDBUS1(), sharedData.getFirstNameDBUS1(), LocalDateTime.now());

//        System.out.println("Query sent to db: " + query);

        DBUtils.executeUpdate(query);

          }


    @Then("the db {string} table should not store the new user record")
    public void the_db_should_not_store_the_new_user_record(String tableName) throws SQLException {

        SoftAssertions softAssertions = new SoftAssertions();

        String query = String.format("SELECT * from %s where email='%s' and first_name='%s'",
               tableName, sharedData.getEmailDBUS1(), sharedData.getFirstNameDBUS1());

        List<List<Object>> queryResult = DBUtils.getQueryResultAsListOfLists(query);

//        System.out.println("Query sent to db: " + query);
//        System.out.println("Actual Query Result: " + queryResult);

        softAssertions.assertThat(queryResult.isEmpty()).isTrue();

        if (!queryResult.isEmpty()) {
            String queryDelete = String.format("DELETE from %s where email='%s' and first_name='%s'",
                    tableName, sharedData.getEmailDBUS1(), sharedData.getFirstNameDBUS1());

            DBUtils.executeUpdate(queryDelete);
        }

        softAssertions.assertAll();
    }

    @When("the user enters the new user credentials and clicks Sign In button")
    public void the_user_enters_the_new_user_credentials_and_clicks_sign_in_button() {

        new LoginPageUS2().login(sharedData.getEmailDBUS1(), sharedData.getPasswordDBUS1());

    }

    @When("The user fills up the fields with valid info")
    public void the_user_fills_up_the_fields_with_valid_info() {

        Faker faker = new Faker();
        sharedData.setFirstNameDBUS1(faker.name().firstName());
        sharedData.setLastNameDBUS1(faker.name().lastName());
        sharedData.setEmailDBUS1(faker.internet().emailAddress());
        sharedData.setPasswordDBUS1("Password1");

    new SignUpPageUS1().fillAllFields( sharedData.getFirstNameDBUS1(),
                                       sharedData.getLastNameDBUS1(),
                                       sharedData.getEmailDBUS1(),
                                       sharedData.getPasswordDBUS1()
                                      );

        System.out.println("Email at sign up" + sharedData.getEmailDBUS1());
//    SeleniumUtils.waitFor(2);
    }

    @Then("The user should be able to sign up successfully")
    public void the_user_should_be_able_to_sign_up_successfully() {

        new SignUpPageUS1().getRegisterButton().click();
        SeleniumUtils.waitFor(1);
        Assert.assertTrue(Driver.getDriver().getPageSource().contains("Registration Successful"));
        SeleniumUtils.waitFor(2);

        String expectedPageTitle = new SignUpPageUS1().getSignInPageTitle();
        String actualPageTitle = Driver.getDriver().getTitle();
        Assert.assertEquals(expectedPageTitle, actualPageTitle);
    }

    @Then("the user should be created in the {string} table in the database")
    public void the_user_should_be_created_in_the_database(String tableName) {

        String query = String.format("SELECT * from %s where email='%s'",
                tableName,
                sharedData.getEmailDBUS1());

//        System.out.println("Query for Read: " + query);

        List<Map<String, Object>> queryResult = DBUtils.getQueryResultListOfMaps(query);
//        System.out.println("Query Result for Read: " + queryResult);

        Map<String, Object> map = queryResult.get(0);

        sharedData.setId_DBUS1( (Integer) map.get("id"));

        Assert.assertTrue(!queryResult.isEmpty());

    }

    @Then("the data should be mapped correctly to the following columns in the {string} table:")
    public void the_data_should_be_mapped_correctly_to_the_following_columns_in_the_table(String tableName, List<String> expectedColumns) {

        SoftAssertions softAssertions = new SoftAssertions();

        StringBuilder columnNames = new StringBuilder();

        expectedColumns.forEach(s -> columnNames.append(s).append(","));
        columnNames.deleteCharAt(columnNames.length()-1);

        String query = String.format("SELECT %s from %s where email='%s'",
                columnNames,
                tableName,
                sharedData.getEmailDBUS1());

//        System.out.println("Query for Mapping: " + query);

        List<Map<String, Object>> queryResult = DBUtils.getQueryResultListOfMaps(query);
//        System.out.println("Query Result for Mapping: " + queryResult);

//        System.out.println("Expected Columns: " + expectedColumns);

        Map<String, Object> map = queryResult.get(0);

        List<String> actualColumns = new ArrayList<>(map.keySet());
        String actualFirstName = (String) map.get("first_name");
        String actualLastName = (String) map.get("last_name");
        String actualemail = (String) map.get("email");
        String actualPassword = (String) map.get("password");

        String expectedPasswordMD5 = DigestUtils.md5Hex(sharedData.getPasswordDBUS1());

        softAssertions.assertThat(expectedColumns).isEqualTo(actualColumns);
        softAssertions.assertThat(sharedData.getFirstNameDBUS1()).isEqualTo(actualFirstName);
        softAssertions.assertThat(sharedData.getLastNameDBUS1()).isEqualTo(actualLastName);
        softAssertions.assertThat(sharedData.getEmailDBUS1()).isEqualTo(actualemail);
        softAssertions.assertThat(expectedPasswordMD5).isEqualTo(actualPassword);

        softAssertions.assertAll();
    }


    @Then("{string} the unique identifier for the user should be autogenerated by the database in the {string} table")
    public void id_the_unique_identifier_for_the_user_should_be_autogenerated_by_the_database(String columnName, String tableName) throws SQLException {

        // VERIFY AUTO-GENERATION
        String query = String.format("SELECT %s from %s where email='%s'",
                columnName,
                tableName,
                sharedData.getEmailDBUS1());

//        System.out.println("Query for Auto-generation: " + query);

        List<Map<String, Object>> queryResult = DBUtils.getQueryResultListOfMaps(query);
//        System.out.println("Query Result for ID Auto-Gen: " + queryResult);

        Assert.assertTrue(!queryResult.isEmpty());


        // VERIFY ID UNIQUE

        String queryID = String.format("SELECT * from %s where id='%s'",
                tableName,
                sharedData.getId_DBUS1());

//        System.out.println("Query for ID: " + queryID);

        List<Map<String, Object>> queryResultID = DBUtils.getQueryResultListOfMaps(queryID);

//        System.out.println("Query Result for ID: " + queryResultID);
//        System.out.println("Size equal to: " +  queryResultID.size());

        Assert.assertEquals(1, queryResultID.size());
        Assert.assertNotNull(sharedData.getId_DBUS1());

    }

    @Then("{string} table should store a timestamp of when the user account was created in the {string} column")
    public void table_should_store_a_timestamp_of_when_the_user_account_was_created(String tableName, String timeStampColumn) {

        String query = String.format("SELECT %s from %s where id='%s'",
                timeStampColumn,
                tableName,
                sharedData.getId_DBUS1());

//        System.out.println("Query for timestamp: " + query);

        List<Map<String, Object>> queryResult = DBUtils.getQueryResultListOfMaps(query);
//        System.out.println("Query Result for Time Stamp: " + queryResult);

        String expectedTimeStamp = LocalDateTime.parse(queryResult.get(0).get(timeStampColumn).toString(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
//        System.out.println("Expected Time Stamp: " + expectedTimeStamp);

        String actualTimeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
//        System.out.println("Actual Time Stamp: " + actualTimeStamp);

        Assert.assertEquals(expectedTimeStamp, actualTimeStamp);

        Assert.assertTrue(!queryResult.isEmpty());
    }

    @Then("{string} table should store and encrypt user passwords in an MD5 hash in the {string} column")
    public void table_should_store_and_encrypt_user_passwords_in_an_md5_hash(String tableName, String passwordColumn) throws SQLException {

        String query = String.format("SELECT %s from %s where id='%s'",
                passwordColumn,
                tableName,
                sharedData.getId_DBUS1());

//        System.out.println("Query for password: " + query);

        List<Map<String, Object>> queryResult = DBUtils.getQueryResultListOfMaps(query);
//        System.out.println("Query Result for password: " + queryResult);

        Map<String, Object> map = queryResult.get(0);

        String expectedPasswordMD5 = DigestUtils.md5Hex(sharedData.getPasswordDBUS1());

//        System.out.println("Actual Password: " + map.get(passwordColumn));
//        System.out.println("Expected Password: " + expectedPasswordMD5);

        Assert.assertEquals(expectedPasswordMD5, map.get(passwordColumn));

        //DELETE ENTRY FROM DATABASE
        if (!queryResult.isEmpty()) {
            String queryDelete = String.format("DELETE from %s where id='%s'",
                    tableName, sharedData.getId_DBUS1());

            DBUtils.executeUpdate(queryDelete);
        }
    }

    @When("the user completed and submits Mortgage Application")
    public void the_user_completed_and_submits_mortgage_application() {

        new SummaryPageUS10().getMortgageApplicationLink().click();

        Faker faker = new Faker();
        sharedData.setZoneID(0);
        sharedData.setType(2);
        sharedData.setPhone("5555555555");
        sharedData.setModifiedAt("");
        sharedData.setImage("");
        sharedData.setCreatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        sharedData.setCountryID(0);
        sharedData.setChurchID(0);
        sharedData.setActive(1);

        new SummaryPageUS10().fillOutTillEConsentPage("100000", "20000", "1000",
                sharedData.getFirstNameDBUS1(), sharedData.getLastNameDBUS1(),sharedData.getEmailDBUS1(),
                "01/01/1980", "234343456", sharedData.getPhone(), "2000", "Duotech",
                "10/4/2017", "5000");

        new SummaryPageUS10().getSubmitButton().click();
        SeleniumUtils.waitFor(1);

        System.out.println("Email at appplication " + sharedData.getEmailDBUS1());
    }

    @Then("{string} columns should correctly display corresponding user information:")
    public void columns_should_correctly_display_corresponding_user_information(String tableName, List<String> expectedColumns) throws SQLException {

        System.out.println(sharedData.getEmailDBUS1());

        String query = String.format("SELECT * from %s where email='%s'",
                tableName,
                sharedData.getEmailDBUS1());

        System.out.println("Query for User-INFO: " + query);

        List<Map<String, Object>> queryResult = DBUtils.getQueryResultListOfMaps(query);
        System.out.println("Query Result for USER-INFO: " + queryResult);

        Map<String, Object> map = queryResult.get(0);

        System.out.println("Map: " + map);

        sharedData.setId_DBUS1( (Integer) map.get("id"));

        System.out.println("Stored ID for User DELETION AFTER THE TEST " + sharedData.getId_DBUS1());

        SoftAssertions softAssertions = new SoftAssertions();

        String actualEmail = (String) map.get(expectedColumns.get(0));
        String actualPassword = (String) map.get(expectedColumns.get(1));
        String actualFirst_name = (String) map.get(expectedColumns.get(2));
        String actualLast_name = (String) map.get(expectedColumns.get(3));
        String actualPhone =(String) map.get(expectedColumns.get(4));
        String actualImage = (String) map.get(expectedColumns.get(5));
        Integer	actualType = (int) map.get(expectedColumns.get(6));
        String actualCreated_at = LocalDateTime.parse(map.get(expectedColumns.get(7)).toString(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                .atZone(ZoneId.of("UTC"))
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        // USe correct zone date time for remote servers

//        String actualCreated_at = LocalDateTime.parse(map.get(expectedColumns.get(7)).toString(),
//                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
//                .atZone(ZoneId.of("UTC")) // Convert to ZonedDateTime in UTC
//                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));


        String	actualModified_at = (String) map.get(expectedColumns.get(8));
        Integer actualZone_id = (int) map.get(expectedColumns.get(9));
        Integer	actualChurch_id = (int) map.get(expectedColumns.get(10));
        Integer	actualCountry_id = (int) map.get(expectedColumns.get(11));
        Integer	actualActive = (int) map.get(expectedColumns.get(12));


        softAssertions.assertThat(sharedData.getEmailDBUS1()).isEqualTo(actualEmail);
        softAssertions.assertThat(DigestUtils.md5Hex(sharedData.getPasswordDBUS1())).isEqualTo(actualPassword);
        softAssertions.assertThat(sharedData.getFirstNameDBUS1()).isEqualTo(actualFirst_name);
        softAssertions.assertThat(sharedData.getLastNameDBUS1()).isEqualTo(actualLast_name);
     //   softAssertions.assertThat(sharedData.getPhone()).isEqualTo(actualPhone);
        softAssertions.assertThat(sharedData.getImage()).isEqualTo(actualImage);
        softAssertions.assertThat(sharedData.getType()).isEqualTo(actualType);
        softAssertions.assertThat(sharedData.getCreatedAt()).isEqualTo(actualCreated_at);
        softAssertions.assertThat(sharedData.getModifiedAt()).isEqualTo(actualModified_at);
        softAssertions.assertThat(sharedData.getZoneID()).isEqualTo(actualZone_id);
        softAssertions.assertThat(sharedData.getChurchID()).isEqualTo(actualChurch_id);
        softAssertions.assertThat(sharedData.getCountryID()).isEqualTo(actualCountry_id);
        softAssertions.assertThat(sharedData.getActive()).isEqualTo(actualActive);

        if (!queryResult.isEmpty()) {
            String queryDelete = String.format("DELETE from %s where id='%s'",
                    tableName, sharedData.getId_DBUS1());

            DBUtils.executeUpdate(queryDelete);
        }

        softAssertions.assertAll();

    }


}
