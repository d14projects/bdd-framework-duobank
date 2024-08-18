@REGRESSION @SMOKE
@API
Feature: API US 6: DELETE /user
  As a mortgage application user,
  I want to be able to delete my account from the mortgage application through an API endpoint.

@delete_user
Scenario: The endpoint should be accessible with a DELETE request to '/user' resource.

  # Create a user to be deleted
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

  # Store the created user ID so that it can be used wit the delete request
  And the response body "id" value is stored

  # Reset the request specification for this request
  And the request specification is reset

  # Delete the user
  Given the request is authenticated with a valid API key
  And the request "Content-type" header is set to "application/json"
  And the request "id" query parameter is set to the stored value
  When I send a "DELETE" request to the endpoint "/user"
  Then the response log should be displayed
  Then the response status code should be 200
  And the response "Content-Type" header should be "application/json"
  And the response time should be less than 2000 ms
  And the response body should have "message" field with value "User deleted successfully"