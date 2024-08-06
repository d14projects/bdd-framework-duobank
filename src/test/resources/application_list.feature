@REGRESSION
#@applications
Feature: User Story 11: Appllicaton List
  As a user,
  I want to see a list of all my submitted applications
  so that I can keep track of my applications and their status.


  Background:
    Given the user navigates to the sign in page
    When the user enters correct credentials and clicks Sign In button
    And the user is redirected to the mortgage account dashboard
    And the user clicks on Application List link

#  @applications
  Scenario: The user can select the number of entries to show on the page from a dropdown with options of 10, 25, 50, and 100.
    Then the dropdown menu should show options for 10, 25, 50 and 100 entries

#  @applications
  Scenario Outline: The user can enter a search term in the search field to filter the results based on the search term.
    When the user enters the "<search>" term in the search field and that name exists in the database
    Then the page should show loan application results for only that name
      | name     |
      | <search> |

    Examples:
      | search           |
      | Judith Kuvalis   |
      | Dustin Armstrong |
      | Larry McGlynn    |
      | Jon Glob         |

#  @applications
  Scenario: The list of submitted applications should be displayed in a table with columns for
  loan id, borrower name, loan amount, and loan details.
    Then the user should see the Application List table headers in the following order
      | loan id       |
      | borrower name |
      | loan amount   |
      | details       |

#  @applications
  Scenario: The loan id should be an integer number.
    Then the loan id should be consist only of digits

#  @applications
  Scenario Outline:: The borrower name should be displayed in the format of first name, last name.
    Then the borrower name should be equal to "<firstName>", "<lastName>" format
      | first       | last       |
      | <firstName> | <lastName> |

    Examples:
      | firstName | lastName  |
      | Judith    | Kuvalis   |
      | Dustin    | Armstrong |
      | Larry     | McGlynn   |
      | Jon       | Glob      |

#  @applications
  Scenario: The loan amount should be displayed in US dollars and should be an integer. FIX BUG
    Then the loan amount should be consist only of digits and contain $ sign

#  @applications
  Scenario: The loan details column should have view details button that should take the user to a page with loan details.
    Then the user should see "View Details" button displayed and clicking should take to correct loan details page

#    loan amount accepts non integer like letters
  @applications
  Scenario: The table should have sorting functionality for each column.
    Then the borrower should be able to sort the "LOAN ID" column in ascending and descending orders
    Then the borrower should be able to sort the "LOAN AMOUNT" column in ascending and descending orders
    Then the borrower should be able to sort the "BORROWER NAME" column in ascending and descending orders
