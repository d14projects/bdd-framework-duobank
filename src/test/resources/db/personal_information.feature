
@REGRESSION
Feature:  As a mortgage loan applicant i want fill my personal information so it should be saved in the app and in the database

@db
  Scenario: User enters personal information on the Personal Information page
  Given I am logging to the Duobank
  When i am clicking to the Mortgage Application
  Then all information in preapproval details should be filled
  When the user selects the Yes checkbox

    Then the user enters the following borrower information
      | b_firstName | b_middleName | b_lastName | b_suffix | b_email              | b_dob      | b_ssn       | b_marital | b_cell       | b_home        |
      | Johnn       | A            | Doe        |          | john.doe11@email.com | 1980-01-01 | 123-45-6789 | Single    | 123-456-7890 | 098-765-4321  |

  And the user enters the following co-borrower information
    | c_firstName | c_middleName | c_lastName | c_suffix | c_email             | c_dob      | c_ssn       | c_marital | c_cell       | c_home        |
    | Jane        | B            | Doe        |          | jane.doe@email.com  | 1982-02-02 | 987-65-4321 | Single    | 321-654-0987 | 789-012-3456  |

    And all info in expenses page is filled
    And all info in Employment and income page filled
    And all info in Credit Report page filled
    And i should be able to enter my name and correct email
    Then he is able to proceed to the next page
    And click the save application


   Then the borrower and co-borrower information should be stored in the "tbl_mortagage" table
  #And email and name should have correct format