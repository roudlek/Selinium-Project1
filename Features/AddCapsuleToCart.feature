#
Feature: Add Capsule to cart
@Ready
  Scenario Outline: Add product to cart with valid quantity
    Given User is on the home page
    And User navigates to the capsules page
    When User adds product <productName> to the cart with a valid quantity <quantity>
    And User opens the shopping cart
    Then the quantity of the selected product <productName> in the cart should be <quantity>
    And the user closes the shopping cart

  Examples:
  |productName|quantity|
  |Chiaro     |20      |
  |Ristretto  |50      |


  @NotReady
  Scenario: Add to cart product with invalid quantity
    Given User is on the home page
    And User navigates to the capsules page
    When User adds product <productName> to the cart with a valid quantity <quantity>
    And User opens the shopping cart
    Then the quantity of the selected product <productName> in the cart should be <quantity>
    And the user closes the shopping cart