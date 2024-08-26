@REGRESSION @SMOKE
@API
Feature: API US 7: POST /login
  As a user of the mortgage application,
  I want to be able to login using my email and password through an API endpoint,
  So that I can access my personal information and apply for a mortgage loan.

#  @post_login
  Scenario: The API endpoint accepts a POST request to /login resource.
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
    And the response "Content-Type" header should be "application/json; charset=UTF-8"


#      @post_login
  Scenario: The API endpoint should require authentication via API key. If API key is not provided, 401 Unauthorized status
      code with error message should be displayed.
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
    Then the response status code should be 401
    And the response body should have "message" field with value "Invalid or missing API Key. Provide a valid api key with 'api_key' query parameter."

#  @post_login
  Scenario: The request must contain a JSON payload with the user's email and password..
    Given the request is authenticated with a valid API key
    And the request "Content-type" header is set to "application/json"
    When I send a "POST" request to the endpoint "/login"
    Then the response log should be displayed
    Then the response status code should be 422
    And the response body should have "message" field with value "Please fill in all required fields!"
#bug here returns 200 while should be 422. 422 is returned in the payload and message

#    @post_login
  Scenario: If the request method is not POST, the API responds with a 405 status code and an error message "Method Not Allowed".
    Given the request is authenticated with a valid API key
    And the request "Content-type" header is set to "application/json"
    And the request body is set to the following payload as
          """
          {
            "email": "jglob@gmail.com",
            "password": "Password1"
          }
          """
    When I send a "PATCH" request to the endpoint "/login"
    Then the response log should be displayed
    Then the response status code should be 405
    And the response body should have "message" field with value "Method Not Allowed!"
   #bug here returns 200 while should be 405. with error message Page Not Found! and status 404


#  @post_login
  Scenario Outline: If the request is missing the email or password fields, or if they are empty,
  the API responds with a 422 status code and an error message "Please fill in all required fields!".
    Given the request is authenticated with a valid API key
    And the request "Content-type" header is set to "application/json"
    And the request body is set to the following fields
      | email   | password   |
      | <email> | <password> |

    When I send a "POST" request to the endpoint "/login"
    Then the response log should be displayed
    Then the response status code should be 422
    And the response body should have "message" field with value "Please fill in all required fields!"

    Examples:
      | email           | password  |
      | jglob@gmail.com |           |
      |                 | Password1 |
      | null            | null      |
#bug, returns 200 status, 422 with expected message returned in the payload, with expected message,
# but when empty message is Invalid Email Address!

#  @post_login
  Scenario: If the email address or password is incorrect, the API responds with a 422 status code and
  an error message "Invalid Email Address!" or "Invalid Password!", respectively.
    Given the request is authenticated with a valid API key
    And the request "Content-type" header is set to "application/json"
    And the request body is set to the following payload as
          """
          {
            "email": "jglob@gmail.com",
            "password": "Password14"
          }
          """
    When I send a "POST" request to the endpoint "/login"
    Then the response log should be displayed
    Then the response status code should be 422
    And the response body should have "message" field with value "Invalid Password!"
#    it does not matter you send incorrect email or password, the status code returns 200, 422 is returned in the payload,
#    and message is always Invalid Email Address

#  @post_login
  Scenario: The API response payload should be in the format:
  {
  "success": true,
  "message": "You've successfully logged in!",
  "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "token_type": "Bearer",
  "expires_in": 3600
  }

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
    And the response payload should be in the correct format
      | success      | true                           |
      | message      | You've successfully logged in! |
      | access_token | notNull                        |
      | token_type   | Bearer                         |
      | expires_in   | 3600                           |