# Feature to be launched before the others to test create invoice functionality 
# and also to get the environment ready for the next features. 
# It is needed to have invoices ready to test payments, disputes and approvals.
#
# Creates two invoices and then submit all invoices in Draft to change status to Open.
#
# Scenario 1: Creates an invoice. Should be successful.
# Scenario 2: Creates an invoice. Should be successful.
# Scenario 3: Submit all invoices in draft state. Should be successful.
#
@01-ICLVCreateInvoice
Feature: 01-ICLVCreateInvoice
  This feature creates a new invoice from CAPITAL TOOL LATIN AMERICA to DANPER TRUJILLO.

  Scenario Outline: Creates an invoice. Should be successful.
    Given I navigate to the ICLV home page
    When I click on Sign In link
    Then I should see the Login Page
    When I enter <UserName> and <Password>
    And I click Login button
    Then I should see the Tool main page
    Given I click the Invoices link for "CAPITAL TOOL COMPANY LATIN AMERICA S.A.C."
    When I click debtor "DANPER TRUJILLO" 
    And I click contact "piet klassse"
    And I click new Invoice implementation
    And I enter "1" as Codigo
    And I enter a random value as Valor
    And I enter "Description" as Description
    And I click Execute to create invoice
    Then the new Invoice is displayed in Documents table 

    Examples: 
      | UserName   | Password |
      | seleniumtesting | 123456Ab|
      
  Scenario Outline: Creates an invoice. Should be successful.
    Given I navigate to the ICLV home page
    When I click on Sign In link
    Then I should see the Login Page
    When I enter <UserName> and <Password>
    And I click Login button
    Then I should see the Tool main page
    Given I click the Invoices link for "CAPITAL TOOL COMPANY LATIN AMERICA S.A.C."
    When I click debtor "DANPER TRUJILLO" 
    And I click contact "piet klassse"
    And I click new Invoice implementation
    And I enter "1" as Codigo
    And I enter a random value as Valor
    And I enter "Description" as Description
    And I click Execute to create invoice
    Then the new Invoice is displayed in Documents table 

    Examples: 
      | UserName   | Password |
      | seleniumtesting | 123456Ab|

  Scenario Outline: Submit all invoices in draft state. Should be successful.
    Given I navigate to the ICLV home page
    When I click on Sign In link
    Then I should see the Login Page
    When I enter <UserName> and <Password>
    And I click Login button
    Then I should see the Tool main page
    Given I click the Invoices link for "CAPITAL TOOL COMPANY LATIN AMERICA S.A.C."
    When I click debtor "DANPER TRUJILLO" 
    And I click contact "piet klassse"
    And I submit all invoices in Draft status
    Then all the invoices in Draft are changed to Open

    Examples: 
      | UserName   | Password |
      | seleniumtesting | 123456Ab|
