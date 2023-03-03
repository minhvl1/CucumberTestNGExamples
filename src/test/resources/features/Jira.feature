@Jira
  Feature: Jira issue
    Scenario:  Authenticated
      Given Authen jira
      Then Show reponse

    Scenario: Create issue
      Given Create issue
      Then Give id issue


    @DeleteIssue
    Scenario: Delete issue
      Given Delete issue