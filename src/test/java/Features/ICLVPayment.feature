# Test different ways of payment for an invoice:
# Successful: Full payment, partial payment.
# Failure: 0 payment, more than pending amount, not enough funds to pay.
#
# Scenario 1: Make a payment for the total amount. Should be successful.
# Scenario 2: Make a payment smaller than the total amount. Should be successful.
# Scenario 3: Make a payment of 0. Should be not possible.
# Scenario 4: Make a payment larger than the total amount. Should be not possible.
# Scenario 5: Make a payment for a debtor with not enough funds.
#

@ICLVPayment
Feature: ICLVPayment
  This feature deals with making a payment in the ICLV using the web infrastructure.

  Scenario Outline: Make a payment for the total amount. Should be successful.
    Given I navigate to the ICLV home page
    When I click on Sign In link
    Then I should see the Login Page
    When I enter <UserName> and <Password>
    And I click Login button
    Then I should see the Tool main page
    Given I click the Payables link for "DANPER TRUJILLO S.A.C."
    When I click first invoice not disputed of "CAPITAL TOOL" to pay it
    And I click the Pay invoice implementation
    And I enter the amount "Full"
    And I click Execute
    And I enter "123456Ab" as password and the code provided
    And I click OK
    Then Open amount of invoice is decreased by "Full" 
    #And Amount in database is also updated

    Examples: 
      | UserName   | Password |
      | seleniumtesting | 123456Ab |
      
  Scenario Outline: Make a payment smaller than the total amount. Should be successful.
    Given I navigate to the ICLV home page
    When I click on Sign In link
    Then I should see the Login Page
    When I enter <UserName> and <Password>
    And I click Login button
    Then I should see the Tool main page
    Given I click the Payables link for "DANPER TRUJILLO S.A.C."
    When I click first invoice not disputed of "CAPITAL TOOL" to pay it    
    And I click the Pay invoice implementation
    And I enter the amount "1.1"
    And I click Execute
    And I enter "123456Ab" as password and the code provided
    And I click OK
    Then Open amount of invoice is decreased by "1.1" 
    #And Amount in database is also updated

    Examples: 
      | UserName   | Password |
      | seleniumtesting | 123456Ab |
      
  Scenario Outline: Make a payment of 0. Should be not possible.
    Given I navigate to the ICLV home page
    When I click on Sign In link
    Then I should see the Login Page
    When I enter <UserName> and <Password>
    And I click Login button
    Then I should see the Tool main page
    Given I click the Payables link for "DANPER TRUJILLO S.A.C."
    When I click first invoice not disputed of "CAPITAL TOOL" to pay it
    And I click the Pay invoice implementation
    And I enter the amount "0"
    And I click Execute
    Then the system should say it is not possible 

    Examples: 
      | UserName   | Password |
      | seleniumtesting | 123456Ab |

  Scenario Outline: Make a payment larger than the total amount. Should be not possible.
    Given I navigate to the ICLV home page
    When I click on Sign In link
    Then I should see the Login Page
    When I enter <UserName> and <Password>
    And I click Login button
    Then I should see the Tool main page
    Given I click the Payables link for "DANPER TRUJILLO S.A.C."
    When I click first invoice not disputed of "CAPITAL TOOL" to pay it    
    And I click the Pay invoice implementation
    And I enter an amount larger than the remaining
    And I click Execute
    Then the system should say it is not possible 

    Examples: 
      | UserName   | Password |
      | seleniumtesting | 123456Ab |
      
  Scenario Outline: Make a payment for a debtor with not enough funds.
    Given I navigate to the ICLV home page
    When I click on Sign In link
    Then I should see the Login Page
    When I enter <UserName> and <Password>
    And I click Login button
    Then I should see the Tool main page
    Given I click the Payables link for "DANPER TRUJILLO S.A.C."
    When I click first invoice not disputed of "LA HACIENDA" to pay it
    And I click the Pay invoice implementation
    And I enter the amount "Full"
    And I click Execute
    Then the system should say there is no sufficient amount 

    Examples: 
      | UserName   | Password |
      | seleniumtesting | 123456Ab |
      