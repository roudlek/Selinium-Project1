Feature: Add product to cart feature
  Verify if user is able to add product to cart
@Ready
  Scenario Outline: add to cart product with valid quantity
    Given user is in home page
    And user navigates to products page
    And user clicked on add to cart button of specified product
    When user typed valid quantity <Quantity>
    And clicked on ok button
    And opened filled cart
    Then compare operation happens and success message
    Examples:
      |Quantity|
      |10|
      |20|
      |50|


  Scenario Outline: add to cart product with invalid quantity
    Given user is in home page
    And user navigates to products page
    And user clicked on add to cart button of specified product
    When user typed invalid quantity <Quantity>
    And clicked on ok button
    And opened filled cart
    Then compare operation happens and success message
    Examples:
      |Quantity|
      |600|
      |3|
      |57|