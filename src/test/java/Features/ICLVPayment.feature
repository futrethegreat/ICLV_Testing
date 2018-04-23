@ICLVPayment
Feature: ICLVPayment
  This feature deals with making a payment in the ICLV using the web infrastructure.

  Scenario Outline: Login with correct credentials and sign out afterwards.
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
    Then Open amount of invoice is decreased by 1.1 

    Examples: 
      | UserName   | Password |
      | davidsauce | Welcome1 |
      #| davidsauc3 | Welcome1 |
      #| davidsauce | Welcome2 |
      #|   | Welcome1 |
      #| davidsauce |   |
      #|   |   |
      
