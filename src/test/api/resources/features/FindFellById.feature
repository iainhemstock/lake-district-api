Feature: Find fell by id

  As a dev
  I want to retrieve all data corresponding to a particular fell
  So that I can use that data in my own application

  Scenario: Successfully requesting a fell by id
    Given an endpoint http://localhost:8080/api/fells/{id}
    When requesting a fell with id 5
    Then the response status code will be 200
    And the response content type will be application/json
    And the response headers will confirm only GET requests are allowed on this endpoint
    And the response body will contain the fell name Great Gable
    And the response body will contain the region http://localhost:8080/api/regions/3
    And the response body will contain the latitude 54.482
    And the response body will contain the longitude -3.219
    And the response body will contain the os map reference NY211104
    And the response body will contain the url http://localhost:8080/api/fells/5
    And the response body will contain the parent peak url http://localhost:8080/api/fells/1
    And the response body will contain the height in feet 2949
    And the response body will contain the height in meters 899
    And the response body will contain the prominence in feet 1394
    And the response body will contain the prominence in meters 425
    And the response body will contain the following dms coordinates equivalent to the latitude
      | degrees     | 54            |
      | minutes     | 28            |
      | seconds     | 55            |
      | hemisphere  | N             |
      | formatted   | 54° 28' 55" N |
    And the response body will contain the following dms coordinates equivalent to the longitude
      | degrees     | 3           |
      | minutes     | 13          |
      | seconds     | 8           |
      | hemisphere  | W           |
      | formatted   | 3° 13' 8" W |
    And the response body will contain the following classification urls
      | http://localhost:8080/api/classifications/3  |
      | http://localhost:8080/api/classifications/11 |
      | http://localhost:8080/api/classifications/15 |
    And the response body will contain the following maps that this fell appears in
      | http://localhost:8080/api/maps/1  |
      | http://localhost:8080/api/maps/2  |
      | http://localhost:8080/api/maps/7  |
    And the response body will conform to the schema in schemas/fell_schema.json

  Scenario: Unsuccessfully requesting a fell by id because the fell doesn't exist
    Given an endpoint http://localhost:8080/api/fells/{id}
    When requesting a fell with id 999
    Then the response status code will be 404
    And the response content type will be application/json
    And the response headers will confirm only GET requests are allowed on this endpoint
    And the response body will contain the status code 404
    And the response body will contain the message Fell was not found for {id=999}
    And the response body will contain the path http://localhost:8080/api/fells/999
    And the response body will contain the timestamp 2018-12-10 13:45:00
    And the response body will conform to the schema in schemas/error_schema.json

  Scenario Outline: Sending an unsupported http method request to this endpoint
    Given an endpoint http://localhost:8080/api/fells/{id}
    When sending unsupported <http method> request with fell id 0 to endpoint
    Then the response status code will be 405
    And the response content type will be application/json
    And the response headers will confirm only GET requests are allowed on this endpoint
    And the response body will contain the status code 405
    And the response body will contain the message <message>
    And the response body will contain the path http://localhost:8080/api/fells/0
    And the response body will contain the timestamp 2018-12-10 13:45:00
    And the response body will conform to the schema in schemas/error_schema.json
    Examples:
      | http method | message                          |
      | POST        | Method POST is not supported     |
      | DELETE      | Method DELETE is not supported   |
      | PUT         | Method PUT is not supported      |
      | PATCH       | Method PATCH is not supported    |


