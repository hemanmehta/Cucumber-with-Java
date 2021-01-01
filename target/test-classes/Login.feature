Feature: Login functionality feature
  
  In order to do internet banking
  As a valid Para Bank customer
  I want to login successfully

  Scenario: Login Successful
    Given I am in Login page of the Para Bank Application
    When I enter credentials
    Then I should be taken to Overview Page

  Scenario Outline: Login Successful
    Given I am in login page
    When I enter valid <username> and <password>
    Then I should be taken to Overview Page

    Examples:
		|username|password|
		|"autotester"|"password"|
		|"tautestuser"|"password"|
		
		Scenario: Login Successful using DataTable
    Given I am in Login page of the Para Bank Application
    When I enter credentials with datatable
    |"autotester"|"password"|
    Then I should be taken to Overview Page
    
    Scenario: Login Successful using Dependency Injection
    Given I am in login page
    When I enter valid <username> and <password> with <userFullName>
    Then I should be taken to DI Overview Page

    Examples:
		|username|password|userFullName|
		|"autotester"|"password"|" Raghu sastri"|
		|"tautester"|"password"|" Raghu sastri"|