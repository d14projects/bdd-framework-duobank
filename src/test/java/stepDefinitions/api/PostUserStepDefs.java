package stepDefinitions.api;

import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import pojo.User;
import stepDefinitions.SharedData;

public class PostUserStepDefs {

    SharedData sharedData;

    public PostUserStepDefs(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    @And("the request body is set to the following payload")
    public void theRequestBodyIsSetToTheFollowingPayload(String docString) {
        Faker faker = new Faker();
        sharedData.getRequestSpecification().body(

                String.format(docString,
                        faker.name().firstName(),
                        faker.name().lastName(),
                        faker.internet().emailAddress(),
                        faker.internet().password(12,  16, true, true, true))
        );
    }

    @Given("the request body is set to the payload, where {string} field is {string}")
    public void the_request_body_is_set_to_the_payload_where_field(String payloadField, String condition) {

        Faker faker = new Faker();

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password(12,  16, true, true, true);

        switch (payloadField) {
            case "first_name" -> {
                if (condition.equals("missing")) {
                    sharedData.getRequestSpecification().body(User.builder()
                            .last_name(lastName)
                            .email(email)
                            .password(password)
                            .build()
                    );
                    ;
                }
                if (condition.equals("empty")) {
                    sharedData.getRequestSpecification().body(User.builder()
                            .first_name("")
                            .last_name(lastName)
                            .email(email)
                            .password(password)
                            .build()
                    );
                }
            }
            case "last_name" -> {
                if (condition.equals("missing")) {
                    sharedData.getRequestSpecification().body(
                            User.builder()
                                    .first_name(firstName)
                                    .email(email)
                                    .password(password)
                                    .build()
                    );
                }
                if (condition.equals("empty")) {
                    sharedData.getRequestSpecification().body(
                            User.builder()
                                    .first_name(firstName)
                                    .last_name("")
                                    .email(email)
                                    .password(password)
                                    .build()
                    );
                }
            }
            case "email" -> {
                if (condition.equals("missing")) {
                    sharedData.getRequestSpecification().body(
                            User.builder()
                                    .first_name(firstName)
                                    .last_name(lastName)
                                    .password(password)
                                    .build()
                    );
                }
                if (condition.equals("empty")) {
                    sharedData.getRequestSpecification().body(
                            User.builder()
                                    .first_name(firstName)
                                    .last_name(lastName)
                                    .email("")
                                    .password(password)
                                    .build()
                    );
                }
                if (condition.equals("invalid")) {
                    sharedData.getRequestSpecification().body(
                            User.builder()
                                    .first_name(firstName)
                                    .last_name(lastName)
                                    .email(firstName)
                                    .password(password)
                                    .build()
                    );
                }
            }
            case "password" -> {
                if (condition.equals("missing")) {
                    sharedData.getRequestSpecification().body(
                            User.builder()
                                    .first_name(firstName)
                                    .last_name(lastName)
                                    .email(email)
                                    .build()
                    );
                }
                if (condition.equals("empty")) {
                    sharedData.getRequestSpecification().body(
                            User.builder()
                                    .first_name(firstName)
                                    .last_name(lastName)
                                    .email(email)
                                    .password("")
                                    .build()
                    );
                }
            }
        }
    }





}
