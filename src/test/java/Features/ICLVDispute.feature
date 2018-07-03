# Test the dispute flow for an invoice: Dispute - Resolve - Reject - Resolve - Approve and
# try to dispute an invoice giving a short description (fail)
#
# Scenario 1: Make a dispute on an invoice for the full amount, however give a description too short.
# Scenario 2: Make a dispute on an invoice for the full amount. Should be successful.
# Scenario 3: Resolve an open dispute and check it returns to debtor.
# Scenario 4: Reject the solution given to a disputed invoice.
# Scenario 5: Resolve an open dispute and check it returns to debtor.
# Scenario 6: Accept the solution given to a disputed invoice.
#

@ICLVDispute
Feature: ICLVDispute
  This feature deals with making a dispute in the ICLV using the web infrastructure.
  - Process: 2186 ICLV Implementations.
  - Activity: 31617 Query. Query the invoice.
  - Implementation: 1325 ICLV dispute.

  Background: Common tasks for all scenarios
    Given I navigate to the ICLV home page
    When I click on Sign In link
    Then I should see the Login Page
    When I enter seleniumtesting and 123456Ab
    And I click Login button
    Then I should see the Tool main page

  Scenario: Make a dispute on an invoice for the full amount, however give a description too short.
  	Process: 2186 ICLV Implementations.
  	Activity: 31617 Query. Query the invoice.
  	Implementation: 1325 ICLV dispute.
    Given I click the Payables link for "DANPER TRUJILLO S.A.C."
    When I click first invoice to dispute of "CAPITAL TOOL COMPANY."
    And I click the Dispute invoice implementation
    And I enter full pending amount
    And I enter a description "Incorrect"
    And I click Execute
    Then the system should say it is too short

  Scenario: Make a dispute on an invoice for the full amount. Should be successful.
  	Process: 2186 ICLV Implementations.
  	Activity: 31617 Query. Query the invoice.
  	Implementation: 1325 ICLV dispute.
    Given I click the Payables link for "DANPER TRUJILLO S.A.C."
    When I click first invoice to dispute of "CAPITAL TOOL COMPANY."
    And I click the Dispute invoice implementation
    And I enter full pending amount
    And I enter a description "I do not agree with this invoice at all"
    And I click Execute
    And I enter "123456Ab" as password and the code provided
    And I click OK
    Then Status dispute of invoice is set to Yes
    Given I click supplier To Receive tab
    When I click on debtor "DANPER TRUJILLO S.A.C."
    Then a task is created to resolve the invoice disputed

  Scenario: Resolve an open dispute and check it returns to debtor.
  	Process: 2186 ICLV Implementations.
  	Activity: 31619 Resolve. Review the query of the debtor, and provide a resolution to the query.
  	Implementation: 1330 ICLV dispute solved.
    Given I click supplier To Receive tab
    When I click on debtor "DANPER TRUJILLO S.A.C."
    And select a disputed invoice
    And resolve the dispute with a note
    Then the invoice is no longer disputed
    Given I click To Pay for supplier
    When I click invoice resolved
    Then it is pending to be approved

  Scenario: Reject the solution given to a disputed invoice.
  	Process: 2186 ICLV Implementations.
  	Activity: 31834 Reject the dispute.
  	Implementation: 1334 ICLV dispute resolution rejected.
    Given I click the Payables link for "DANPER TRUJILLO S.A.C."
    When I click first invoice in "Solved" status
    And I click the Rejected dispute implementation
    And I enter as Note "Rejected solution for this invoice"
    And I click Execute 
    And I enter "123456Ab" as password and the code provided
    And I click OK
    Then the invoice status changes to "Dispute"

  Scenario: Resolve an open dispute and check it returns to debtor.
  	Process: 2186 ICLV Implementations.
  	Activity: 31619 Resolve. Review the query of the debtor, and provide a resolution to the query.
  	Implementation: 1330 ICLV dispute solved.
    Given I click supplier To Receive tab
    When I click on debtor "DANPER TRUJILLO S.A.C."
    And select a disputed invoice
    And resolve the dispute with a note
    Then the invoice is no longer disputed
    Given I click To Pay for supplier
    When I click invoice resolved
    Then it is pending to be approved

  Scenario: Approve the solution given to a disputed invoice.
  	Process: 2186 ICLV Implementations.
  	Activity: 31832 Approved. Approve the resolution that was supplied to the query.
  	Implementation: 1332 ICLV dispute resolution approved.
    Given I click the Payables link for "DANPER TRUJILLO S.A.C."
    When I click first invoice in "Solved" status
    And I click the Approved dispute implementation
    And I enter as Note "Approved solution for this invoice"
    And I click Execute 
    And I enter "123456Ab" as password and the code provided
    And I click OK
    Then the invoice status changes to "Solved and approved"
