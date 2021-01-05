Feature: GET /fells?offset={invalid} - NEGATIVE TESTING WITH INVALID PARAMETERS

	Background:
		Given Great Gable, Helvellyn and Scafell Pike exist
		And the default limit of items returned is 1

	#===================================================================================================================
	# [1] verify correct HTTP status code
	#===================================================================================================================
	Scenario: API returns BAD REQUEST status code when invalid offset is specified
		When making a GET request to /fells?offset=-1
		Then the status code will be 400

	#===================================================================================================================
	# [2] Verify response payload
	#===================================================================================================================
	Scenario: API returns error response when invalid offset is specified
		When making a GET request to /fells?offset=-1
		Then the body will contain the status code 400 BAD_REQUEST
		And the body will contain the message Offset cannot be negative
		And the body will contain the timestamp 2018-12-10 13:45:00

	#===================================================================================================================
	# [3] Verify response headers
	#===================================================================================================================
	Scenario: API provides content type header
		When making a GET request to /fells?offset=-1
		Then the content type will be application/json

	#===================================================================================================================
	# [4] Verify correct application state
	#===================================================================================================================
	Scenario: API confirms that this endpoint only accepts idempotent requests
		When making an OPTIONS request to /fells?offset=-1
		Then the headers will confirm only GET, HEAD and OPTIONS methods are allowed
