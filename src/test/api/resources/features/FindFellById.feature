Feature: Find fell by id

  As a dev
  I want to retrieve all data corresponding to a particular fell
  So that I can use that data in my own application

  Scenario: Successfully requesting a fell by id
    Given an endpoint http://localhost:8080/api/v1/fells/{id}
    When requesting a fell with id 5
    Then the response status code will be 200
    And the response content type will be application/json
    And the response headers will confirm only GET requests are allowed on this endpoint
    And the response body will conform to the schema in schemas/fell_schema.json
    And the response body will contain the fell name Great Gable
    And the response body will contain the region Central Lake District
    And the response body will contain the latitude 54.482
    And the response body will contain the longitude -3.219
    And the response body will contain the os map reference NY211104
    And the response body will contain the url to itself http://localhost:8080/api/v1/fells/5
    And the response body will contain the parent peak url http://localhost:8080/api/v1/fells/1
    And the response body will contain the height in feet 2949
    And the response body will contain the height in meters 899
    And the response body will contain the prominence in feet 1394
    And the response body will contain the prominence in meters 425
    And the response body will contain the following dms coordinates equivalent to the latitude
      | degrees     | 54            |
      | minutes     | 28            |
      | seconds     | 55            |
      | hemisphere  | N             |
    And the response body will contain the following dms coordinates equivalent to the longitude
      | degrees     | 3           |
      | minutes     | 13          |
      | seconds     | 8           |
      | hemisphere  | W           |
    And the response body will contain the following classifications
      | Birkett    |
      | Marilyn    |
      | Fellranger |
    And the response body will contain the following maps that this fell appears in
      | OS Landranger 89 |
      | OS Landranger 90 |
      | OS Explorer OL6  |

  Scenario: Unsuccessfully requesting a fell by id because the fell doesn't exist
    Given an endpoint http://localhost:8080/api/v1/fells/{id}
    When requesting a fell with id 999
    Then the response status code will be 404
    And the response content type will be application/json
    And the response body will conform to the schema in schemas/error_schema.json
    And the response headers will confirm only GET requests are allowed on this endpoint
    And the response body will contain the status code 404
    And the response body will contain the message Fell was not found for {id=999}
    And the response body will contain the path http://localhost:8080/api/v1/fells/999
    And the response body will contain the timestamp 2018-12-10 13:45:00

  Scenario Outline: Sending an unsupported http method request to this endpoint
    Given an endpoint http://localhost:8080/api/v1/fells/{id}
    When sending unsupported <http method> request with fell id 0 to endpoint
    Then the response status code will be 405
    And the response headers will confirm only GET requests are allowed on this endpoint
    And the response content type will be application/json
    And the response body will conform to the schema in schemas/error_schema.json
    And the response body will contain the status code 405
    And the response body will contain the message <message>
    And the response body will contain the path http://localhost:8080/api/v1/fells/0
    And the response body will contain the timestamp 2018-12-10 13:45:00
    Examples:
      | http method | message                          |
      | POST        | Method POST is not supported     |
      | DELETE      | Method DELETE is not supported   |
      | PUT         | Method PUT is not supported      |
      | PATCH       | Method PATCH is not supported    |


