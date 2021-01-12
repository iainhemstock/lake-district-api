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

	Scenario: ?sort=height.asc - API provides a collection of fells sorted by height.asc
		When making a GET request to /fells?sort=height.asc
		Then the body will contain the following fells
			| name        | href            |
			| Great Gable | /fells/NY211104 |

	Scenario: ?sort=name.asc - API provides a collection of fells sorted by name.asc
		When making a GET request to /fells?sort=name.asc
		Then the body will contain the following fells
			| name         | href            |
			| Great Gable  | /fells/NY211104 |

	