Feature: Select
    This feature allows users to indicate which item they wish to comply
    with subsequent instructions.

Scenario: Select unselected unit
    When the user is playing human campaign 3
    And the user scrolls the screen to x:0 y:0
    And the user touches the screen at x:615 y:660
    Then unit "Footman1" will be selected
