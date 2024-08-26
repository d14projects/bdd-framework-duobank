package stepDefinitions.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    public static void main(String[] args) throws JsonProcessingException {

        String json = "{ \"id\": \"3334\",\n" +
                "        \"realtor_status\": \"2\",\n" +
                "        \"realtor_info\": \"\",\n" +
                "        \"loan_officer_status\": \"2\",\n" +
                "        \"purpose_loan\": \"Purchase a Home\",\n" +
                "        \"est_purchase_price\": \"100000\",\n" +
                "        \"down_payment\": \"20000\",\n" +
                "        \"down_payment_percent\": \"20.00\",\n" +
                "        \"total_loan_amount\": \"80000.00\",\n" +
                "        \"src_down_payment\": \"Checking/Savings (most recent bank statement)\",\n" +
                "        \"add_fund_available\": \"1000\",\n" +
                "        \"apply_co_borrower\": \"2\",\n" +
                "        \"b_firstName\": \"Erwin\",\n" +
                "        \"b_middleName\": \"\",\n" +
                "        \"b_lastName\": \"Feest\",\n" +
                "        \"b_suffix\": \"\",\n" +
                "        \"b_email\": \"dorothea.feeney@yahoo.com\",\n" +
                "        \"b_dob\": \"1980-01-01\",\n" +
                "        \"b_ssn\": \"234-34-3456\",\n" +
                "        \"b_marital\": \"Single\",\n" +
                "        \"b_cell\": \"555-555-5555\",\n" +
                "        \"b_home\": \"\",\n" +
                "        \"c_firstName\": \"\",\n" +
                "        \"c_middleName\": \"\",\n" +
                "        \"c_lastName\": \"\",\n" +
                "        \"c_suffix\": \"\",\n" +
                "        \"c_email\": \"\",\n" +
                "        \"c_dob\": \"\",\n" +
                "        \"c_ssn\": \"\",\n" +
                "        \"c_marital\": \"\",\n" +
                "        \"c_cell\": \"\",\n" +
                "        \"c_home\": \"\",\n" +
                "        \"rent_own_status\": \"Own\",\n" +
                "        \"monthly_rental_payment\": \"\",\n" +
                "        \"first_mortagage_total_payment\": \"2000\",\n" +
                "        \"employer_name\": \"[\\\"Duotech\\\"]\",\n" +
                "        \"position\": \"[\\\"\\\"]\",\n" +
                "        \"city\": \"[\\\"\\\"]\",\n" +
                "        \"state\": \"[\\\"\\\"]\",\n" +
                "        \"start_date\": \"[\\\"2017-10-04\\\"]\",\n" +
                "        \"end_date\": \"[\\\"\\\"]\",\n" +
                "        \"current_job\": \"[\\\"\\\"]\",\n" +
                "        \"co_employer_name\": \"[\\\"\\\"]\",\n" +
                "        \"co_position\": \"[\\\"\\\"]\",\n" +
                "        \"co_city\": \"[\\\"\\\"]\",\n" +
                "        \"co_state\": \"[\\\"\\\"]\",\n" +
                "        \"co_start_date\": \"[\\\"\\\"]\",\n" +
                "        \"co_end_date\": \"[\\\"\\\"]\",\n" +
                "        \"co_current_job\": \"[\\\"\\\"]\",\n" +
                "        \"gross_monthly_income\": \"5000\",\n" +
                "        \"monthly_over_time\": \"\",\n" +
                "        \"monthly_bonuses\": \"\",\n" +
                "        \"monthly_commision\": \"\",\n" +
                "        \"monthly_dividents\": \"\",\n" +
                "        \"c_gross_monthly_income\": \"\",\n" +
                "        \"c_monthly_over_time\": \"\",\n" +
                "        \"c_monthly_bonuses\": \"\",\n" +
                "        \"c_monthly_commision\": \"\",\n" +
                "        \"c_monthly_dividents\": \"\",\n" +
                "        \"add_belong\": \"[\\\"\\\",\\\"\\\",\\\"\\\"]\",\n" +
                "        \"income_source\": \"[\\\"\\\",\\\"\\\",\\\"\\\"]\",\n" +
                "        \"amount\": \"[\\\"\\\",\\\"\\\",\\\"\\\"]\",\n" +
                "        \"eConsent_declarer\": \"\",\n" +
                "        \"eConsent_declarer_FirstName\": \"Erwin\",\n" +
                "        \"eConsent_declarer_LastName\": \"Feest\",\n" +
                "        \"eConsent_declarer_Email\": \"dorothea.feeney@yahoo.com\",\n" +
                "        \"created_on\": \"2024-08-11 12:27:56\",\n" +
                "        \"modified_on\": \"\",\n" +
                "        \"loan_status\": \"\",\n" +
                "        \"user_id\": \"12627\",\n" +
                "        \"active\": \"1\" }";
        ObjectMapper objectMapper = new ObjectMapper();
        ApplicationDetails details = objectMapper.readValue(json, ApplicationDetails.class);
        System.out.println(details);

    }

}
