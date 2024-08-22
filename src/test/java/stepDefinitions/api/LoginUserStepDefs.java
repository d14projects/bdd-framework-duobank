package stepDefinitions.api;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import pojo.LoginUser;
import pojo.User;
import stepDefinitions.SharedData;

import java.util.*;

public class LoginUserStepDefs {

    SharedData sharedData;

    public LoginUserStepDefs(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    @And("the request body is set to the following payload as")
    public void theRequestBodyIsSetToTheFollowingPayloadAs(String payload) {

        sharedData.getRequestSpecification().body(payload);

    }

    @Given("the request body is set to the following fields")
    public void the_request_body_is_set_to_the_following_fields(List<Map <String, String>> fields) {

        User user = User.builder()
                .email(fields.get(0).get("email"))
                .password(fields.get(0).get("password"))
                .build();

        sharedData.getRequestSpecification().body(user);
    }


    @Then("the response payload should be in the correct format")
    public void the_response_payload_should_be_in_the_correct_format(Map<String, String> expectedPayload) {

        LoginUser responseBody = sharedData.getResponse().then().extract().as(LoginUser.class);

        String actualSuccess = responseBody.getSuccess();
        String actualMessage = responseBody.getMessage();
        String actualAccessToken = responseBody.getAccess_token();
        String actualTokenType = responseBody.getToken_type();
        Integer actualExpiresIn = responseBody.getExpires_in();

        Integer expectedExpiresIn = Integer.parseInt(expectedPayload.get("expires_in"));

        Assert.assertEquals(expectedPayload.get("success"), actualSuccess);
        Assert.assertEquals(expectedPayload.get("message"), actualMessage);
        Assert.assertNotNull(actualAccessToken);
        Assert.assertEquals(expectedPayload.get("token_type"), actualTokenType);
        Assert.assertEquals(expectedExpiresIn, actualExpiresIn);
    }



}
