Feature : Add machine to cart
  @NotReady
  Scenario Outline: Add Machine to cart with valid quantity
  Given User is on the home page
  And User navigates to the machines page
  When User adds product <productName> to the cart with a valid quantity <quantity>
  And User opens the shopping cart
  Then the quantity of the selected product <productName> in the cart should be <quantity>
  And the user closes the shopping cart

  Examples:
  |productName|quantity|
  |Chiaro     |20      |
  |Ristretto  |50      |