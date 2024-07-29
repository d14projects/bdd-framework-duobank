Feature:  Mortgage Application Dashboard
  As a user who has applied for a mortgage with the bank,
  I want to access the dashboard page of the bank's mortgage application system
  So that I can view my account information, apply for a new mortgage, and view a list of my previous mortgage applications.

 Background:
   @dashboard
  Scenario: Displaying bank's logo on the dashboard
    Given I am logged into the application
    When I navigate to the dashboard page
    Then I should see the bank's logo in the top left corner of the page
    And I should see a link labeled "Mortgage Application"
    And I should see a link labeled "Application List"

     When I click on the "Mortgage Application" link
     Then I should be taken to the Mortgage Application page

     When I click on the next "Application List" link
     Then I should be taken to the Application List page

     Then I should see my account information including my name and profile picture in the top right corner of the page

     When i  click on the profile picture
     Then i should see logout button and click on it
     And i should be able to logout


