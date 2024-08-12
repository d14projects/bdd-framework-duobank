@REGRESSION @signup
Feature: UI User Story 1: Duobank App Sign Up
  As a potential customer,
  I want to sign up for an account on the bank mortgage application
  so that I can start the process of applying for a mortgage.

  Background:
    Given the user navigates to the sign in page
    When the user clicks sign up link


#  @signup
  Scenario: The Sign Up page should include input fields for
  the user's First Name, Last Name, Email Address, and Password.
    Then the user should see First Name, Last Name, Email Address, and Password fields

#  @signup
  Scenario Outline: All input fields should have clear labels and validation rules as follows
    Then the info entered on the field should meet the following conditions
      | field   | required   | hasLetterAndSpace | maxLength   | minLength   | isDuplicate | hasUppercase | hasLowercase | hasNumber | format   |
      | <field> | <required> | <letterAndSpace>  | <maxLength> | <minLength> | <duplicate> | <upp>        | <low>        | <num>     | <format> |

    Examples:
      | field     | required | letterAndSpace | maxLength | minLength | duplicate | upp  | low  | num  | format              |
      | firstName | true     | true           | 50        |           |           |      |      |      |                     |
      | lastName  | true     | true           | 50        |           |           |      |      |      |                     |
      | email     | true     |                | 255       |           | true      |      |      |      | example@example.com |
      | password  | true     |                | 50        | 8         |           | true | true | true |                     |


#  @signup
  Scenario: The Sign Up button should be non-functional when some of the required input fields are filled out correctly
    When the user clicks Sign Up button after filling some of the required fields
      | field |
      | 0     |
      | 1     |
      | 2     |
      | 3     |
    Then Sign up button should be non-functional and user should remain on the Sign Up page

#  @signup
  Scenario: The Sign Up button should be non-functional until all required input fields are filled out correctly
    When the user clicks Sign Up button by filling all required input fields
    Then the user should see Registration Successful message and be redirected to the Sign In Page

#  @signup
  Scenario: Clicking the Sign Up button should create a new account for the user and display “Registration Successful”message
  and redirect them to the Sign In page.
    When the user clicks Sign Up button by filling all required input fields
    Then the user should see Registration Successful message and be redirected to the Sign In Page

#  @signup
  Scenario: Entering an email address that is already associated with an existing account
  When the user enters an email address that is already associated with an existing account
  Then an error message “This email already used” should be displayed.

#  @signup
  Scenario: The Sign Up page should include a "Already have an account? Sign in" link that takes the user to the Sign In page.
    When the user confirms "Already have an account? Sign in" link is displayed
    And  the user clicks on "Already have an account? Sign in" link
    Then the user should be redirected to the Sign In Page

