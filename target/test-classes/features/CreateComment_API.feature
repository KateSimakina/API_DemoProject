
@comment @smoke
Feature: Create a user comment feature
  User Story: As a user I should be able to create a user comment under a user post

  Scenario: Create a user comment
    Given The user created and the user post created by providing the existing ID
    When I send a POST request to comments endpoint with valid payload by providing the existing post ID
    Then I should get the 201 Created http status code
    And The response payload should contain following fields:
      | id      |
      | post_id |
      | name    |
      | email   |
      | body    |

#TODO:
# Negative TC :
#POST a user
#Validate a response
#Get the user ID
#By using ID create a user post
#By using ID create a user comment
#By using ID delete a user post
#By using ID retrieve a user comment (negative test)
#Expected an error
