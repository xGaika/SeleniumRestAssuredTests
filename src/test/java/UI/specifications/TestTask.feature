@TestTaskTag
Feature: feature to test google search functionality

  Scenario Outline: Login and validate player table sort is working

    Given user is on login page
    When user login with <username> and <password>
    And get players
    And sort players by ExternalId
    Then check that players sorted by externalId

    Examples:
    | username |     password     |
    | admin1   | [9k<k8^z!+$$GkuP |