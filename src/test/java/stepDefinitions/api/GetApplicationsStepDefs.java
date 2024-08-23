package stepDefinitions.api;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import com.github.javafaker.Faker;
import io.cucumber.java.en.*;
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

import java.util.*;

public class GetApplicationsStepDefs {

    SharedData sharedData;

    public GetApplicationsStepDefs(SharedData sharedData) {
        this.sharedData = sharedData;
    }


    @And("the JWT token from the response is stored")
    public void theJWTTokenFromTheResponseIsStored() {

        String accessToken = sharedData.getResponse().path("access_token");

        System.out.println("Access Token: " + accessToken);

        sharedData.setJWToken(accessToken);
    }


    @And("the JWT token is set in the header")
    public void theJWTTokenIsSetInTheHeader() {

        sharedData.getRequestSpecification().header(
                "Authorization", sharedData.getJWToken()
        );
    }


    @And("the id field in all applications should not be null")
    public void theIdFieldInAllApplicationsShouldNotBeNull() {

        List<String> list = sharedData.getResponse().jsonPath().getList("mortagage_applications.id");
        Assert.assertTrue(!list.contains(null));


//        sharedData.getResponse().then().
//                body("playlists.id", not(hasItem( nullValue() ) ) ) ;
    }


    @Then("the response should contain a list of all mortgage applications with the following fields")
    public void the_response_should_contain_a_list_of_all_applications_with_the_following_fields(List<String> expectedFields) {

        List<Map<String, Object>> list = sharedData.getResponse().jsonPath().getList("mortagage_applications");

        System.out.println(list.size());

        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = sharedData.getResponse().jsonPath().getMap("mortagage_applications[" + i + "]");

            Assert.assertEquals(expectedFields, new ArrayList<>(map.keySet()));
        }
    }

    @Then("list mortgage applications must be ordered by creation date: newest to oldest, or highest")
    public void list_mortgage_applications_must_be_ordered_by_creation_date_newest_to_oldest_or_largest_id_to_lowest() {

        List<String> list = sharedData.getResponse().jsonPath().getList("mortagage_applications.id");

        System.out.println(list);

        list.sort(Comparator.reverseOrder());

        List<String> expectedOrder = new ArrayList<>(list);

        Assert.assertEquals(expectedOrder, list);



    }

}




