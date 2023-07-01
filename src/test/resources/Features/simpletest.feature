Feature: print something

  @SimpleTest
    @SkipHook
  Scenario: print something for test
    Given User enters coffee shop
    When User asks for coffee
    Then We gave him coffe
