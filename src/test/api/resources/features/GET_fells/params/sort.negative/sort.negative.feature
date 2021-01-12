Feature: GET /fells?sort=[invalid] - NEGATIVE TESTING WITH INVALID PARAMETERS

	Background:
		Given Great Gable, Helvellyn and Scafell Pike exist
		And the default page offset is 0
		And the default page size is 1

	Scenario Outline: ?sort=[invalid] - API returns error status when sort param is invalid
		When making a GET request to /fells?sort=<invalid sort param>
		Then the status code will be 400
		Examples:
			| invalid sort param |
			| height.descending  |
			| prominence.desc    |
			| height.descending  |

	Scenario Outline: ?sort=[invalid] - API provides json body validated against schema when sort param is invalid
		When making a GET request to /fells?sort=<invalid sort param>
		Then the body will conform to the schema in schemas/error.schema.json
		Examples:
			| invalid sort param |
			| height.descending  |
			| prominence.desc    |
			| height.descending  |

	Scenario Outline: ?sort=[invalid] - API returns error response when sort param is invalid
		When making a GET request to /fells?sort=<invalid sort param>
		Then the body will contain the status code 400 BAD_REQUEST
		And the body will contain the message Invalid sort request {sort=<invalid sort param>}
		And the body will contain the timestamp 2018-12-10 13:45:00
		Examples:
			| invalid sort param |
			| height.descending  |
			| prominence.desc    |
			| height.descending  |
