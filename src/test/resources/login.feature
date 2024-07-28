@REGRESSION @signup
Feature: Duobank App Sign In
  As an existing customer,
  I want to sign in to my account on the bank mortgage application
  so that I can start the process of applying for a mortgage.

  Background:
    Given the user is on the login page
    When the user enters valid credentials and clicks Sign In button
    Then the user is redirect