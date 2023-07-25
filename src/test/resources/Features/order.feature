
Feature: the aim of this technical test is to see how the candiate will use locators 
to find specific block (add button) per product name in a list of products


  @valid_Order
  Scenario Outline: Order some capsules
    Given User opens the url "https://ma.buynespresso.com/ma_fr/cafe.html"
    When User scrolls to <product> capsule
    And User adds <quantity> units of <product> capsule to cart
    Then Mini cart contains <quantity> units of <product> capsule 
    Examples:
      |product|quantity|
      |TOKYO VIVALTO LUNGO| 10 |
