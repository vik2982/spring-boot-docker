Feature: Test CRUD methods in REST API

  Scenario: GET all football teams
    Given I Set GET service api endpoint
    When I Send a GET HTTP request
    Then I receive valid Response
