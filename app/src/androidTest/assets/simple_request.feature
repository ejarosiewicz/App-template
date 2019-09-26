Feature: Some simple request

  Scenario: See list elements after request
    Given Network is enabled
    When I want to make request
    Then I see header on the screen
    And I see content on the screen
    And I see text only container on the screen

#  Scenario: Show network error message
#    Given Network has errors
#    When I want to make request
#    Then I see network error message on the screen
#
#  Scenario: Show no network message
#    Given Network is disabled
#    When I want to make request
#    Then I see no network message on the screen