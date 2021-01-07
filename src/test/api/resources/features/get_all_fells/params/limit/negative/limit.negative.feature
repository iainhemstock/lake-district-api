Feature: /fells?limit=[invalid] - NEGATIVE TESTS WITH INVALID INPUT

	Background:
		Given Great Gable, Helvellyn and Scafell Pike exist
		And the default offset of items returned is 0

	Scenario Outline: /fells?limit=[invalid] - API returns BAD_REQUEST status code when page size is invalid
		When making a GET request to /fells?limit=<invalid_limit>
		Then the status code will be 400
		Examples:
			| invalid_limit |
			| 0             |
			| -1            |

	Scenario Outline: /fells?limit=[invalid] - API provides json body validated against schema when page size is invalid
		When making a GET request to /fells?limit=<invalid_limit>
		Then the body will conform to the schema in schemas/error_schema.json
		Examples:
			| invalid_limit |
			| 0             |
			| -1            |

	Scenario Outline: /fells?limit=[invalid] - API provides error response when requested page size is invalid
		When making a GET request to /fells?limit=<invalid_limit>
		Then the body will contain the status code 400 BAD_REQUEST
		And the body will contain the message Limit cannot be negative or zero
		And the body will contain the timestamp 2018-12-10 13:45:00
		Examples:
			| invalid_limit |
			| 0             |
			| -1            |