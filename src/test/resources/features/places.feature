Feature: Manage places
  In order to maintain the place system
  As an API user
  I want to create, retrieve and delete places

  Scenario: Create a place
    Given a place with name "Gran Teatro de Cáceres", city "Cáceres" and postal code 10002
    When the place is saved
    Then the place is persisted successfully

  Scenario: Delete a place
    Given an existing place with name "Museo del Prado", city "Madrid" and postal code 28014
    When the place is deleted
    Then the place no longer exists

  Scenario: Get non-existing place
    When I request a place with id 999
    Then the response status should be 404

  Scenario: Delete non-existing place
    When I delete a place with id 999
    Then the response status should be 404

  Scenario: Get all places
    Given an existing place with name "Parque del Príncipe", city "Cáceres" and postal code 10004
    When I request all places
    Then the response status should be 200

  Scenario: Create place with invalid body
    When I create a place with invalid payload
    Then the response status should be 500