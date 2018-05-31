@ICLVApproval
Feature: ICLVApproval
  This feature deals with the approval of an invoice in the ICLV using the web infrastructure.

  Scenario Outline: Approve a not approved invoiced. Should be successful.
    Given I navigate to the ICLV home page
    When I click on Sign In link
    Then I should see the Login Page
    When I enter <UserName> and <Password>
    And I click Login button
    Then I should see the Tool main page
    Given I click the Payables link for "DANPER TRUJILLO S.A.C."
    When I click first invoice with Approved status "No" to approve it
    And I click the Approve implementation
    And I enter "Approved" as note 
    And I click Execute
    And I enter "Welcome1" as password and the code provided
    And I click OK
    Then Approved status is set to "Yes" 

    Examples: 
      | UserName   | Password |
      | davidsauce | Welcome1 |

  Scenario Outline: Approve an approved invoice. Should be not possible.
    Given I navigate to the ICLV home page
    When I click on Sign In link
    Then I should see the Login Page
    When I enter <UserName> and <Password>
    And I click Login button
    Then I should see the Tool main page
    Given I click the Payables link for "DANPER TRUJILLO S.A.C."
    When I click first invoice with Approved status "Yes" to approve it
    And I click the Approve implementation
    And I enter "Approved" as note 
    And I click Execute
    Then the system should say it is already approved 

    Examples: 
      | UserName   | Password |
      | davidsauce | Welcome1 |
      