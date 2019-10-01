Feature: Some simple request

  Scenario: See list elements after request
    Given Network is enabled
    When I refresh the site
    Then I see header on the screen
    And I see content on the screen
    And I see text only container on the screen

  Scenario: Show no network message
    Given Network is disabled
    When I refresh the site
    Then I see no network message on the screen