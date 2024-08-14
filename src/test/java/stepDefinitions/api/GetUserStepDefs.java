package stepDefinitions.api;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import stepDefinitions.SharedData;
import utilities.ConfigReader;

import java.util.Map;
import java.util.Set;

public class GetUserStepDefs {

    SharedData sharedData;

    public GetUserStepDefs(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    @Given("the request is authenticated with a valid API key")
    public void the_request_is_authenticated_with_a_valid_api_key() {

        sharedData.getRequestSpecification().
                queryParam("api_key", ConfigReader.getProperty("api.key"));
    }

    @Given("the request {string} header is set to {string}")
    public void the_request_header_is_set_to(String key, String value) {

        sharedData.getRequestSpecification().
                header(key, value);
    }

    @Given("the request {string} query parameter is set to {string}")
    public void the_request_query_parameter_is_set_to(String key, String value) {

        sharedData.getRequestSpecification().
                queryParam(key, value);
    }

    @When("I send a {string} request to the endpoint {string}")
    public void i_send_a_request_to_the_endpoint(String requestType, String endpoint) {

        switch (requestType) {
            case "GET" -> sharedData.setResponse(sharedData.getRequestSpecification().when().log().all().get(endpoint));
            case "POST" ->
                    sharedData.setResponse(sharedData.getRequestSpecification().when().log().all().post(endpoint));
            case "PUT" -> sharedData.setResponse(sharedData.getRequestSpecification().when().log().all().put(endpoint));
            case "PATCH" ->
                    sharedData.setResponse(sharedData.getRequestSpecification().when().log().all().patch(endpoint));
            case "DELETE" ->
                    sharedData.setResponse(sharedData.getRequestSpecification().when().log().all().delete(endpoint));
        }
    }

    @Then("the response log should be displayed")
    public void the_response_log_should_be_displayed() {

            sharedData.getResponse().then().log().all();
    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(Integer expectedStatusCode) {

        sharedData.getResponse().then().
                statusCode(expectedStatusCode);
    }

    @Then("the response {string} header should be {string}")
    public void the_response_header_should_be(String key, String value) {

        sharedData.getResponse().then().
                header(key, value);

    }

    @Then("the response time should be less than {int} ms")
    public void the_response_time_should_be_less_than_ms(Integer time) {
        sharedData.getResponse().then().
                time(lessThan((long) time));

    }

    @Then("the response body should have {string} field with value {string}")
    public void the_response_body_should_have_field_with_value(String key, String value) {

        sharedData.getResponse().then().
                body(key, equalTo(value));

    }

    @Then("the response body should not contain {string} property")
    public void the_response_body_should_not_contain_property(String expectedProperty) {

        Map <String, Object> map = sharedData.getResponse().then().
                extract().as(new TypeRef<>() {});

        Set<String> keySet = map.keySet();
        for (String actualProperty : keySet) {
            Assert.assertNotEquals(expectedProperty, actualProperty );
        }

    }

}
