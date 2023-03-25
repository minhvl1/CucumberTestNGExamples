@EXAM
Feature: test
  @exam1
  Scenario: Calculator date
    Given Calculator between "20170220" and "20211224"
    Then  Calculator close date when add 1 month into "20170228"

  @exam2
  Scenario: PHP Travels
    Given Go to "https://phptravels.net/admin"
    Then Verify placeholder is display
    And Email placeholder have text should equal "Email"
    And Password placeholder have text should equal "Password"