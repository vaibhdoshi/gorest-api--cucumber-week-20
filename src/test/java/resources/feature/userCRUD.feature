Feature: Gorest com
  As a user I want to test Gorest Application

  Scenario Outline: CRUD TEST
    Given Gorest Application is running
    When I create a new user by providing the information name "<name>" email "<email>" gender "<gender>" status "<status>"
    Then I verify that the user with ID is created
    And I update the user with information name "<name>" email "<email>" gender "<gender>" status "<status>"
    And I verify that user with ID is updated successfully
    And I delete the user
    Then I verify user is deleted successfully from the application
    Examples:
      | name   | email             | gender | status |
      | Vianna | Vianna1@gmail.com | Female | active |