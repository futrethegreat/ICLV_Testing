@ICLVDispute
Feature: ICLVDispute
  This feature deals with making a dispute in the ICLV using the web infrastructure.

  Scenario Outline: Make a dispute on an invoice for the full amount. Should be successful.
    Given I navigate to the ICLV home page
    When I click on Sign In link
    Then I should see the Login Page
    When I enter <UserName> and <Password>
    And I click Login button
    Then I should see the Tool main page
    Given I click the Payables link for "DANPER TRUJILLO S.A.C."
    When I click first invoice to dispute of "CAPITAL TOOL COMPANY."
    And I click the Dispute invoice implementation
    And I enter full pending amount
    And I enter a description "I do not agree with this invoice at all"
    And I click Execute
    And I enter "Welcome1" as password and the code provided
    And I click OK
    Then Status dispute of invoice is set to Yes
    Given I click supplier Invoices tab
    When I click on debtor "DANPER TRUJILLO S.A.C."
    Then a task is created to resolve the invoice disputed

    Examples: 
      | UserName   | Password |
      | davidsauce | Welcome1 |

  Scenario Outline: Make a dispute on an invoice for the full amount, however give a description too short.
    Given I navigate to the ICLV home page
    When I click on Sign In link
    Then I should see the Login Page
    When I enter <UserName> and <Password>
    And I click Login button
    Then I should see the Tool main page
    Given I click the Payables link for "DANPER TRUJILLO S.A.C."
    When I click first invoice to dispute of "CAPITAL TOOL COMPANY."
    And I click the Dispute invoice implementation
    And I enter full pending amount
    And I enter a description "Incorrect"
    And I click Execute
    Then the system should say it is too short

    Examples: 
      | UserName   | Password |
      | davidsauce | Welcome1 |

  Scenario Outline: Resolve an open dispute and check it returns to debtor.
    Given I navigate to the ICLV home page
    When I click on Sign In link
    Then I should see the Login Page
    When I enter <UserName> and <Password>
    And I click Login button
    Then I should see the Tool main page
    Given I click supplier Invoices tab
    When I click on debtor "DANPER TRUJILLO S.A.C."
    And select a disputed invoice
    And resolve the dispute with a note
    Then the invoice is no longer disputed
    Given I click To Pay for supplier
    When I click invoice resolved
    Then it is pending to be approved

    Examples: 
      | UserName   | Password |
      | davidsauce | Welcome1 |

  #Scenario Outline: Accept the solution given to a disputed invoice.
    #Given I navigate to the ICLV home page
    #When I click on Sign In link
    #Then I should see the Login Page
    #When I enter <UserName> and <Password>
    #And I click Login button
    #Then I should see the Tool main page
    #Given I click the Payables link for "DANPER TRUJILLO S.A.C."
    #When I click first invoice in "Solved" status
    #And I click the Approved dispute implementation
    #And I enter as Note "Approved solution for this invoice"
    #And I click Execute 
    #And I enter "Welcome1" as password and the code provided
    #And I click OK
    #Then the invoice status changes to "Solved and approved"
#
    #Examples: 
      #| UserName   | Password |
      #| davidsauce | Welcome1 |

  Scenario Outline: Reject the solution given to a disputed invoice.
    Given I navigate to the ICLV home page
    When I click on Sign In link
    Then I should see the Login Page
    When I enter <UserName> and <Password>
    And I click Login button
    Then I should see the Tool main page
    Given I click the Payables link for "DANPER TRUJILLO S.A.C."
    When I click first invoice in "Solved" status
    And I click the Rejected dispute implementation
    And I enter as Note "Rejected solution for this invoice"
    And I click Execute 
    And I enter "Welcome1" as password and the code provided
    And I click OK
    Then the invoice status changes to "Dispute"

    Examples: 
      | UserName   | Password |
      | davidsauce | Welcome1 |

  Scenario Outline: Resolve an open dispute and check it returns to debtor.
    Given I navigate to the ICLV home page
    When I click on Sign In link
    Then I should see the Login Page
    When I enter <UserName> and <Password>
    And I click Login button
    Then I should see the Tool main page
    Given I click supplier Invoices tab
    When I click on debtor "DANPER TRUJILLO S.A.C."
    And select a disputed invoice
    And resolve the dispute with a note
    Then the invoice is no longer disputed
    Given I click To Pay for supplier
    When I click invoice resolved
    Then it is pending to be approved

    Examples: 
      | UserName   | Password |
      | davidsauce | Welcome1 |

  Scenario Outline: Accept the solution given to a disputed invoice.
    Given I navigate to the ICLV home page
    When I click on Sign In link
    Then I should see the Login Page
    When I enter <UserName> and <Password>
    And I click Login button
    Then I should see the Tool main page
    Given I click the Payables link for "DANPER TRUJILLO S.A.C."
    When I click first invoice in "Solved" status
    And I click the Approved dispute implementation
    And I enter as Note "Approved solution for this invoice"
    And I click Execute 
    And I enter "Welcome1" as password and the code provided
    And I click OK
    Then the invoice status changes to "Solved and approved"

    Examples: 
      | UserName   | Password |
      | davidsauce | Welcome1 |
      