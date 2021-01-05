Feature: GET /fells?offset=2 - EXTENDED POSITIVE TESTS WITH OPTIONAL PARAMETERS

	Background:
		Given Great Gable, Helvellyn and Scafell Pike exist
		And the default limit of items returned is 1

	#===================================================================================================================
	# [1] verify correct HTTP status code
	#===================================================================================================================
	Scenario: API returns OK status code when valid offset is specified
		When making a GET request to http://localhost:8080/api/v1/fells?offset=2
		Then the status code will be 200

	#===================================================================================================================
	# [2] Verify response payload
	#===================================================================================================================
	Scenario: API provides json body validated against schema
		When making a GET request to http://localhost:8080/api/v1/fells?offset=2
		Then the response will conform to the schema in schemas/linked_repo_page_schema.json
		Then the pagination attributes will conform to the schema in schemas/links_schema.json
		And the items attribute will conform to the schema in schemas/simple_fell_schema.json

	Scenario Outline: API provides contextual navigational links depending on the current page
		When making a GET request to http://localhost:8080/api/v1/fells?offset=2
		Then the prev href will contain an offset <prev offset>
		And the self href will contain an offset <self offset>
		Examples:
			| prev offset | self offset |
			| offset=1    | offset=2   |

	Scenario: API provides a simplified projection of a fell
		When making a GET request to http://localhost:8080/api/v1/fells?offset=2
		Then the body will contain a fell name Scafell Pike
		And fell self href http://localhost:8080/api/v1/fells/NY215072

	Scenario: API provides pagination metadata for this collection of resources
		When making a GET request to http://localhost:8080/api/v1/fells?offset=2
		Then the body will contain the offset 2
		And the limit 1
		And the total items 3

	#===================================================================================================================
	# [3] Verify response headers
	#===================================================================================================================
	Scenario: API provides content type header
		When making a GET request to http://localhost:8080/api/v1/fells?offset=2
		Then the content type will be application/json

	#===================================================================================================================
	# [4] Verify correct application state
	#===================================================================================================================
	Scenario: API confirms that this endpoint only accepts idempotent requests
		When making an OPTIONS request to http://localhost:8080/api/v1/fells?offset=2
		Then the headers will confirm only GET, HEAD AND OPTIONS methods are allowed