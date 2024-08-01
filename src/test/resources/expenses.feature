@UserStory6 @REGRESSION
Feature: User Story 6.
  As a potential homebuyer, I want to use the Expenses page of my bank's
  mortgage application to provide information about my current living
  situation and expenses so that I can move forward with the mortgage
  application process.
  Background:
    Given the user has completed the Preapproval Details page
    Given the user has completed the Personal Information Page

    Scenario: The "Do you currently rent or own?" field should have two checkboxes, one
    for "Rent" and one for "Own". The user should be able to select only one
    checkbox
      Given the user is on the Expenses page
      And sees two checkbox fields "Rent" and "Own"
      And user can select only one checkbox at a time
      Then the other checkbox is not selected

      Scenario: "Monthly Rental Payment" field is required when "Rent" is selected
        When user selects Rent checkbox
        And the user skips Monthly Rental Payment field and click Next button
        Then the Monthly Rental Payment field should be required