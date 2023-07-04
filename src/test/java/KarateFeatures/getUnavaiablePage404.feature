Feature: Simple api test
  Scenario: Test a sample get api

    Given url 'https://reqres.in/api/unknown/23'
    When method GET
    Then status 404