Feature: Fueling
  To allow fueling of a car

  Scenario Outline: Car magically multiplies fuel when fueling 
    Given The customer empties the the car's fuel levels
    When the customer fuels the following number of gallons <gallons> to his or her car
    Then the car is left with the expected <expected> gallons

    Examples: 
      | gallons | expected | 
      | 3       | 15       |
      | 5       | 25       |
      | 10      | 50       |

      
#Scenario Outline: Car calculates correct trip after fueling when tank is empty
#    Given The customer empties the the car's fuel levels
#    When the customer owns a car of brand <carBrand> and make <carMake>
#   And the customer fuels the following number of gallons <gallons> to his or her car
#	And the gas type is <gastype>
#  Then the car is has the following <expectedMilage> expected miles to travel

	#autodatagen"

#   etc...