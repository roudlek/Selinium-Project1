Feature: Add product to cart feature
  Verify if user is able to add product to cart
@Ready
  Scenario Outline: add to cart product with valid quantity
    Given user is in home page
    And user navigates to products page
    And user clicked adds product to cart
#    When user typed valid quantity <quantity typed>
    When user picked a valid quantity
    And clicked on ok button
    And Wait for cart to be updated
    And opened filled cart
    Then verifie product quantity <quantity typed> of first product(second row,first cell) in excel file
    Examples:
      |quantity typed|
      |"10"          |
      |"20"          |

#  i think i will use scenario and add the quantity as well in the excel sheet

  Scenario Outline: add to cart product with invalid quantity
    Given user is in home page
    And accept cookie
    And user navigates to products page
    And user clicked on add to cart button of specified product
    When user typed invalid quantity <Quantity>
    And clicked on ok button
    And Wait for cart to be updated
    And opened filled cart
    Then Assert that actual quantity in span of specified product equal to expected value
    And proceed to checkout
    Examples:
      |Quantity|
      |600|
      |3|
      |57|