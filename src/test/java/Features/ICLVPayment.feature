@ICLVPayment
Feature: ICLVPayment
  This feature deals with making a payment in the ICLV using the web infrastructure.

  Scenario Outline: Make a payment smaller than the total amount. Should be successful.
    Given I navigate to the ICLV home page
    When I click on Sign In link
    Then I should see the Login Page
    When I enter <UserName> and <Password>
    And I click Login button
    Then I should see the Tool main page
    Given I click the Payables tab
    When I click first invoice to pay
    And I click the Pay invoice implementation
    And I enter the amount 1.1
    And I click Execute
    And I enter "Welcome1" as password and the code provided
    And I click OK
    Then Open amount of invoice is decreased by 1.1 

    Examples: 
      | UserName   | Password |
      | davidsauce | Welcome1 |
      
  Scenario Outline: Make a payment of 0. Should be not possible.
    Given I navigate to the ICLV home page
    When I click on Sign In link
    Then I should see the Login Page
    When I enter <UserName> and <Password>
    And I click Login button
    Then I should see the Tool main page
    Given I click the Payables tab
    When I click first invoice to pay
    And I click the Pay invoice implementation
    And I enter the amount 0
    And I click Execute
    Then the system should say it is not possible 

    Examples: 
      | UserName   | Password |
      | davidsauce | Welcome1 |

  Scenario Outline: Make a payment larger than the total amount. Should be not possible.
    Given I navigate to the ICLV home page
    When I click on Sign In link
    Then I should see the Login Page
    When I enter <UserName> and <Password>
    And I click Login button
    Then I should see the Tool main page
    Given I click the Payables tab
    When I click first invoice to pay
    And I click the Pay invoice implementation
    And I enter an amount larger than the remaining
    And I click Execute
    Then the system should say it is not possible 

    Examples: 
      | UserName   | Password |
      | davidsauce | Welcome1 |
      