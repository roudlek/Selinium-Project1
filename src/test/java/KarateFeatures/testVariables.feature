Feature: Simple api test
  Scenario: Test a sample get api

#    url is something fixed
    * def domainName = 'reqres.in'
    * def userId = '2'
#    Given url 'https://' + domaineName +'/api/users?page=2'
    Given url `https://${domainName}`
    And path '/api/users?page='
    And path userId
    * def num = 5
    * def myMessage = 'whats up'
    * def myBoolean = true
      #xml
    * def parent = <parent>we are the parents</parent>
    * def foo = function(arg){ return arg + bar }
    * print num
    * print 'My message for you is: ', myMessage, ' and my number is: ', num
    When method GET
    Then status 200
    And match response != null

# comment
