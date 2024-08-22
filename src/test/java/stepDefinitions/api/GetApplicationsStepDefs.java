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

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
        System.out.println(list);
        Assert.assertTrue(!list.contains(null));


//        sharedData.getResponse().then().
//                body("playlists.id", not(hasItem( nullValue() ) ) ) ;
    }

    @And("the owner field in all applications should be {string}")
    public void theOwnerFieldInAllPlaylistsShouldBe(String expected) {
        List<String> list = sharedData.getResponse().jsonPath().getList("playlists.owner");
        System.out.println(list);

        list.forEach(e -> Assert.assertEquals(expected, e));

        Set<String> uniqueSet
                = new HashSet<>(list); // will remove all duplicates, and if the expected value is really unique, it's size should become 1
        System.out.println(uniqueSet);
        Assert.assertTrue(uniqueSet.size()==1 && uniqueSet.iterator().next().equals(expected));

        // you need to use iterator().next(), after uniqueSet to check equality of string expected and the ones in the actual list

    }

}




