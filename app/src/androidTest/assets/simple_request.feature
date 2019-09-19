Feature: Some simple request

  Scenario: See header after request
    Given Network is enabled
    When I want to make request
    Then I see the header at the top

  Scenario: See content after request
    Given Network is enabled
    When I want to make request
    Then I see the content

  Scenario: See text after request
    Given Network is enabled
    When I want to make request
    Then I see text

  Scenario: Show network error message
    Given Network has errors
    When I want to make request
    Then I see network error message on the screen

  Scenario: Show no network message
    Given Network is disabled
    When I want to make request
    Then I see no network message on the screen