@REGRESSION @SMOKE
@API
Feature: API US 7: GET /applications
  As a user of the mortgage application,
  I want to be able to login using my email and password through an API endpoint,
  So that I can access my personal information and apply for a mortgage loan.

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

#  @get_applications
  Scenario: Get applications for a specific user

    Given the request specification is reset
    Given the request is authenticated with a valid API key
    And the request "Content-type" header is set to "application/json"
    And the JWT token is set in the header
    When I send a "GET" request to the endpoint "/applications"
    Then the response log should be displayed
    Then the response status code should be 200
    And the response "Content-Type" header should be "application/json; charset=UTF-8"
    And the response time should be less than 2000 ms
    And the id field in all applications should not be null
#    And the owner field in all applications should be "jglob@gmail.com"