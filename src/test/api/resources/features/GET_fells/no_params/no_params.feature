Feature: GET /fells - BASIC POSITIVE TESTS

	Background:
		Given Great Gable, Helvellyn and Scafell Pike exist
		And the default page offset is 0
		And the default page size is 1
		And the default page sort is height.desc

	Scenario: API provides OK status code on successful request
		When making a GET request to /fells
		Then the status code will be 200

	Scenario: API provides json body validated against schema
		When making a GET request to /fells
		Then the response will conform to the schema in schemas/fells.schema.json

	Scenario Outline: API provides contextual navigational links depending on the current page
		When making a GET request to /fells
		Then the body will contain the following <prev>, <current> and <next> links
		Examples:
			| prev | current                                                              | next                                                                 |
			|      | http://localhost:8080/api/v1/fells?offset=0&limit=1&sort=height.desc | http://localhost:8080/api/v1/fells?offset=1&limit=1&sort=height.desc |

	Scenario: API provides pagination metadata for this collection of resources
		When making a GET request to /fells
		Then the body will contain the offset 0
		And the limit 1
		And the total items 3

	Scenario: API provides a collection of fells
		When making a GET request to /fells
		Then the body will contain the following fells in order
			| name         | href            | height meters | height feet |
			| Scafell Pike | /fells/NY215072 | 978           | 3209        |
		And the number of fells contained in the page is 1

	Scenario: API provides content type header
		When making a GET request to /fells
		Then the content type will be application/json

	Scenario: API confirms that this endpoint only accepts idempotent requests
		When making an OPTIONS request to /fells
		Then the headers will confirm only GET, HEAD and OPTIONS methods are allowed