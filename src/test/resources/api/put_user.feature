@REGRESSION @SMOKE
@API
Feature: API US 4: PUT /user
  As a mortgage application user,
  I want to be able to update my personal information through an API endpoint,
  so that my information is accurate and up-to-date.

#  @put_user
  Scenario: The API endpoint should be accessible through a PUT request to the resource "/user".
    Given the request is authenticated with a valid API key
    And the request "Content-type" header is set to "application/json"
    And the request "id" query parameter is set to "12588"
    And the request body is set to the following payload
      """
      {
        "first_name": "%s",
        "last_name": "%s",
        "email": "%s",
        "password": "%s"
      }
      """

    When I send a "PUT" request to the endpoint "/user"
    Then the response log should be displayed
    Then the response status code should be 200