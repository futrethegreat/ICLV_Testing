# try to dispute an invoice giving a short description (fail)
#
# Scenario 1: Login with correct credentials and sign out afterwards.
# Scenario 2: Login with incorrect and empty credentials
#							Correct user name / wrong password
#							Wrong user name / correct password
#							Correct user name / empty password
#							Empty user name / correct password
#							Empty user name / empty password
#
@ICLVLogin
Feature: ICLVLogin
  This feature deals with the login functionality of the ICLV web page.
  - Process: -.
  - Activity: -.
  - Implementation: -.

  Background: Common tasks for all scenarios
    Given I navigate to the ICLV home page
    When I click on Sign In link
    Then I should see the Login Page

  Scenario Outline: Login with correct credentials and sign out afterwards.
    When I enter <UserName> and <Password>
    And I click Login button
    Then I should see the Tool main page
    When I click on Sign Out link
    Then I should see the Sign Out page

    Examples: 
      | UserName   | Password |
      | seleniumtesting | 123456Ab |
      
  Scenario Outline: Login with incorrect and empty credentials
    When I enter <UserName> and <Password>
    And I click Login button
    Then I should receive error message

    Examples: 
      | UserName   | Password |
      | seleniumtesting | 123456AB |
      | seleniumtestin | 123456Ab |
      | seleniumtesting |  |
      |  | 123456Ab |
      |  |  |