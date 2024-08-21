package apiGetUserTDD;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DeleteUserTDD {
    @BeforeClass
    public static void setupClass() {
        RestAssured.baseURI = "http://qa-duobank.us-east-2.elasticbeanstalk.com/api";
    }
    @Test
            public void deleteUser(){
        given().
                header("accept", "*/*").
                queryParam("api_key", "c8a912d7d1c5a5a99c508f865b5eaae14a5b484f5bfe2d8f48c40e46289b47f3").
                queryParam("id", "13634").
                when().
                log().all().
                post("/user").
                then().
                log().all().
                statusCode(422).
                body("message", equalTo("User deleted successfully"));
    }
                      //Verify Deletion
    //•	The user should not be able to access their account after it has been deleted.
                      @Test
                      public void getUser2() {

                          given().
                                  header("Accept", "application/json").
                                  queryParam("api_key", "c8a912d7d1c5a5a99c508f865b5eaae14a5b484f5bfe2d8f48c40e46289b47f3").
                                  queryParam("id", "13634").
                                  when().
                                  log().all().
                                  get("/user").
                                  then().
                                  log().all().
                                  statusCode(404).
                                  body("message", equalTo("User not found"));

                      }

}
