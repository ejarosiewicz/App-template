Feature: Some simple request

  Scenario: Do request on click
    Given Network is enabled
    When I want to make request
    Then I see the result on the screen


  Scenario: Show no network message
    Given Network is disabled
    When I want to make request
    Then I see no error message on the screen