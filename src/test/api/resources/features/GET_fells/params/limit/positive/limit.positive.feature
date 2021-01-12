Feature: GET /fells?limit=[valid] - EXTENDED POSITIVE TESTS WITH OPTIONAL PARAMETERS

	Background:
		Given Great Gable, Helvellyn and Scafell Pike exist
		And the default page offset is 0

	Scenario: /fells?limit=1 - API provides navigational links to current and next pages when page limit is 1
		When making a GET request to /fells?limit=1
		Then a pref href will not exist
		And the self href will contain a limit limit=1
		And the next href will contain a limit limit=1

	Scenario: /fells?limit=2 - API returns navigational links to current and next pages when page limit is 2
		When making a GET request to /fells?limit=2
		Then a pref href will not exist
		And the self href will contain a limit limit=2
		And the next href will contain a limit limit=2

	Scenario: /fells?limit=3 - API returns navigational links to current page when page limit is 3
		When making a GET request to /fells?limit=3
		Then a pref href will not exist
		And the self href will contain a limit limit=3
		And a next href will not exist

	Scenario: /fells?limit=99999 - API returns navigation links to the current page when page limit is greater than the total number of items
		When making a GET request to /fells?limit=99999
		Then a pref href will not exist
		And the self href will contain a limit limit=99999
		And a next href will not exist

	Scenario Outline: /fells?limit=# - API provides pagination metadata for this collection of resources
		When making a GET request to /fells?limit=<limit>
		Then the body will contain the offset <offset>
		And the limit <limit>
		And the total items <total items>
		Examples:
			| limit | offset | total items |
			| 1     | 0      | 3           |
			| 2     | 0      | 3           |
			| 3     | 0      | 3           |
			| 99999 | 0      | 3           |

	Scenario: ?fells?limit=1 - API returns a page containing a single fell when limit=1
		When making a GET request to /fells?limit=1
		Then the body will contain the following fells
			| name         | href            |
			| Scafell Pike | /fells/NY215072 |
		And the number of fells contained in the page is 1

	Scenario: ?fells?limit=2 - API returns a page containing two fells when limit=2
		When making a GET request to /fells?limit=2
		Then the body will contain the following fells
			| name         | href            |
			| Scafell Pike | /fells/NY215072 |
			| Helvellyn    | /fells/NY342151 |
		And the number of fells contained in the page is 2

	Scenario: ?fells?limit=3 - API returns a page containing three fells when limit=3
		When making a GET request to /fells?limit=3
		Then the body will contain the following fells
			| name         | href            |
			| Scafell Pike | /fells/NY215072 |
			| Helvellyn    | /fells/NY342151 |
			| Great Gable  | /fells/NY211104 |
		And the number of fells contained in the page is 3

	Scenario: ?fells?limit=99999 - API returns a page containing three fells when limit=99999
		When making a GET request to /fells?limit=99999
		Then the body will contain the following fells
			| name         | href            |
			| Great Gable  | /fells/NY211104 |
			| Helvellyn    | /fells/NY342151 |
			| Scafell Pike | /fells/NY215072 |
		And the number of fells contained in the page is 3