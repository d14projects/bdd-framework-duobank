package apiGetUserTDD;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class PutUserTDD {
    @BeforeClass
    public static void setupClass() {
        RestAssured.baseURI = "http://qa-duobank.us-east-2.elasticbeanstalk.com/api";
    }

    @Test
    public void putUser() {//TEST FAILED-INTERNAL SERVER ERROR
        /*•---If the user information is successfully updated in the database, the API responds with a
              200 OK status code and a success message indicating that the user was updated successfully.
         ----The API endpoint updates the modified_at field in the database to reflect the
              date and time the user information was last modified.
        -----The API must return the Content-Type header as "application/json".
        -----The response time for a single user request should be less than 500ms*/

        Faker faker = new Faker();
        String userName = faker.name().username();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        //String password = faker.internet().password(8, 16, true, true, true);


        String formatted = String.format("""
                {"username": "%s",
                "first_name": "%s",
                "last_name": "%s",
                "email": "%s"
                }
                """, userName, firstName, lastName, email);
        System.out.println(formatted);

        Response response=given().
                body(formatted).
                header("accept", "*/*").
                header("Content-Type", "application/json").
                queryParam("api_key", "c8a912d7d1c5a5a99c508f865b5eaae14a5b484f5bfe2d8f48c40e46289b47f3").
                queryParam("id", "13634").
                when().
                log().all().
                put("/user").
                then().
                log().all().
                statusCode(200).
                body("message", equalTo("User was updated successfully")).
                header("Content-Type", equalTo("application/json")).
                extract().
                response();

        long responseTime = response.time();
        String modifiedAt = response.path("modified_at");


          System.out.println("Response time: " + responseTime );
          System.out.println("The user was last modified at: " + modifiedAt);
         assertThat("Response time is too long!", responseTime,lessThan(5000L));


    }

    }

