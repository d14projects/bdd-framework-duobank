package stepDefinitions.api;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import pojo.User;
import stepDefinitions.SharedData;
import utilities.ConfigReader;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class DeleteUserStepDefs {

    SharedData sharedData;

    public DeleteUserStepDefs(SharedData sharedData) {
        this.sharedData = sharedData;
    }


    @Then("the response body {string} value is stored")
    public void the_response_body_value_is_stored(String key) {

        sharedData.setFieldValue( sharedData.getResponse().jsonPath().get("user_id"));

    }

    @Then("the request specification is reset")
    public void the_request_specification_is_reset() {
        sharedData.setRequestSpecification(given());
    }

    @Given("the request {string} query parameter is set to the stored value")
    public void the_request_query_parameter_is_set_to_the_stored_value(String key) {

        sharedData.getRequestSpecification().queryParam(key, sharedData.getFieldValue());
    }
}
