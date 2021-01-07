Feature: GET /fells/[id] - BASIC POSITIVE TESTS
	Background:
		Given Great Gable exists

	#VerifyStatusCode
	Scenario: API provides OK status code on successful request
		When making a GET request to /fells/NY211104
		Then the status code will be 200

	#VerifyResponsePayload
	Scenario: API provides json body validated against schema
		When making a GET request to /fells/NY211104
		Then the body will conform to the schema in schemas/item_schema.json
		And the links attribute will conform to the schema in schemas/links_schema.json
		And the item attribute will conform to the schema in schemas/fell_schema.json

	#VerifyResponsePayload
	Scenario: API provides a detailed view of a fell
		When making a GET request to /fells/NY211104
		Then the body will contain the fell name Great Gable
		And the body will contain the region Central Lake District
		And the body will contain the latitude 54.482
		And the body will contain the longitude -3.219
		And the body will contain the os map reference NY211104
		And the body will contain a self href http://localhost:8080/api/v1/fells/NY211104
		And the body will contain the parent peak href http://localhost:8080/api/v1/fells/NY215072
		And the body will contain the height in feet 2949
		And the body will contain the height in meters 899
		And the body will contain the prominence in feet 1394
		And the body will contain the prominence in meters 425
		And the body will contain the following dms coordinates equivalent to the latitude
			| degrees     | 54            |
			| minutes     | 28            |
			| seconds     | 55            |
			| hemisphere  | N             |
		And the body will contain the following dms coordinates equivalent to the longitude
			| degrees     | 3           |
			| minutes     | 13          |
			| seconds     | 8           |
			| hemisphere  | W           |
		And the body will contain the following classifications
			| Birkett    |
			| Marilyn    |
			| Fellranger |
		And the body will contain the following maps that this fell appears in
			| OS Landranger 89 |
			| OS Landranger 90 |
			| OS Explorer OL6  |

	#VerifyResponsePayload
	Scenario: API does not specify a parent href when a fell has no parent
		Given Scafell Pike exists
		When making a GET request to /fells/NY215072
		Then the body will not contain the parent peak href

	#VerifyResponseHeaders
	Scenario: API provides content type header
		When making a GET request to /fells/NY211104
		Then the content type will be application/json

	#VerifyApplicationState
	Scenario: API confirms that this endpoint only accepts idempotent requests
		When making an OPTIONS request to /fells/NY211104
		Then the headers will confirm only GET, HEAD and OPTIONS methods are allowed

	#VerifyApplicationState
	Scenario Outline: API provides an error response when making an unsupported http method request
		When making an <unsupported http method> request to /fells/NY211104
		Then the status code will be 405
		And the content type will be application/json
		And the body will conform to the schema in schemas/error_schema.json
		And the body will contain the status code 405 METHOD_NOT_ALLOWED
		And the body will contain the message <message>
		And the body will contain the timestamp 2018-12-10 13:45:00
		Examples:
			| unsupported http method | message                          |
			| POST                    | Method POST is not supported     |
			| DELETE                  | Method DELETE is not supported   |
			| PUT                     | Method PUT is not supported      |
			| PATCH                   | Method PATCH is not supported    |


