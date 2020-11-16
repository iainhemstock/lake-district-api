Feature: Find fell by id

  As a dev
  I want to retrieve all data corresponding to a particular fell
  So that I can use that data in my own application

  Background:
    Given an endpoint http://localhost:8080/api/fells/{id}

  Scenario: Successfully requesting a fell by id
    Given a fell id of 5
    When requesting a fell with that id
    Then the response will return a 200 status code
    And the content type will be application/json
    And the response body will conform to the schema in schemas/fell_schema.json
    And the response body will contain the following values
      | name              | Great Gable                         |
      | height_meters     | 899                                 |
      | height_feet       | 2949                                |
      | prominence_meters | 425                                 |
      | prominence_feet   | 1394                                |
      | region            | http://localhost:8080/api/regions/3 |
      | latitude          | 54.482                              |
      | longitude         | -3.219                              |
      | os_map_ref        | NY211104                            |
      | url               | http://localhost:8080/api/fells/5   |
      | parent_peak       | http://localhost:8080/api/fells/1   |
    And the response body will also contain the following converted longitude dms coordinates
      | degrees     | 3           |
      | minutes     | 13          |
      | seconds     | 8           |
      | hemisphere  | W           |
      | formatted   | 3° 13' 8" W |
    And the response body will also contain the following converted latitude dms coordinates
      | degrees     | 54            |
      | minutes     | 28            |
      | seconds     | 55            |
      | hemisphere  | N             |
      | formatted   | 54° 28' 55" N |
    And the response body will also contain the following classification urls
      | http://localhost:8080/api/classifications/11 |
      | http://localhost:8080/api/classifications/3  |
      | http://localhost:8080/api/classifications/15 |
      | http://localhost:8080/api/classifications/2  |
      | http://localhost:8080/api/classifications/12 |
      | http://localhost:8080/api/classifications/4  |
      | http://localhost:8080/api/classifications/13 |
      | http://localhost:8080/api/classifications/14 |
      | http://localhost:8080/api/classifications/16 |
      | http://localhost:8080/api/classifications/1  |
    And the response body will also contain the following map urls
      | http://localhost:8080/api/maps/1  |
      | http://localhost:8080/api/maps/2  |
      | http://localhost:8080/api/maps/7  |

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

