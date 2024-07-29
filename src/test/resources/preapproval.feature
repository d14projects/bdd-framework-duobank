

   Feature: Preapproval Details Page Functionality
   Background:
    Given I am logged to the Duobank
    @applicationSteps
    Scenario: Displaying and interacting with the Preapproval Details page


    Then i clicked to the Mortgage Application
    And I should see a checkbox for "Are you working with a realtor?" with options "Yes" and "No"

    When I select "Yes" for the Realtor checkbox
    Then the "Realtor Information" field should be enabled and required

    When I select "No" for the "Are you working with a realtor?" checkbox
    Then the "Realtor Information" field should be disabled

    And I should see a another checkbox for "Are you currently working with a loan officer?" with options "Yes" and "No"
    And the "Purpose of Loan" field should have a dropdown menu with options "Purchase a Home", "Refinance", and "Construction"

    When I enter "10000.00" into the Estimated Purchase Price field
    Then Estimated Purchase Price field should accept the value "10000.00"

    When I enter "5000.00" into the Down Payment Amount field
    Then Down Payment Amount field should accept the value "5000.00"
    And You Loan Amount filed should automatically calculate the down payment percentage


    When I enter "3000.00" into the Additional Funds field
    Then Additional Funds  field should accept the value "3000.00"

    @navigation
    Scenario: Preventing navigation with incomplete required fields
    When i am on the Mortgage Application page
    Then I do not fill out all the required fields
    And I click the "Next" button
    And I should not be able to proceed to the next page

    @navigation2
     Scenario: Navigation with complete required fields
      When i am on the Mortgage Application page
      Then all information filled
      And it should lead me to the next page