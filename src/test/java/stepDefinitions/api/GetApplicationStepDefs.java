package stepDefinitions.api;

import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import pojo.Application;
import pojo.ApplicationDetails;
import pojo.ApplicationDetailsResponse;
import pojo.ApplicationsResponse;
import stepDefinitions.SharedData;

import java.util.*;

public class GetApplicationStepDefs {

    SharedData sharedData;

    public GetApplicationStepDefs(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    @Then("list mortgage application must be must contain all required fields")
    public void list_mortgage_application_must_be_must_contain_all_required_fields() {


        ApplicationDetailsResponse detailsResponse = sharedData.getResponse().then().extract().as(ApplicationDetailsResponse.class);

        ApplicationDetails details = detailsResponse.getApplication_details();

        System.out.println(details);

    }

    //        JsonPath jsonPath = sharedData.getResponse().then().extract().jsonPath();
//
//        Map<String, String> applicationDetails = jsonPath.getMap("application_details");
//
//        System.out.println(applicationDetails);


}
