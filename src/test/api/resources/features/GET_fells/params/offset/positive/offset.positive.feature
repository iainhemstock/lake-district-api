Feature: GET /fells?offset=[valid] - EXTENDED POSITIVE TESTS WITH OPTIONAL PARAMETERS

	Background:
		Given Great Gable, Helvellyn and Scafell Pike exist
		And the default page size is 1
		And the default page sort is height.desc

	Scenario: ?offset=0 - API provides navigational links to current and next page
		When making a GET request to /fells?offset=0
		Then a pref href will not exist
		And the self href will contain an offset offset=0
		And the next href will contain an offset offset=1

	Scenario: ?offset=1 - API provides navigational links to prev, current and next page
		When making a GET request to /fells?offset=1
		Then the prev href will contain an offset offset=0
		And the self href will contain an offset offset=1
		And the next href will contain an offset offset=2

	Scenario: ?offset=2 - API provides navigational links prev and current page
		When making a GET request to /fells?offset=2
		Then the prev href will contain an offset offset=1
		And the self href will contain an offset offset=2
		And a next href will not exist

	Scenario: ?offset=0 - API provides a collection of fells offset by specified amount of 0
		When making a GET request to /fells?offset=0
		Then the body will contain the following fells in order
			| name         | href            | height meters | height feet |
			| Scafell Pike | /fells/NY215072 | 978           | 3209        |

	Scenario: ?offset=1 - API provides a collection of fells offset by specified amount of 1
		When making a GET request to /fells?offset=1
		Then the body will contain the following fells in order
			| name         | href            | height meters | height feet |
			| Helvellyn    | /fells/NY342151 | 950           | 3117        |

	Scenario: ?offset=2 - API provides a collection of fells offset by specified amount of 2
		When making a GET request to /fells?offset=2
		Then the body will contain the following fells in order
			| name         | href            | height meters | height feet |
			| Great Gable  | /fells/NY211104 | 899           | 2949        |
		And the number of fells contained in the page is 1

	Scenario Outline: ?offset=# - API provides pagination metadata for this collection of resources
		When making a GET request to /fells?offset=<offset>
		Then the body will contain the offset <offset>
		And the limit <limit>
		And the total items <total items>
		Examples:
			| offset | limit | total items |
			| 0	     | 1     | 3           |
			| 1	     | 1     | 3           |
			| 2	     | 1     | 3           |
