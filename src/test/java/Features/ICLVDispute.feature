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
    Given I click the Payables tab
    When I click first invoice to dispute
    And I click the Dispute invoice implementation
    And I enter a description "I do not agree with this invoice at all"
    And I click Execute
    And I enter "Welcome1" as password and the code provided
    And I click OK
    Then Status dispute of invoice is set to Yes
    #Then a task is create for my supplier to resolve the dispute 

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
    Given I click the Payables tab
    When I click first invoice to dispute
    And I click the Dispute invoice implementation
    And I enter a description "Incorrect"
    And I click Execute
    Then the system should say it is too short

    Examples: 
      | UserName   | Password |
      | davidsauce | Welcome1 |
      
  #Scenario Outline: Make a dispute on an invoice for 1000 PEN. Should be successful.
  #note that the amount field is missing at the moment
  