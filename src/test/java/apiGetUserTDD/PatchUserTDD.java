package apiGetUserTDD;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PatchUserTDD {
    @BeforeClass
    public static void setupClass() {
        RestAssured.baseURI = "http://qa-duobank.us-east-2.elasticbeanstalk.com/api";
    }

    @Test
    public void patchUser() {
     /*•---•The API should be able to update the user information specified in the request body.
      ---- The API should respond with a 200 OK status code and a success message if the user information was updated successfully.*/

        Faker faker = new Faker();
        String newFirstName = faker.name().firstName();


        given().
                body(String.format("""
                        {
                        "first_name": "%s"
                        }
                        """, newFirstName)).
                header("accept", "*/*").
                header("Content-Type", "application/json").
                queryParam("api_key", "c8a912d7d1c5a5a99c508f865b5eaae14a5b484f5bfe2d8f48c40e46289b47f3").
                queryParam("id", "13634").
                when().
                log().all().
                patch("/user").
                then().
                log().all().
                statusCode(200).
                body("message", equalTo("User updated successfully"));


        //-------Verify update
        given().
                header("accept", "*/*").
                queryParam("api_key", "c8a912d7d1c5a5a99c508f865b5eaae14a5b484f5bfe2d8f48c40e46289b47f3").
                queryParam("id", "13634").
                when().
                log().all().
                patch("/user").
                then().
                log().all().
                statusCode(200).
                body("first_name", equalTo(newFirstName));

    }

    @Test
    public void patchUserError() {
     /*•---••	The API should respond with a 500 Internal Server Error status code and an error message
     if there was an error updating the user information in the database.*/

        Faker faker = new Faker();
        String newUserName = faker.name().username();
        String newFirstName = faker.name().firstName();


        given().
                body(String.format("""
                        {
                        "username" : "%s",
                        "first_name": "%s"
                        }
                        """, newUserName, newFirstName)).
                header("accept", "*/*").
                header("Content-Type", "application/json").
                queryParam("api_key", "c8a912d7d1c5a5a99c508f865b5eaae14a5b484f5bfe2d8f48c40e46289b47f3").
                queryParam("id", "13634").
                when().
                log().all().
                patch("/user").
                then().
                log().all().
                statusCode(500).
                body("message", equalTo("Unknown column 'username' in 'field list'"));

    }}

