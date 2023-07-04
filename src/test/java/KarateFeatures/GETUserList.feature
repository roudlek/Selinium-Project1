Feature: Simple api test

  Background:
    * url 'https://reqres.in'

  Scenario: Test a sample get api

    Given def pageId = '1'
    And path 'api','users','?page=',pageId
    When method GET
    Then status 200
    And match response != null

  Scenario: get one user and print it id value
    * path '/api/users'
    When method GET
    * def userId = response[0]
    * print userId
    Then status 200
    And print userId