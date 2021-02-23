Feature: Customer Testing

@TestAPI
Scenario: Boarding new customer
Given I have the data to create customer
|firstName |lastName |phone |addresses |id     |
|Alex      |Kezloy   |123   |Backer 2  |union 3|
And I use customer header
When I create post request to create customer
Then I get status code 201 from database
And response body should contain 
|firstName |
|lastName  |
|phone     |
|addresses |
|id        |
