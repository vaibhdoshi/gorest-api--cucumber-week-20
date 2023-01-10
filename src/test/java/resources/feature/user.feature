Feature:Testing different request on Gorest Application

  Scenario: check if Gorest application can be access by User
    When User sends a Get request
    Then User should get list of users and must get back a valid staus code 200

  Scenario:Create a new user & verify if user is added
    When I create a new user by providing the information name "<name>" email "<email>" gender "<gender>" status "<status>"
    Then I verify that user is created with userId and must get valid staus code 201

  Scenario:Update user with new details and verify if user is updated
    When I update the user with information name "<name>" email "<email>" gender "<gender>" status "<status>"
    Then I verify that user is updated with userId and must get status code 200

    Scenario: User should be deleted
      When user is deleted
      Then I verify user is deleted with with status code 404