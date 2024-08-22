@REGRESSION @SMOKE
@API
Feature: API US 5: PATCH  /user
  As a mortgage application user,
  I want to be able to modify my user information such as name, address, phone number, and email
  through a PATCH request to the API endpoint '/user'

#  @patch_user
  Scenario Outline: The API endpoint '/user' should be able to handle PATCH requests.
    Given the request is authenticated with a valid API key
    Given the request "Content-type" header is set to "application/json"
    Given the request "id" query parameter is set to "12588"
    Given the request body is set to update the following fields
      | first_name   | last_name   | email   | type   | active   |
      | <first_name> | <last_name> | <email> | <type> | <active> |
    When I send a "PATCH" request to the endpoint "/user"
    Then the response log should be displayed
    Then the response status code should be 200

    Examples:
      | first_name | last_name | email            | type | active |
      | Alec       | Baldwin   | a.bald@gmail.com | 1    | 0      |
      | Zorro      |           | zec@gmail.com    | 2    | 0      |
      |            | Molly     |                  |      |        |
      |            |           | second@gmail.com |      |        |
      |            |           |                  | 2    | 0      |
      |            |           |                  |      | 1      |

#  @patch_user
  Scenario: The API endpoint should require authentication via API key.
  If API key is not provided, 401 Unauthorized status code with error message should be displayed.
    Given the request "Content-type" header is set to "application/json"
    Given the request "id" query parameter is set to "12588"
    Given the request body is set to update the following fields
      | first_name | last_name | email         | type | active |
      | Zorro      |           | zec@gmail.com | 2    | 0      |
    When I send a "PATCH" request to the endpoint "/user"
    Then the response log should be displayed
    Then the response status code should be 401

#  @patch_user
  Scenario: The API endpoint should return a JSON response and Content-Type header as "application/json".
    Given the request is authenticated with a valid API key
    Given the request "Content-type" header is set to "application/json"
    Given the request "id" query parameter is set to "12588"
    Given the request body is set to update the following fields
      | first_name | last_name | email         | type | active |
      |            | Doe       | doe@gmail.com | 1    | 1      |
    When I send a "PATCH" request to the endpoint "/user"
    Then the response log should be displayed
    Then the response status code should be 200
    And the response "Content-Type" header should be "application/json"

  @patch_user
  Scenario: The API should verify that the user ID provided in the URL path parameter is valid,
  and should respond with a 400 Bad Request status code and an error message if it is not.
    Given the request is authenticated with a valid API key
    Given the request "Content-type" header is set to "application/json"
    Given the request "id" query parameter is set to "asd"
    Given the request body is set to update the following fields
      | first_name | last_name | email         | type | active |
      |            | Doe       | doe@gmail.com | 1    | 1      |
    When I send a "PATCH" request to the endpoint "/user"
    Then the response log should be displayed
    Then the response status code should be 400
    And the response body should have "message" field with value "Invalid or missing ID"

