Feature: GET /fells?page={p}&size={s} - EXTENDED POSITIVE TESTS WITH OPTIONAL PARAMETERS

	Background:
		Given Great Gable, Helvellyn and Scafell Pike exist

	#VerifyStatusCode
	Scenario Outline: API returns OK status code for specific page and size requests
		When making a GET request to http://localhost:8080/api/v1/<endpoint>
		Then the status code will be 200
			Examples:
				| endpoint 			  |
				| fells?page=0&size=1 |
				| fells?page=1&size=1 |
				| fells?page=2&size=1 |

	#VerifyResponsePayload
	Scenario Outline: API provides json body validated against schema
		When making a GET request to http://localhost:8080/api/v1/<endpoint>
		Then the body will conform to the schema in schemas/collection_resource_schema.json
		Examples:
			| endpoint 			  |
			| fells?page=0&size=1 |
			| fells?page=1&size=1 |
			| fells?page=2&size=1 |

	#VerifyResponsePayload
	Scenario Outline: API provides contextual navigational links depending on the current page
		When making a GET request to http://localhost:8080/api/v1/<endpoint>
		Then the body may contain the following <first>, <prev>, <current>, <next> and <last> links
		Examples:
			| endpoint 		      | first | prev  | current | next  | last  |
			| fells?page=0&size=1 | false | false | true    | true  | true  |
			| fells?page=1&size=1 | true  | true  | true    | true  | true  |
			| fells?page=2&size=1 | true  | true  | true    | false | false |

	#VerifyResponsePayload
	Scenario Outline: API provides pagination metadata for this collection of resources
		When making a GET request to http://localhost:8080/api/v1/<endpoint>
		Then the body will contain the <current page number>, <total pages> and <total results>
		Examples:
			| endpoint 			  | current page number | total pages  | total results |
			| fells?page=0&size=1 | 1 					| 3            | 3             |
			| fells?page=1&size=1 | 2                   | 3            | 3             |
			| fells?page=2&size=1 | 3                   | 3            | 3             |

	#VerifyResponsePayload
	Scenario Outline: API provides a simplified view of fell results
		When making a GET request to http://localhost:8080/api/v1/<endpoint>
		Then the body will contain a simplified fell with name <fell name>
		And region <fell region>
		And url <url>
		Examples:
			| endpoint 			  | fell name    | fell region            | url 										|
			| fells?page=0&size=1 | Great Gable  | Central Lake District  | http://localhost:8080/api/v1/fells/NY211104 |
			| fells?page=1&size=1 | Helvellyn    | Eastern Lake District  | http://localhost:8080/api/v1/fells/NY342151 |
			| fells?page=2&size=1 | Scafell Pike | Southern Lake District | http://localhost:8080/api/v1/fells/NY215072 |

	#VerifyResponseHeaders
	Scenario Outline: API provides content type header for specific page and size requests
		When making a GET request to http://localhost:8080/api/v1/<endpoint>
		Then the content type will be application/json
		Examples:
			| endpoint 			  |
			| fells?page=0&size=1 |
			| fells?page=1&size=1 |
			| fells?page=2&size=1 |

	#VerifyApplicationState
	Scenario Outline: API confirms that this endpoint only accepts idempotent requests
		When making an OPTIONS request to http://localhost:8080/api/v1/<endpoint>
		Then the headers will confirm only GET, HEAD AND OPTIONS methods are allowed
		Examples:
			| endpoint 			  |
			| fells?page=0&size=1 |
			| fells?page=1&size=1 |
			| fells?page=2&size=1 |