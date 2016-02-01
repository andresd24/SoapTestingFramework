package feature.fueling;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.lang.ProcessBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class FuelingSteps {
	
	@Given("The customer empties the the car's fuel levels")
	public void connectToCarService() {
		try {
	        ProcessBuilder pb = new ProcessBuilder("curl.exe", "--header", "Content-Type: text/xml;charset=UTF-8", "--data",
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
	        ProcessBuilder pb = new ProcessBuilder("curl.exe", "--header", "Content-Type: text/xml;charset=UTF-8", "--data",
	          		String.format("@soap_files/test_cases/addFuel_%1$d.xml", gallons), "http://localhost:8090/car");
	        Process p = pb.start(); 
	        p.waitFor(); 
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	@Then("the car is left with the expected (\\d+) gallons")
	public void verifyAmountOfGalons(final int expectedGallons) {
		try {
			
			Process p = new ProcessBuilder("curl.exe", "--header", "Content-Type: text/xml;charset=UTF-8", "--data",
	        		"@soap_files/getFuelLevel.xml", "http://localhost:8090/car").start();
			p.waitFor();
 
			InputStream inputStream = p.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			
	        String line;
	        while ((line = bufferedReader.readLine()) != null)
	        {
	        	assertThat(line, containsString(String.format("<return>%1$d</return>", expectedGallons)));
	        }
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}
