Feature: User Story 7.
  As a user applying for a mortgage loan, I want to provide my employment
  and income details to the bank, so that they can evaluate my eligibility for
  a loan.

  Background:
    Given the user has completed the Preapproval Details page
    And the user has completed the Personal Information Page
    And the user completed the Expenses Page

  Scenario:
    When the user is on the Employment and Income Page
    Then the page should contain the following fields
      | Employer Name |
      | Position      |
      | City          |
      | State         |
    And the page should contain date pickers for
      | Start Date |
      | End Date   |
