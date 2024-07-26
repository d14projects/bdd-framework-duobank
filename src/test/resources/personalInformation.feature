Feature: UserStory5.Personal Information
  As a potential homebuyer, I want to use the Personal Information page of
  my bank's mortgage application to provide my personal details and co-
  borrower's details (if applicable) so that I can move forward with the
  mortgage application process.
  Background:
    Given the user has completed the Preapproval Details page


@UserStory5
  Scenario:  User select Co-Borrower Yes-Checkbox

    When the user sees the "Are you applying with a co-borrower?" field
    And the user selects the Yes checkbox
    Then the Yes checkbox should be checked
    And the No checkbox should not be checked