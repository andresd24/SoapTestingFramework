package feature.fueling;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.containsString;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.en.And;
import java.lang.ProcessBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class FuelingSteps {
	
	private String m_carModel = "";
	private int m_gallons = 0;
	private double m_conversionRate = 0.0;
	
	@Given("The customer empties the the car's fuel levels")
	public void connectToCarService() {
		try {
	        ProcessBuilder pb = new ProcessBuilder("ext/curl", "--header", "Content-Type: text/xml;charset=UTF-8", "--data",
	        		"@soap_files/emptyFuel.xml", "http://localhost:8090/car");
	        Process p = pb.start(); 
	        p.waitFor(); 
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	@When("^the customer fuels the following number of gallons (\\d+) to his or her car$")
	public void fuelCar(final int gallons) throws IOException {
		try {
	        ProcessBuilder pb = new ProcessBuilder("ext/curl", "--header", "Content-Type: text/xml;charset=UTF-8", "--data",
	          		String.format("@soap_files/test_cases/addFuel_%1$d.xml", gallons), "http://localhost:8090/car");
	        Process p = pb.start(); 
	        p.waitFor(); 
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	@Then("^the car is left with the expected (\\d+) gallons$")
	public void the_car_is_left_with_the_expected_expected_gallons(final int expectedGallons) {
		try {
			
			Process p = new ProcessBuilder("ext/curl", "--header", "Content-Type: text/xml;charset=UTF-8", "--data",
	        		"@soap_files/getFuelLevel.xml", "http://localhost:8090/car").start();
			p.waitFor();
 
			InputStream inputStream = p.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			
	        String line;
	        boolean passedThroughLoop = false;
	        while ((line = bufferedReader.readLine()) != null)
	        {
	        	assertThat(line, containsString(String.format("<return>%1$d</return>", expectedGallons)));
	        	passedThroughLoop = true;
	        }
	        assertTrue(passedThroughLoop);
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	@When("^the customer owns a car of brand \'([^\"]*)\' and make \'([^\"]*)\'$")
	public void setCustomerCarBrandAndMake(final String brand, final String carModel)
	{
		try {
			m_carModel = carModel; 
			System.out.println(brand + " " + carModel);
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
	
	@And("^the customer fuels the following number of gallons (\\d+) to his or her car of fule (\\d+) type$")
	public void fuelCarWithGallonsOfSpecificFuel(final int gallons, final int fuelType)
	{
		System.out.println(Integer.toString(gallons) + " " + Integer.toString(fuelType));
		
		m_gallons = gallons;
		switch (m_carModel)
		{
			case "Passat":
				if (fuelType == 98)
				{
					m_conversionRate = 16.4;
				}
				else if (fuelType == 95)
				{
					m_conversionRate = 18.3;
				}
				break;
			case "Golf":
				if (fuelType == 95)
				{
					m_conversionRate = 21.1;
				}
				break;
			case "9238478#!%&%!":
				if (fuelType == 90)
				{
					m_conversionRate = 4429496730.0;
				}
				break;			
			default:
				break;
		}
		
	}

	@Then("^the car is has the following (\\s+) expected miles to travel$")
	public void verifyTheExpectedTripDistanceInMiles(final String expectedTripMiles)
	{
		double expectedMiles = Double.parseDouble(expectedTripMiles);
        assertEquals((m_conversionRate * m_gallons), expectedMiles, 0.05);
	}

}
