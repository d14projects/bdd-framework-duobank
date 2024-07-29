@REGRESSION
  Feature: User signUp for Duobank App
    As a user, I want to be able to sign up as a new user
  so that I can create a new account to access the feature of the application

  Background:
    Given the user is on the login page

    @signup
   Scenario: Successful sign up as a new user
     When the user clicks sign up link
      And enters firstName, lastName, emailAddress, password
      Then the user should see Registration Successful message