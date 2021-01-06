Feature: GET /fells?limit=# - EXTENDED POSITIVE TESTS WITH OPTIONAL PARAMETERS

	Background:
		Given Great Gable, Helvellyn and Scafell Pike exist
		And the default offset of items returned is 0

	Scenario: ?limit=1 - API provides navigational links to current and next pages when page limit is 1
		When making a GET request to /fells?limit=1
		Then a pref href will not exist
		And the self href will contain a limit limit=1
		And the next href will contain a limit limit=1

	Scenario: ?limit=2 - API returns navigational links to prev, current and next pages when page limit is 2
		When making a GET request to /fells?limit=2
		Then a pref href will not exist
		And the self href will contain a limit limit=2
		And the next href will contain a limit limit=2

	Scenario: ?limit=3 - API returns navigational links to prev and current pages when page limit is 3
		When making a GET request to /fells?limit=3
		Then a pref href will not exist
		And the self href will contain a limit limit=3
		And a next href will not exist