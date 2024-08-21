package apiGetUserTDD;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.matcher.RestAssuredMatchers.*;
import org.hamcrest.Matchers.*;
import java.util.List;
import static io.cucumber.core.internal.com.fasterxml.jackson.databind.type.LogicalType.Map;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.*;

public class GetUserTDD {
    @BeforeClass
    public static void setupClass() {
        RestAssured.baseURI = "http://qa-duobank.us-east-2.elasticbeanstalk.com/api";
        //ConfigReader.getProperty("api.key");
    }

    @Test
    public void getUser() {
        /*The API endpoint should require authentication via API key.*/
        given().
                header("Accept", "application/json").
                queryParam("per_page", "10").
                queryParam("api_key", "c8a912d7d1c5a5a99c508f865b5eaae14a5b484f5bfe2d8f48c40e46289b47f3").
                when().
                log().all().
                get("/users").
                then().
                log().all().
                statusCode(200);


    }

    @Test
    public void getUserWithoutAPIKey() {
        /*•	 If API key is not provided,401 Unauthorized status code with error message should be displayed.*/
        given().
                header("Accept", "application/json").
                queryParam("per_page", "10").
                when().
                log().all().
                get("/users").
                then().
                log().all().
                statusCode(401).
                body("message", equalTo("Invalid or missing API Key. Provide a valid api key with 'api_key' query parameter."));
    }

    @Test
    public void getJsonResponse() {
        /*•	The API endpoint should return a JSON response.*/

        JsonPath jsonResponse = given().
                header("Accept", "application/json").
                queryParam("api_key", "c8a912d7d1c5a5a99c508f865b5eaae14a5b484f5bfe2d8f48c40e46289b47f3").
                when().
                log().all().
                get("/users").
                then().
                log().all().
                statusCode(200).
                extract().jsonPath();
        System.out.println(jsonResponse.prettyPrint());


    }

    @Test
    public <Map> void getAllUserData() {
        /*The response should include all user data stored in the application's database.*/
        given().
                header("Accept", "application/json").
                queryParam("api_key", "c8a912d7d1c5a5a99c508f865b5eaae14a5b484f5bfe2d8f48c40e46289b47f3").
                when().
                log().all().
                get("/users").
                then().
                log().all().
                statusCode(200).
                body("id", everyItem(notNullValue())).
                body("email", everyItem(notNullValue())).
                body("password", everyItem(notNullValue())).
                body("first_name", everyItem(notNullValue())).
                body("last_name", everyItem(notNullValue())).
                body("phone", everyItem(notNullValue())).
                body("image", everyItem(notNullValue())).
                body("type", everyItem(notNullValue())).
                body("created_at", everyItem(notNullValue())).
                body("modified_at", everyItem(notNullValue())).
                body("zone_id", everyItem(notNullValue())).
                body("church_id", everyItem(notNullValue())).
                body("country_id", everyItem(notNullValue())).
                body("active", everyItem(notNullValue()));
    }

    @Test
    public void checkNoUsersFoundResponse() {
        // •	If there are no users in the database, the API endpoint should return a 404 HTTP response code and a JSON response with an error message "No users found".
        given()
                .header("Accept", "application/json")
                .queryParam("api_key", "c8a912d7d1c5a5a99c508f865b5eaae14a5b484f5bfe2d8f48c40e46289b47f3")
                .when()
                .log().all()
                .get("/users")
                .then()
                .log().all()
                .statusCode(404)
                .body("message", equalTo("No users found"));//HOW TO IMPLEMENT THIS?
    }

    @Test
    public void requestIsNotGet() {
        // •	If the HTTP request method is not GET, the API endpoint should return a 405 HTTP response code and a JSON response with an error message "Invalid request method".
        given()
                .header("Accept", "application/json")
                .queryParam("api_key", "c8a912d7d1c5a5a99c508f865b5eaae14a5b484f5bfe2d8f48c40e46289b47f3")
                .when()
                .log().all()
                .patch("/users")
                .then()
                .log().all()
                .statusCode(405)  // Expecting 404 Not Found
                .body("message", equalTo("Invalid request method"));
    }

    @Test
    public void checkResponseContainsOnlyRelevantFields() {
        // •	Retrieved response should include only relevant fields such as 'id', 'email', 'first_name', 'last_name', 'phone', 'image', 'type', 'created_at', 'modified_at', 'zone_id', 'church_id', 'country_id', and 'active'.
        List<String> expectedFields = List.of(
                "id", "email", "first_name", "last_name", "phone", "image",
                "type", "created_at", "modified_at", "zone_id", "church_id", "country_id", "active"
        );
        given().
                header("Accept", "application/json").
                queryParam("api_key", "c8a912d7d1c5a5a99c508f865b5eaae14a5b484f5bfe2d8f48c40e46289b47f3").
                when().
                log().all().
                get("/users").
                then().
                log().all().
                statusCode(200).
                body("$", everyItem(hasKey("id"))).
                body("$", everyItem(hasKey("email"))).
                body("$", everyItem(hasKey("first_name"))).
                body("$", everyItem(hasKey("last_name"))).
                body("$", everyItem(hasKey("phone"))).
                body("$", everyItem(hasKey("image"))).
                body("$", everyItem(hasKey("type"))).
                body("$", everyItem(hasKey("created_at"))).
                body("$", everyItem(hasKey("modified_at"))).
                body("$", everyItem(hasKey("zone_id"))).
                body("$", everyItem(hasKey("church_id"))).
                body("$", everyItem(hasKey("country_id"))).
                body("$", everyItem(hasKey("active")));

    }

    @Test
    public void checkNoSensitiveInformationReturned() {//TEST FAILED, PASSWORD IS RETURNED
        // •	The API should not return the user's password or any other sensitive information.

               given().
                header("Accept", "application/json").
                queryParam("api_key", "c8a912d7d1c5a5a99c508f865b5eaae14a5b484f5bfe2d8f48c40e46289b47f3").
                when().
                log().all().
                get("/users").
                then().
                log().all().
                statusCode(200).
                body("$", everyItem(not(hasKey("password")))).
                body("$", everyItem(not(hasKey("social_security_number")))).
                body("$", everyItem(not(hasKey("credit_card_number"))));
    }
    @Test
    public void checkResponseIsArrayOfJsonObjects() {
        // •	The API endpoint should return all user data as an array of JSON objects.
        given().
                header("Accept", "application/json").
                queryParam("api_key", "c8a912d7d1c5a5a99c508f865b5eaae14a5b484f5bfe2d8f48c40e46289b47f3").
                when().
                log().all().
                get("/users").
                then().
                log().all().
                statusCode(200).
                body("$", isA(List.class));

    }
}

