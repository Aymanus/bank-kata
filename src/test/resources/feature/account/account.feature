Feature: US 1
  In order to save money, As a bank client I want to make a deposit in my account
  
  Scenario: deposit in account
    Given bank account
    When i make a deposit of 50 euros at date 2016-06-01
    And i make a withdrawal of 30 euros at date 2016-06-02
    And i make a deposit of 100 euros at date 2016-06-03
    Then my balance should be 120 euros
    And my historical is :
    | Date      | Amount | Balance |
    |2016-06-01 |     50 |        0|
    |2016-06-02 |     -30|       50|
    |2016-06-03 |     100|       20|