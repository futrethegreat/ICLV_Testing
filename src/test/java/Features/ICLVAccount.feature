# Withdraws money from an account with no payment programmed (success) and
# withdraws moeny from an account with payment programmed (fail)
#
# Scenario 1: Withdraw money from account and check the amount is reduced.
# Scenario 2: Withdraw money from account with a payment already programmed for the day. Should fail.
#
@ICLVAccount
Feature: ICLVAccount
  This feature deals with the management of accounts in the ICLV using the web infrastructure.

  Scenario Outline: Withdraw money from account and check the amount is reduced.
    Given I navigate to the ICLV home page
    When I click on Sign In link
    Then I should see the Login Page
    When I enter <UserName> and <Password>
    And I click Login button
    Then I should see the Tool main page
    Given I click the Account link for "DANPER TRUJILLO S.A.C."
    When I click first account in "PEN" currency and with Saldo
    And I click the Withdraw implementation
    And I enter "500" as the amount to withdraw
    And I click Execute withdraw
    And I enter "123456Ab" as password and the code provided for the withdraw
    And I click OK for the withdraw
    Then Saldo of account is decreased by "500"

    Examples: 
      | UserName   | Password |
      | seleniumtesting | 123456Ab|

  Scenario Outline: Withdraw money from account with a payment already programmed for the day. Should fail.
    Given I navigate to the ICLV home page
    When I click on Sign In link
    Then I should see the Login Page
    When I enter <UserName> and <Password>
    And I click Login button
    Then I should see the Tool main page
    Given I click the Account link for "DANPER TRUJILLO S.A.C."
    When I click first account in "PEN" currency and with Saldo
    And I click the Withdraw implementation
    And I enter "500" as the amount to withdraw
    And I click Execute withdraw
    Then an error message is displayed

    Examples: 
      | UserName   | Password |
      | seleniumtesting | 123456Ab|
