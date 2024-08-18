@REGRESSION @SMOKE
@API
Feature: API US 5: PATCH  /user
  As a mortgage application user,
  I want to be able to modify my user information such as name, address, phone number, and email
  through a PATCH request to the API endpoint '/user'

  @patch_user
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