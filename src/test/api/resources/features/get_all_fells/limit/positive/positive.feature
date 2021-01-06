Feature: GET /fells?limit=# - EXTENDED POSITIVE TESTS WITH OPTIONAL PARAMETERS

	Background:
		Given Great Gable, Helvellyn and Scafell Pike exist
		And the default offset of items returned is 0

	Scenario: ?limit=1 - API provides navigational links to current and next page
		When making a GET request to /fells?limit=1
		Then a pref href will not exist
		And the self href will contain a limit limit=1
		And the next href will contain a limit limit=1