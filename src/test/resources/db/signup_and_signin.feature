@REGRESSION
Feature: DB User Story 1: Appllicaton List
  As a user of Duobank Mortgage Application,
  I want to be able to sign up and log in securely using a database system that protects my personal and financial information,
  so that I can complete the mortgage application process with confidence.

#  @db_only
  Scenario: Validate tbl_users table schema and data storage
    Then "tbl_user" table should have the following columns:
      | id          |
      | email       |
      | password    |
      | first_name  |
      | last_name   |
      | phone       |
      | image       |
      | type        |
      | created_at  |
      | modified_at |
      | zone_id     |
      | church_id   |
      | country_id  |
      | active      |

#  @db_only
  Scenario: The "tbl_users" table should not store duplicate email addresses.
    Then "tbl_user" table should not contain duplicate "email" addresses

#  @db_only
  Scenario: The "tbl_users" table should not allow duplicate email addresses.
    When the user creates a new user record in "tbl_user" table with the already existing email address "jglob@gmail.com"
    Then the db "tbl_user" table should not store the new user record

#  @data_mapping
  Scenario: Verify data mapping of user registration process and accuracy of db column content
#    Create a new user - UI
    Given the user navigates to the sign in page
    And the user clicks sign up link
    When The user fills up the fields with valid info
    Then The user should be able to sign up successfully
#    Verify the created user on the DB layer
    And the user should be created in the "tbl_user" table in the database
    And the data should be mapped correctly to the following columns in the "tbl_user" table:
      | first_name |
      | last_name  |
      | email      |
      | password   |
#    Verify the created user info on the DB layer - FLAKY TIMESTAMP due to minutes change due to waiting requirements at UI side
    And "id" the unique identifier for the user should be autogenerated by the database in the "tbl_user" table
    And "tbl_user" table should store a timestamp of when the user account was created in the "created_at" column
    And "tbl_user" table should store and encrypt user passwords in an MD5 hash in the "password" column

#  @ui_to_db
  Scenario: Verify "tbl_user" columns correctly display corresponding user information:
#    Create a new user - UI
    Given the user navigates to the sign in page
    And the user clicks sign up link
    And The user fills up the fields with valid info
    And The user should be able to sign up successfully
#    Create a new loan application - UI
    When the user navigates to the sign in page
    And the user enters the new user credentials and clicks Sign In button
    And the user is redirected to the mortgage account dashboard
    And the user completed and submits Mortgage Application
    And the user should see Application Submitted Successfully message
#    Verify the created loan application info is correctly mapped on the DB layer PHONE IS BUG
    Then "tbl_user" columns should correctly display corresponding user information:
      | email       |
      | password    |
      | first_name  |
      | last_name   |
      | phone       |
      | image       |
      | type        |
      | created_at  |
      | modified_at |
      | zone_id     |
      | church_id   |
      | country_id  |
      | active      |