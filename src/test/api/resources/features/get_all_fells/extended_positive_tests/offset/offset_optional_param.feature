Feature: GET /fells?offset=# - EXTENDED POSITIVE TESTS WITH OPTIONAL PARAMETERS

	Background:
		Given Great Gable, Helvellyn and Scafell Pike exist
		And the default limit of items returned is 1

	#===================================================================================================================
	# [2] Verify response payload
	#===================================================================================================================
	Scenario: ?offset=0 - API provides navigational links to current and next page
		When making a GET request to http://localhost:8080/api/v1/fells?offset=0
		Then a pref href will not exist
		And the self href will contain an offset offset=0
		And the next href will contain an offset offset=1

	Scenario: ?offset=1 - API provides navigational links to prev, current and next page
		When making a GET request to http://localhost:8080/api/v1/fells?offset=1
		Then the prev href will contain an offset offset=0
		And the self href will contain an offset offset=1
		And the next href will contain an offset offset=2

	Scenario: ?offset=2 - API provides navigational links prev and current page
		When making a GET request to http://localhost:8080/api/v1/fells?offset=2
		Then the prev href will contain an offset offset=1
		And the self href will contain an offset offset=2
		And a next href will not exist

	Scenario Outline: ?offset=# - API provides a collection of fells offset by specified amount
		When making a GET request to http://localhost:8080/api/v1/fells?offset=<offset>
		Then the body will contain a fell name <fell name>
		And fell self href <self href>
		Examples:
			| offset | fell name    | self href        							 |
			| 0      | Great Gable  | http://localhost:8080/api/v1/fells/NY211104 |
			| 1      | Helvellyn    | http://localhost:8080/api/v1/fells/NY342151 |
			| 2      | Scafell Pike | http://localhost:8080/api/v1/fells/NY215072 |

	Scenario Outline: ?offset=# - API provides pagination metadata for this collection of resources
		When making a GET request to http://localhost:8080/api/v1/fells?offset=<offset>
		Then the body will contain the offset <offset>
		And the limit <limit>
		And the total items <total items>
		Examples:
			| offset | limit | total items |
			| 0	     | 1     | 3           |
			| 1	     | 1     | 3           |
			| 2	     | 1     | 3           |
