@REGRESSION
Feature: Duobank App Sign In
  As a user,
  I want to be able to sign in to my mortgage account,
  so that I can access my account information and perform necessary actions.

  Background:
    Given the user navigates to the sign in page

#  @login
  Scenario: Welcome message greeting
    Then the user should see a Welcome Back! greeting message displayed on the page

#  @login
  Scenario: The sign in page should have two input fields: one for email and one for password
    Then the user should see two input fields: one for email and one for password

#  @login
  Scenario: The email address and password fields should be required
  When the user does not fill out both email and password fields
  Then the user should not be able to submit the form to sign to the dashboard

#  @login
  Scenario: The email address should be in valid email format
  When the user enters email address to the email field
  Then the email should be in a valid email address format e.g. example@example.com

  @login
  Scenario: The password field should be masked and not show the entered characters
  When the user enters password to the password field
  Then the password field should be masked and not show entered characters

#  @login
  Scenario: User sign in to the mortgage account dashboard
  When the user enters correct credentials and clicks Sign In button
  Then the user is redirected to the mortgage account dashboard