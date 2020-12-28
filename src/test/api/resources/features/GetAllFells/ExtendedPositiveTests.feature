Feature: GET /fells?page={}&size={} - EXTENDED POSITIVE TESTS WITH OPTIONAL PARAMETERS

	Background:
		Given Great Gable, Helvellyn and Scafell Pike exist

	#VerifyStatusCode
	Scenario Outline: API returns OK status code for specific page and size requests
		When making a GET request to http://localhost:8080/api/v1/fells?offset=<offset>&limit=<limit>
		Then the status code will be 200
			Examples:
				| offset | limit |
				| 0 	 | 1     |
				| 1      | 1     |
				| 2      | 1     |

	#VerifyResponsePayload
	Scenario Outline: API provides json body validated against schema
		When making a GET request to http://localhost:8080/api/v1/fells?offset=<offset>&limit=<limit>
		Then the body will conform to the schema in schemas/paged_collection_schema.json
		Examples:
			| offset | limit |
			| 0 	 | 1     |
			| 1      | 1     |
			| 2      | 1     |

	#VerifyResponsePayload
	Scenario Outline: API provides contextual navigational links depending on the current page
		When making a GET request to http://localhost:8080/api/v1/fells?offset=<offset>&limit=<limit>
		Then the body will contain the following <prev>, <current> and <next> links
		Examples:
			| offset | limit | prev                                                | current                                             | next                                                 |
			| 0      | 1     |                                                     | http://localhost:8080/api/v1/fells?offset=0&limit=1 | http://localhost:8080/api/v1/fells?offset=1&limit=1  |
			| 1      | 1     | http://localhost:8080/api/v1/fells?offset=0&limit=1 | http://localhost:8080/api/v1/fells?offset=1&limit=1 | http://localhost:8080/api/v1/fells?offset=2&limit=1  |
			| 2      | 1     | http://localhost:8080/api/v1/fells?offset=1&limit=1 | http://localhost:8080/api/v1/fells?offset=2&limit=1 |                                                      |

	#VerifyResponsePayload
	Scenario Outline: API provides pagination metadata for this collection of resources
		When making a GET request to http://localhost:8080/api/v1/fells?offset=<offset>&limit=<limit>
		Then the body will contain the offset <offset>
		And the limit <limit>
		And the total items <total items>
		Examples:
			| offset | limit | total items |
			| 0      | 1     | 3           |
			| 1      | 1     | 3           |
			| 2      | 1     | 3           |

	#VerifyResponsePayload
	Scenario Outline: API provides a simplified view of fell results
		When making a GET request to http://localhost:8080/api/v1/fells?offset=<offset>&limit=<limit>
		Then the body will contain a simplified fell with name <fell name>
		And region <fell region>
		And self href <url>
		Examples:
			| offset | limit | fell name    | fell region            | url 										   |
			| 0      | 1     | Great Gable  | Central Lake District  | http://localhost:8080/api/v1/fells/NY211104 |
			| 1      | 1     | Helvellyn    | Eastern Lake District  | http://localhost:8080/api/v1/fells/NY342151 |
			| 2      | 1     | Scafell Pike | Southern Lake District | http://localhost:8080/api/v1/fells/NY215072 |

	#VerifyResponseHeaders
	Scenario Outline: API provides content type header for specific page and size requests
		When making a GET request to http://localhost:8080/api/v1/fells?offset=<offset>&limit=<limit>
		Then the content type will be application/json
		Examples:
			| offset | limit |
			| 0      | 1     |
			| 1      | 1     |
			| 2      | 1     |

	#VerifyApplicationState
	Scenario Outline: API confirms that this endpoint only accepts idempotent requests
		When making an OPTIONS request to http://localhost:8080/api/v1/fells?offset=<offset>&limit=<limit>
		Then the headers will confirm only GET, HEAD AND OPTIONS methods are allowed
		Examples:
			| offset | limit |
			| 0      | 1     |
			| 1      | 1     |
			| 2      | 1     |