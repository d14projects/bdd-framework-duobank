#Feature: As a mortgage loan applicant, i want to consent and sign disclosures related with application.
  Feature:  As a mortgage loan applicant i want to reach Econsent page by filling previous pages.
  Background:
    Given I am logging to the Duobank
@navigation3
   Scenario:Navigation to Econsent page

    When i am clicking to the Mortgage Application
    Then all information in preapproval details should be filled
    And  info in Personal Info page is filled as following
  | First Name| Yana|
  |Last Name  | Vlas|
  |Email      | janus30@mail.ru|
  |SSN        |123456789       |
  |Cell Phone |123-456-7899    |
    And all info in expenses page is filled
    And all info in Employment and income page filled
    And all info in Credit Report page filled


    When i do not fill first, last name and email
    Then i should see an error message


  And i should be able to enter my name and correct email
  And i should see clear text for disclosure
  And i should see "Agreee" and "Disagree" buttons
 # And "Agree" button should be selected by default


  When none of the buttons clicked
  Then user cant submit
  And error message should be displayed

  When user stays with on "agree" button
  Then he is able to proceed to the next page



    @disagreement @navigation3
    Scenario:disagree button process
   When user click on "Disagree" button
   Then alert message should pop up
   And User will be redirected to mane page








