@REGRESSION
#@ui_to_db_crud
Feature: User Story 1: Appllicaton List
  As a user of Duobank Mortgage Application,
  I want to be able to sign up and log in securely using a database system that protects my personal and financial information,
  so that I can complete the mortgage application process with confidence.

  @db_only
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