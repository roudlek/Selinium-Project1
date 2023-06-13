#
Feature: Add product to cart feature
  Verify if user is able to add product to cart
@Ready
  Scenario: Add to cart product with valid quantity
    Given User is in home page
    And User navigates to products page
    When User adds product to cart with a valid quantity
    And User opens shopping cart
    Then Show success message

  @NotReady
  Scenario: Add to cart product with invalid quantity
    Given User is in home page
    And User navigates to products page
    When User adds product to cart with an invalid quantity
    And User opens shopping cart
    Then Show failure message