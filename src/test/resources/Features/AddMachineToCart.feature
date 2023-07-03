@Nesspresso
Feature: Add machine to cart
@Ready
  Scenario Outline: Add Machine to cart with valid quantity
    Given User is on the home page
    And User navigates to the machines page
    When User adds Machine <productName> to the cart with a valid quantity <quantity>
    And User opens the shopping cart
    Then the quantity of the selected product <productName> in the cart should be <quantity>
    And the user closes the shopping cart
    Examples:
      | productName | quantity |
      | Inissia     | 1        |