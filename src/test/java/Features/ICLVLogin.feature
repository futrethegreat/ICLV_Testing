@ICLVLogin
Feature: ICLVLogin
  This feature deals with the login functionality of the ICLV web page.

  Scenario Outline: Login with correct credentials and sign out afterwards.
    Given I navigate to the ICLV home page
    When I click on Sign In link
    Then I should see the Login Page
    When I enter <UserName> and <Password>
    And I click Login button
    Then I should see the Tool main page
    When I click on Sign Out link
    Then I should see the Sign Out page

    Examples: 
      | UserName   | Password |
      | davidsauce | Welcome1 |
      #| davidsauc3 | Welcome1 |
      #| davidsauce | Welcome2 |
      #|   | Welcome1 |
      #| davidsauce |   |
      #|   |   |
      
  Scenario Outline: Login with incorrect and empty credentials
    Given I navigate to the ICLV home page
    When I click on Sign In link
    Then I should see the Login Page
    When I enter <UserName> and <Password>
    And I click Login button
    Then I should receive error message

    Examples: 
      | UserName   | Password |
      | davidsauce | Welcome2 | 
      | davidsauc3 | Welcome1 | 
      | davidsauce |  |
      |  | Welcome2 |
      |  |  |