@user @smoke @regression
Feature: Get one user end point test

  User Story: As a user sending GET request to the endpoint and providing ID number as a path parameter
              I should be able to get the http status code 200 OK and the JSON object should contain given ID and name

  Scenario: Get one user by providing the existing ID
    Given The user with given ID created and exists
    When I send a GET request to the end point with the appropriate ID
    Then I should get the 200 OK http status code
    And My payload contains given name and ID




