Feature: Find fell by id

  As a dev
  I want to retrieve all data corresponding to a particular fell
  So that I can use that data in my own application

  Background:
    Given an endpoint /fells/{id}

  Scenario: Successfully requesting a fell by id
    Given a fell id of 5
    When requesting a fell with that id
    Then the response will return a 200 status code
    And the content type will be application/json
    And the response body will conform to the schema in schemas/fell_schema.json

  Scenario: Unsuccessfully requesting a fell by id that doesn't exist
    Given a fell id of 999
    When requesting a fell with that id
    Then the response will return a 404 status code
    And the content type will be application/json
    And the response body will conform to the schema in schemas/error_schema.json

  Scenario: Attempting to use an unsupported http method with this endpoint
    When using one of the following unsupported http methods with endpoint
      | POST   |
      | PUT    |
      | PATCH  |
      | DELETE |
    Then the response will return a 405 status code
    And the content type will be application/json
    And the response body will conform to the schema in schemas/error_schema.json

