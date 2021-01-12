Feature: GET /fells?sort=[invalid] - NEGATIVE TESTING WITH INVALID PARAMETERS

	Background:
		Given Great Gable, Helvellyn and Scafell Pike exist
		And the default page offset is 0
		And the default page size is 3

	Scenario: ?sort=name.ascending - API returns error status when sort direction is unrecognized
		When making a GET request to /fells?sort=height.descending
		Then the status code will be 400

	Scenario: /sort=name.ascending - API provides json body validated against schema when sort direction is unrecognized
		When making a GET request to /fells?sort=height.descending
		Then the body will conform to the schema in schemas/error.schema.json
