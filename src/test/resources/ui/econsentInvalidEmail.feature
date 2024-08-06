Feature:  As a mortgage loan applicant i want to reach Econsent page by filling previous pages.
  Background:
    Given I am logging to the Duobank
  @navigation4
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

Scenario Outline:Enter invalid email
When i enter invalid email"<invalidEmail>"
Then i should see an error message for email
Examples:
| invalidEmail          |
| invalidemail.com      |
| another@invalidemail  |