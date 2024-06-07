
@tag
Feature: Purchase order from ecommerce website
  I want to use this template for my feature file

	Background: 
	Given I landed on ecommerce page

  @tag2
  Scenario Outline: positive test of submitting the order
    Given Logged in with username <name> and password <password>
    When I add the product <productName> from cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on confirmationPage

    Examples: 
      | name     					 | password				| productName
      | doctorr8@yahoo.com | Armaxsovs1112	| ZARA COAT 3
      
