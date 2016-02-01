Feature: Fueling
  To allow a customer to find his favourite books quickly, the library must offer multiple ways to search for a book.

  Scenario Outline: Car magicly multiplies fuel when fueling using ratios stored in db
    Given The customer empties the the car's fuel levels
    When the customer fuels the following number of gallons <gallons> to his or her car
    Then the car is left with the expected <expected> gallons

    Examples: 
      | gallons | expected | 
      | 3       | 15       |
      | 5       | 25       |
      | 10      | 50       |
