Feature: GET /fells?sort=[valid] - EXTENDED POSITIVE TESTS WITH OPTIONAL PARAMETERS

	Background:
		Given Great Gable, Helvellyn and Scafell Pike exist
		And the default page offset is 0
		And the default page size is 3

	Scenario: ?sort=height.desc - API provides a collection of fells sorted by height.desc
		When making a GET request to /fells?sort=height.desc
		Then the body will contain the following fells in order
			| name         | href            |
			| Scafell Pike | /fells/NY215072 |
			| Helvellyn    | /fells/NY342151 |
			| Great Gable  | /fells/NY211104 |
		And the number of fells contained in the page is 3

	Scenario: ?sort=height.asc - API provides a collection of fells sorted by height.asc
		When making a GET request to /fells?sort=height.asc
		Then the body will contain the following fells in order
			| name         | href            |
			| Great Gable  | /fells/NY211104 |
			| Helvellyn    | /fells/NY342151 |
			| Scafell Pike | /fells/NY215072 |
		And the number of fells contained in the page is 3

	Scenario: ?sort=name.asc - API provides a collection of fells sorted by name.asc
		When making a GET request to /fells?sort=name.asc
		Then the body will contain the following fells in order
			| name         | href            |
			| Great Gable  | /fells/NY211104 |
			| Helvellyn    | /fells/NY342151 |
			| Scafell Pike | /fells/NY215072 |
		And the number of fells contained in the page is 3

	Scenario: ?sort=name.desc - API provides a collection of fells sorted by name.desc
		When making a GET request to /fells?sort=name.desc
		Then the body will contain the following fells in order
			| name         | href            |
			| Scafell Pike | /fells/NY215072 |
			| Helvellyn    | /fells/NY342151 |
			| Great Gable  | /fells/NY211104 |
		And the number of fells contained in the page is 3