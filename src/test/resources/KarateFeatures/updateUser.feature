Feature: Simple api test
  Scenario: Test a sample get api

    Given url 'https://reqres.in/api/users/2'
    And request {"name": "morpheus","job": "zion resident"}
    When method PUT
    Then status 200
    And match response != null

#    Given url 'https://reqres.in/api/users/2'
#    And request {"name": "morpheus","job": "zion resident"}
#    When method PUT
#    Then status 200
#    And match response == {"name": "morpheus","job": "zion resident","updatedAt": "2023-07-03T10:42:11.708Z"}
#    And match response != null