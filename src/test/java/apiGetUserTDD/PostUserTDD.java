package apiGetUserTDD;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class PostUserTDD {
    @BeforeClass
    public static void setupClass() {
        RestAssured.baseURI = "http://qa-duobank.us-east-2.elasticbeanstalk.com/api";
    }

    @Test
    public void postUserWithInvalidPassword() {
        /*••	The API should return a 422 Unprocessable Entity error with a message indicating that the password is invalid if the password:
o	is less than 8 characters long,
o	does not contain at least one uppercase character,
o	does not contain at least one lowercase character,
o	does not contain at least one number, or
o	does not contain at least one special symbol (!@#$%^&*()-_=+{};:,<.>).
*/
        Faker faker=new Faker();
        String userName=faker.name().username();
        String firstName=faker.name().firstName();
        String lastName =faker.name().lastName();
        String email=faker.internet().emailAddress();
        String password=faker.internet().password();


        String formatted=String.format("""
               {"username": "%s",
               "first_name": "%s",
               "last_name": "%s",
               "email": "%s",
               "password": "%s"
               }
               """,userName, firstName, lastName, email, password);
        System.out.println(formatted);
        given().

                header("accept", "*/*").
                header("Content-Type", "application/json").
                queryParam("api_key", "c8a912d7d1c5a5a99c508f865b5eaae14a5b484f5bfe2d8f48c40e46289b47f3").
                body(formatted).
                when().
                log().all().
                post("/user").
                then().
                log().all().
                statusCode(422);
    }

    @Test
    public void postUserWithInvalidName() {
        /*•	•	The API should return a 422 Unprocessable Entity error
         with a message indicating that the first name is invalid if the first name is less than 2 characters long*/
        String firstName="Y";
        Faker faker=new Faker();
        String userName=faker.name().username();
       String lastName =faker.name().lastName();
       String email=faker.internet().emailAddress();
        String password=faker.internet().password(12,  16, true, true, true);


        String formatted=String.format("""
               {"username": "%s",
               "first_name": "%s",
               "last_name": "%s",
               "email": "%s",
               "password": "%s"
               }
               """,userName, firstName, lastName, email, password);
       System.out.println(formatted);
        given().
                body(formatted).
                header("Accept", "application/json").
                queryParam("api_key", "c8a912d7d1c5a5a99c508f865b5eaae14a5b484f5bfe2d8f48c40e46289b47f3").
                when().
                log().all().
                post("/user").
                then().
                log().all().
                statusCode(422).
                body("message", equalTo("First name must be at least 2 characters long!"));


    }

    @Test
    public void postUserWithExistingEmail() {
        /*••	•	The API should return a 422 Unprocessable Entity error with a message indicating
         that the email address is already in use if the email address already exists in the database.)
*/
        String email="herc@mail.com";
        Faker faker=new Faker();
        String userName=faker.name().username();
        String firstName=faker.name().firstName();
        String lastName =faker.name().lastName();
        String password=faker.internet().password(12,  16, true, true, true);


        String formatted=String.format("""
               {"username": "%s",
               "first_name": "%s",
               "last_name": "%s",
               "email": "%s",
               "password": "%s"
               }
               """,userName, firstName, lastName, email, password);
        System.out.println(formatted);
        given().

                header("accept", "*/*").
                header("Content-Type", "application/json").
                queryParam("api_key", "c8a912d7d1c5a5a99c508f865b5eaae14a5b484f5bfe2d8f48c40e46289b47f3").
                body(formatted).
                when().
                log().all().
                post("/user").
                then().
                log().all().
                statusCode(422).
                body("message", equalTo("This e-mail is already in use!"));
    }
    @Test
    public void postUser() {
        /*The API should create a new user account in the database if all validation checks pass and return a
         201 Created response with the following data:
o	status: 1
o	http_code: 201
o	message: 'The user has been created.'
o	user_id: the ID of the newly created user account.
*/

        Faker faker=new Faker();
        String userName=faker.name().username();
        String firstName=faker.name().firstName();
        String lastName =faker.name().lastName();
        String email=faker.internet().emailAddress();
        String password=faker.internet().password(8,  16, true, true, true);


        String formatted=String.format("""
               {"username": "%s",
               "first_name": "%s",
               "last_name": "%s",
               "email": "%s",
               "password": "%s"
               }
               """,userName, firstName, lastName, email, password);
        System.out.println(formatted);
        given().

                header("accept", "*/*").
                header("Content-Type", "application/json").
                queryParam("api_key", "c8a912d7d1c5a5a99c508f865b5eaae14a5b484f5bfe2d8f48c40e46289b47f3").
                body(formatted).
                when().
                log().all().
                post("/user").
                then().
                log().all().
                statusCode(201).
                body("status", equalTo(1)).
                body("http_code", equalTo(201)).
                body("message", equalTo("The user has been created."));

    }
}

