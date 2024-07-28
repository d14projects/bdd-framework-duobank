@REGRESSION
Feature: User Story 1. Duobank App Sign Up
  As a potential customer,
  I want to sign up for an account on the bank mortgage application
  so that I can start the process of applying for a mortgage.

  Background:
    Given the user is on the login page
    When the user clicks sign up link

  Scenario: Successful sign up as a new user
    And enters firstName, lastName, emailAddress, password
    Then the user should see Registration Successful message


  Scenario: The Sign Up page should include input fields for
  the user's First Name, Last Name, Email Address, and Password.

    Then the user should see First Name, Last Name, Email Address, and Password fields

  @signup
  Scenario Outline: All input fields should have clear labels and validation rules as follows
    And the user enters info on the "<field>" field
    Then the entered info should meet the following conditions
      | field   | required   | hasLetterAndSpace | maxLength   | minLength   | isDuplicate | hasUppercase | hasLowercase | hasNumber |
      | <field> | <required> | <letterAndSpace>  | <maxLength> | <minLength> | <duplicate> | <upp>        | <low>        | <num>     |

    Examples:

      | field     | required | letterAndSpace | maxLength | minLength | duplicate | upp   | low   | num   |
      | firstName | true     | true           | 50        | 0         | false     | false | false | false |
      | lastName  | true     | true           | 50        | 0         | false     | false | false | false |
      | email     | true     | false          | 255       | 0         | true      | false | false | false |
      | password  | true     | false          | 50        | 8         | false     | true  | true  | true  |


