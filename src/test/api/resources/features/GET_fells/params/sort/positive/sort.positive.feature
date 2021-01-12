Feature: GET /fells?sort=[valid] - EXTENDED POSITIVE TESTS WITH OPTIONAL PARAMETERS

	Background:
		Given Great Gable, Helvellyn and Scafell Pike exist
		And the default page offset is 0
		And the default page size is 1

	Scenario: ?sort=height.desc - API provides a collection of fells sorted by height.desc
		When making a GET request to /fells?sort=height.desc
		Then the body will contain the following fells
			| name         | href            |
			| Scafell Pike | /fells/NY215072 |