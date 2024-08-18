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

public class PatchUserStepDefs {

    SharedData sharedData;

    public PatchUserStepDefs(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    @Given("the request body is set to update the following fields")
    public void the_request_body_is_set_to_update_field_to(List<Map <String, String>> fields) {

        User user = User.builder()
                .first_name(fields.get(0).get("first_name"))
                .last_name(fields.get(0).get("last_name"))
                .email(fields.get(0).get("email"))
                .type(fields.get(0).get("type"))
                .active(fields.get(0).get("active"))
                .build();

        sharedData.getRequestSpecification().body(user);

//        switch (fieldName) {
//            case "first_name" -> sharedData.getRequestSpecification().body(User.builder().first_name(newValue).build());
//            case "last_name" -> sharedData.getRequestSpecification().body(User.builder().last_name(newValue).build());
//            case "email" -> sharedData.getRequestSpecification().body(User.builder().email(newValue).build());
//            case "type" -> sharedData.getRequestSpecification().body(User.builder().type(newValue).build());
//            case "active" -> sharedData.getRequestSpecification().body(User.builder().active(newValue).build());
//        }
    }

}

