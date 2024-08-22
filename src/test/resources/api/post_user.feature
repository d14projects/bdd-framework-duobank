@REGRESSION @SMOKE
@API
Feature: API US 3: POST /user API endpoint features
  As a mortgage application user,
  I want to be able to create a new user account
  so that I can apply for a mortgage and access my account information.

#  @post_user
  Scenario: API endpoint '/user' should be accessible by HTTP POST request.
    Given the request is authenticated with a valid API key
    And the request "Content-type" header is set to "application/json"
    And the request body is set to the following payload
      """
      {
        "first_name": "%s",
        "last_name": "%s",
        "email": "%s",
        "password": "%s"
      }
      """

    When I send a "POST" request to the endpoint "/user"
    Then the response log should be displayed
    Then the response status code should be 201

#  @post_user
  Scenario: If API key is not provided, 401 Unauthorized status code with error message should be displayed.
    Given the request "Content-type" header is set to "application/json"
    And the request body is set to the following payload
      """
      {
        "first_name": "%s",
        "last_name": "%s",
        "email": "%s",
        "password": "%s"
      }
      """
    When I send a "POST" request to the endpoint "/user"
    Then the response log should be displayed
    Then the response status code should be 401
    And the response body should have "message" field with value "Invalid or missing API Key. Provide a valid api key with 'api_key' query parameter."


#  @post_user
  Scenario: The API endpoint should return a JSON response.
    Given the request is authenticated with a valid API key
    And the request "Content-type" header is set to "application/json"
    And the request body is set to the following payload
      """
      {
        "first_name": "%s",
        "last_name": "%s",
        "email": "%s",
        "password": "%s"
      }
      """

    When I send a "POST" request to the endpoint "/user"
    Then the response log should be displayed
    Then the response status code should be 201
    And the response "Content-Type" header should be "application/json"

#@post_user
  Scenario: API should validate the request body and return a 422 Unprocessable Entity error with a message
  indicating which required fields are missing or invalid, if any of the following fields are missing or empty:
  o	first_name
  o	last_name
  o	email
  o	password

  Given the request is authenticated with a valid API key
  And the request "Content-type" header is set to "application/json"
  And the request body is set to the payload, where "first_name" field is "missing"
  When I send a "POST" request to the endpoint "/user"
  Then the response log should be displayed
  Then the response status code should be 422
  And the response body should have "message" field with value "Missing or Invalid Required Fields!"

  Given the request is authenticated with a valid API key
  And the request "Content-type" header is set to "application/json"
  And the request body is set to the payload, where "first_name" field is "empty"
  When I send a "POST" request to the endpoint "/user"
  Then the response log should be displayed
  Then the response status code should be 422
  And the response body should have "message" field with value "Missing or Invalid Required Fields!"

  Given the request is authenticated with a valid API key
  And the request "Content-type" header is set to "application/json"
  And the request body is set to the payload, where "last_name" field is "missing"
  When I send a "POST" request to the endpoint "/user"
  Then the response log should be displayed
  Then the response status code should be 422
  And the response body should have "message" field with value "Missing or Invalid Required Fields!"

  Given the request is authenticated with a valid API key
  And the request "Content-type" header is set to "application/json"
  And the request body is set to the payload, where "last_name" field is "empty"
  When I send a "POST" request to the endpoint "/user"
  Then the response log should be displayed
  Then the response status code should be 422
  And the response body should have "message" field with value "Missing or Invalid Required Fields!"

  Given the request is authenticated with a valid API key
  And the request "Content-type" header is set to "application/json"
  And the request body is set to the payload, where "email" field is "missing"
  When I send a "POST" request to the endpoint "/user"
  Then the response log should be displayed
  Then the response status code should be 422
  And the response body should have "message" field with value "Missing or Invalid Required Fields!"

  Given the request is authenticated with a valid API key
  And the request "Content-type" header is set to "application/json"
  And the request body is set to the payload, where "email" field is "empty"
  When I send a "POST" request to the endpoint "/user"
  Then the response log should be displayed
  Then the response status code should be 422
  And the response body should have "message" field with value "Missing or Invalid Required Fields!"

  Given the request is authenticated with a valid API key
  And the request "Content-type" header is set to "application/json"
  And the request body is set to the payload, where "password" field is "missing"
  When I send a "POST" request to the endpoint "/user"
  Then the response log should be displayed
  Then the response status code should be 422
  And the response body should have "message" field with value "Missing or Invalid Required Fields!"

  Given the request is authenticated with a valid API key
  And the request "Content-type" header is set to "application/json"
  And the request body is set to the payload, where "password" field is "empty"
  When I send a "POST" request to the endpoint "/user"
  Then the response log should be displayed
  Then the response status code should be 422
  And the response body should have "message" field with value "Missing or Invalid Required Fields!"

#  @post_user
  Scenario: If the email address is not valid the API should return a 422 Unprocessable Entity error
  with a message indicating that the email address is invalid

    Given the request is authenticated with a valid API key
    And the request "Content-type" header is set to "application/json"
    And the request body is set to the payload, where "email" field is "invalid"
    When I send a "POST" request to the endpoint "/user"
    Then the response log should be displayed
    Then the response status code should be 422
    And the response body should have "message" field with value "Invalid Email Address!"

#  @post_user
  Scenario: The API should return a 422 Unprocessable Entity error with a message indicating
  that the password is invalid if the password is less than 8 characters:
    Given the request is authenticated with a valid API key
    And the request "Content-type" header is set to "application/json"
    And the request body is set to the payload, where "password" field is "less than 8 characters"
    When I send a "POST" request to the endpoint "/user"
    Then the response log should be displayed
    Then the response status code should be 422
    And the response body should have "message" field with value "Password must be at least 8 characters long and contain at least one uppercase character, one lowercase character, one number, and one special symbol (!@#$%^&*()-_=+{};:,<.>)!"