@REGRESSION @SMOKE
@API
Feature: API US 2: GET /user

#  @Get_User
Scenario: The API endpoint should be accessible through a GET request to the resource "/user"
    Given the request is authenticated with a valid API key
    And the request "Content-type" header is set to "application/json"
    And the request "id" query parameter is set to "11426"
    When I send a "GET" request to the endpoint "/user"
    Then the response log should be displayed
    Then the response status code should be 200

#  @Get_User
Scenario: The API endpoint should require authentication via API key.
  If API key is not provided, 401 Unauthorized status code with error message should be displayed.
    Given the request "Content-type" header is set to "application/json"
    And the request "id" query parameter is set to "11426"
    When I send a "GET" request to the endpoint "/user"
    Then the response log should be displayed
    Then the response status code should be 401

#@Get_User
Scenario: The API endpoint should return a JSON response.
    Given the request is authenticated with a valid API key
    And the request "Content-type" header is set to "application/json"
    And the request "id" query parameter is set to "11426"
    When I send a "GET" request to the endpoint "/user"
    Then the response log should be displayed
    Then the response status code should be 200
    And the response "Content-Type" header should be "application/json"

#@Get_User
Scenario: The request must include a valid user ID as a query parameter.
   Given the request is authenticated with a valid API key
    And the request "Content-type" header is set to "application/json"
    And the request "id" query parameter is set to "11426"
    When I send a "GET" request to the endpoint "/user"
    Then the response log should be displayed
    Then the response status code should be 200

#@Get_User
Scenario: If the user ID is not provided, the API should return a 400 Bad Request response with
a message "Bad request. User ID is not provided."
    Given the request is authenticated with a valid API key
    And the request "Content-type" header is set to "application/json"
    When I send a "GET" request to the endpoint "/user"
    Then the response log should be displayed
    Then the response status code should be 400
    And the response body should have "message" field with value "Bad request. User id is not provided."

#@Get_User
Scenario: If the user ID is invalid, the API should return a 400 Bad Request response with
a message "Bad request. User ID is not provided."
    Given the request is authenticated with a valid API key
    And the request "Content-type" header is set to "application/json"
    And the request "id" query parameter is set to "abc"
    When I send a "GET" request to the endpoint "/user"
    Then the response log should be displayed
    Then the response status code should be 400
    And the response body should have "message" field with value "Bad request. User id is not provided."

#@Get_User
  Scenario: If the user ID is valid but not found in the database, the API should return a 404 Not Found response with
  a message "User not found."
    Given the request is authenticated with a valid API key
    And the request "Content-type" header is set to "application/json"
    And the request "id" query parameter is set to "1"
    When I send a "GET" request to the endpoint "/user"
    Then the response log should be displayed
    Then the response status code should be 404
    And the response body should have "message" field with value "User not found."

#@Get_User
Scenario: If the user ID is valid and found in the database, the API should return a 200 OK response
with the user's information in JSON format with the following properties
    Given the request is authenticated with a valid API key
    And the request "Content-type" header is set to "application/json"
    And the request "id" query parameter is set to "11426"
    When I send a "GET" request to the endpoint "/user"
    Then the response log should be displayed
    Then the response status code should be 200
    And the response "Content-Type" header should be "application/json"
    And the response body should have "id" field with value "11426"
    And the response body should have "first_name" field with value "Dustin"
    And the response body should have "last_name" field with value "Walter"
    And the response body should have "email" field with value "boyce.parker@hotmail.com"
    And the response body should have "created_at" field with value "2023-12-31 04:01:05"
    And the response body should have "type" field with value "2"
    And the response body should have "active" field with value "1"

#  @Get_User
  Scenario: The API should not return the user's password or any other sensitive information.
    Given the request is authenticated with a valid API key
    And the request "Content-type" header is set to "application/json"
    And the request "id" query parameter is set to "11426"
    When I send a "GET" request to the endpoint "/user"
    Then the response log should be displayed
    Then the response status code should be 200
    And the response body should not contain "password" property

#  @Get_User
  Scenario: The API must return the Content-Type header as "application/json".
    Given the request is authenticated with a valid API key
    And the request "Content-type" header is set to "application/json"
    And the request "id" query parameter is set to "11426"
    When I send a "GET" request to the endpoint "/user"
    Then the response log should be displayed
    Then the response status code should be 200
    And the response "Content-Type" header should be "application/json"

#  @Get_User
  Scenario: The response time for a single user request should be less than 500ms.
    Given the request is authenticated with a valid API key
    And the request "Content-type" header is set to "application/json"
    And the request "id" query parameter is set to "11426"
    When I send a "GET" request to the endpoint "/user"
    Then the response log should be displayed
    Then the response status code should be 200
    And the response time should be less than 500 ms