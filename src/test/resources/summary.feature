@REGRESSION @summary
Feature: User Story 10: Summary Page Functionality
  As a mortgage loan applicant,
  I want to be able to review and edit the details of my loan application on a summary page
  so that I can ensure that all of the information is accurate and up-to-date.

  I want to be able to edit any section of the application,including the pre-approval inquiry, personal details,
  expenses, employment and income, and eConsent.
  Once I have made any necessary changes, I want to be able to submit the application

  Background:
    Given the user navigates to the sign in page
    When the user enters correct credentials and clicks Sign In button
    And the user is redirected to the mortgage account dashboard
    And the user clicks on Mortgage Application link and fills out the form till e-Consent inclusive

#  @summary
  Scenario: The Summary page should be accessible from the main application flow and should clearly indicate
  that it is a review and edit page for the application.
    Then The Summary page should be displayed and contain edit buttons

#  @summary
  Scenario: The page should display all the information that the user has previously entered in each section
  of the application, including the pre-approval inquiry, personal details, expenses, employment and income, and eConsent.
    Then the page should display all the information that the user has previously entered in the in each section
      | section             |
      | PreApproval Inquiry |
      | Personal Details    |
      | Expenses            |
      | Employment & Income |
      | eConsent            |

#  @summary
  Scenario: Each section of the application should be clearly labeled and separated from the other sections to make it
  easy for the user to find the information they need to review or edit.
    Then each section should be clearly labeled
      | label1 | PreApproval Inquiry |
      | label2 | Personal Details    |
      | label3 | Expenses            |
      | label4 | Employment & Income |
      | label5 | Order Credit        |
      | label6 | eConsent            |

#  @summary
  Scenario: For each section of the application, there should be an "Edit" button that the user can click to make changes
  to that section.
    Then each section should have "Edit" button displayed

#  @summary
  Scenario: Clicking the "Edit" button for a section should take the user back to the relevant page in the main application
  flow so that they can make any necessary changes.
    When the user clicks on the Edit button for a "PreApproval Details" section
    Then the user is taken to the PreApproval page of the application
    When the user clicks on the Edit button for a "Personal Information" section
    Then the user is taken to the Personal Information page of the application
    When the user clicks on the Edit button for a "Expenses" section
    Then the user is taken to the Expenses page of the application
    When the user clicks on the Edit button for a "Employment and Income" section
    Then the user is taken to the Employment and Income page of the application
    When the user clicks on the Edit button for a "Credit Report" section
    Then the user is taken to the Credit Report page of the application
    When the user clicks on the Edit button for a "eConsent" section
    Then the user is taken to the E-Consent page of the application

#  @summary
  Scenario: The Summary page should include a "Submit" button that the user can click once they have reviewed and
  edited all sections of the application.
    Then the user is able to see the Submit button

#  (typo in Submit message)
#  @summary
  Scenario: Clicking the "Submit" button should submit the application and take the user to a confirmation page that
  confirms that their application has been received and will be reviewed by the bank.
    When the user clicks on the Submit button
    Then the user should see Application Submitted Successfully message
