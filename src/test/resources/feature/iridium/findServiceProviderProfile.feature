Feature: Find a service provider  
	
	Scenario Outline: Test FindServiceProviderProfile web method
		Given a request to FindServiceProviderProfile web method with the following parameters "<signature>","<timeStamp>","<accountNumber>" and "<userName>
		Then we can validate that the result contains "<accountName>" 
		
	 Examples:
 | signature | timeStamp | accountNumber | userName | accountName | 
 | ENrZ+57nMtUujucSREUJD/V3T7w= | 2016-01-22T17:57:33Z | 200001 | IWSTESTSP0001 | Iridium Satellite Test SP | 




