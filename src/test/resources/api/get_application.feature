@REGRESSION @SMOKE
@API
Feature: API US 9: GET /application
  As a registered user of the mortgage application,
  I want to retrieve the details of a single mortgage application that I have submitted previously.
  I should be able to retrieve the application details using a JWT token that is generated upon successful login.

  Background:
    Given the request is authenticated with a valid API key
    And the request "Content-type" header is set to "application/json"
    And the request body is set to the following payload as
          """
          {
            "email": "jglob@gmail.com",
            "password": "Password1"
          }
          """
    When I send a "POST" request to the endpoint "/login"
    Then the response log should be displayed
    Then the response status code should be 200
    And the response body should have "message" field with value "You've successfully logged in!"
    And the JWT token from the response is stored

  @get_application
  Scenario: Get application for a specific user

    Given the request specification is reset
    Given the request is authenticated with a valid API key
    And the request "id" query parameter is set to "3334"
    And the request "Content-type" header is set to "application/json"
    And the JWT token is set in the header
    When I send a "GET" request to the endpoint "/application"
    Then the response log should be displayed
    Then the response status code should be 200
    And the response "Content-Type" header should be "application/json; charset=UTF-8"
    And the response time should be less than 2000 ms
#    Then list mortgage application must be must contain all required fields