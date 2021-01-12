Feature: GET /fells?sort=[invalid] - NEGATIVE TESTING WITH INVALID PARAMETERS

	Background:
		Given Great Gable, Helvellyn and Scafell Pike exist
		And the default page offset is 0
		And the default page size is 1

	Scenario: ?sort=name.descending - API returns error status when sort direction is unrecognized
		When making a GET request to /fells?sort=height.descending
		Then the status code will be 400

	Scenario: ?sort=name.descending - API provides json body validated against schema when sort direction is unrecognized
		When making a GET request to /fells?sort=height.descending
		Then the body will conform to the schema in schemas/error.schema.json

	Scenario: ?sort=prominence.desc - API returns error response when sort field is not supported
		When making a GET request to /fells?sort=prominence.desc
		Then the body will contain the status code 400 BAD_REQUEST
		And the body will contain the message Invalid sort request {sort=prominence.desc}
		And the body will contain the timestamp 2018-12-10 13:45:00

	Scenario: ?sort=name.descending - API returns error response when sort direction is unrecognized
		When making a GET request to /fells?sort=height.descending
		Then the body will contain the status code 400 BAD_REQUEST
		And the body will contain the message Invalid sort request {sort=height.descending}
		And the body will contain the timestamp 2018-12-10 13:45:00