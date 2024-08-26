@REGRESSION @SMOKE
@API
Feature: API US 8: GET /applications
  As a loan officer,
  I want to be able to retrieve a list of mortgage applications for a specific user,
  so that I can review their application status and history.

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
    And the response time should be less than 1000 ms
    And the id field in all applications should not be null

#  @get_applications
  Scenario: The endpoint must return a list of mortgage applications with the following information for each application:
  o	Application ID
  o	Borrower First Name
  o	Borrower Last Name
  o	Borrower Middle Name
  o	Total Loan Amount

    Given the request specification is reset
    Given the request is authenticated with a valid API key
    And the request "Content-type" header is set to "application/json"
    And the JWT token is set in the header
    When I send a "GET" request to the endpoint "/applications"
    Then the response log should be displayed
    Then the response status code should be 200
    And the response "Content-Type" header should be "application/json; charset=UTF-8"
    And the response time should be less than 1000 ms
    And the response should contain a list of all mortgage applications with the following fields
      | id                |
      | b_firstName       |
      | b_lastName        |
      | b_middleName      |
      | total_loan_amount |

      @get_applications
  Scenario: The list of mortgage applications must be ordered by creation date (newest to oldest).

        Given the request specification is reset
        Given the request is authenticated with a valid API key
        And the request "Content-type" header is set to "application/json"
        And the JWT token is set in the header
        When I send a "GET" request to the endpoint "/applications"
        Then the response log should be displayed
        Then the response status code should be 1000
        Then list mortgage applications must be ordered by creation date: newest to oldest, or highest