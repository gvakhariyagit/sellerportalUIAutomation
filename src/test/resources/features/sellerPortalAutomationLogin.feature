Feature: Seller portal login Functionality


Scenario: User logging in with correct credentials
	Given landing to seller portal login page
	When user logs-in with credentials -> username : seller@yopmail.com, password: Seller@1
	Then user logged-in successfully with welcome title : "Welcome Jitender Singh"

Scenario: User logging in with incorrect credentials
	Given landing to seller portal login page
	When user logs-in with credentials -> username : seller123@yopmail.com, password: Seller
	Then login was unsuccessful with error message : "Invalid Credential"
