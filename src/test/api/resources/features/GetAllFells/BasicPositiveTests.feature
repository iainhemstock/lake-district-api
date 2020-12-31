Feature: GET /fells - BASIC POSITIVE TESTS

	Background:
		Given Great Gable, Helvellyn and Scafell Pike exist
		And the offset of items returned in the response is 0 by default
		And the limit of the number of items returned in the response is 1 by default

	Scenario: API provides OK status code on successful request
		When making a GET request to http://localhost:8080/api/v1/fells
		Then the status code will be 200

	Scenario: API provides json body validated against schema
		When making a GET request to http://localhost:8080/api/v1/fells
		Then the response will conform to the schema in schemas/paged_collection_schema.json
		Then the pagination attributes will conform to the schema in schemas/links_schema.json
		And the items attribute will conform to the schema in schemas/simple_fell_schema.json

	Scenario Outline: API provides contextual navigational links depending on the current page
		When making a GET request to http://localhost:8080/api/v1/fells
		Then the body will contain the following <prev>, <current> and <next> links
		Examples:
			| prev | current                                             | next                                                 |
			|      | http://localhost:8080/api/v1/fells?offset=0&limit=1 | http://localhost:8080/api/v1/fells?offset=1&limit=1  |

	Scenario Outline: API provides pagination metadata for this collection of resources
		When making a GET request to http://localhost:8080/api/v1/fells
		Then the body will contain the offset <offset>
		And the limit <limit>
		And the total items <total items>
		Examples:
			| offset | limit  | total items |
			| 0      | 1      | 3           |

	Scenario: API provides a simplified view of a fell
		When making a GET request to http://localhost:8080/api/v1/fells
		Then the body will contain a simplified fell with name Great Gable
		And self href http://localhost:8080/api/v1/fells/NY211104

	Scenario: API provides content type header
		When making a GET request to http://localhost:8080/api/v1/fells
		Then the content type will be application/json

	Scenario: API confirms that this endpoint only accepts idempotent requests
		When making an OPTIONS request to http://localhost:8080/api/v1/fells
		Then the headers will confirm only GET, HEAD AND OPTIONS methods are allowed