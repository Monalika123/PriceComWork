Feature: iPhone XR (64GB) - Yellow price comparision 
	I want to comapre iPhone XR (64GB) - Yellow price in Amazon and Flipkart

@runTestAmazon
Scenario: iPhone XR (64GB) - Yellow price comparision 
	Given User opens Amazon page 
	When page is loaded, searches for iPhone model in amazon 
		|       Model               |
		|  iPhone 12 Pro Max        |
		#	And Gets the price of the selected iPhone 
		#	Then navigates to flipkart 
		#	When page is loaded, searches for iPhone XR 64GB Yellow in flipkart 
		#	And Gets the price of the selected iPhone 
		#	And find which website has lesser value 
		
@runTestFlipkart 
Scenario: iPhone XR (64GB) - Yellow price comparision 
	Given User opens Flipkart page 
		
    