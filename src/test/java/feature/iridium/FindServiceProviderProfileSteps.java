package feature.iridium;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import static org.junit.Assert.*;

public class FindServiceProviderProfileSteps {
	private static final long MAX_TIME_TO_WAIT = 2;
	private InputStream inputStream = null;
	private List<String> command  = null;

	@Before
	public void beforeScenario() {
		this.command = Stream.of("ext/curl", "Content-Type: text/xml;charset=UTF-8", "--data",
				"@soap_files/test_cases/findServiceProviderProfile.xml",
				"http://192.168.0.218:8080/iws-current/iws-int").collect(Collectors.toList());
	}

	@Given("^a request to FindServiceProviderProfile web method with the following parameters (.*),(.*),(.*) and (.*)$")
	public void callToFindServiceProviderProfile(String signature, String timeStamp, String accountNumber, String userName) throws InterruptedException, IOException {
		System.out.println(this.command);
		ProcessBuilder pb = new ProcessBuilder("ext/curl", "--header", "Content-Type: text/xml;charset=UTF-8", "--data",
				"@soap_files/test_cases/findServiceProviderProfile.xml",
				"http://192.168.0.218:8080/iws-current/iws-int");

		Process p = pb.start();
		p.waitFor(MAX_TIME_TO_WAIT, TimeUnit.SECONDS);

		this.inputStream = p.getInputStream();
		assertNotNull(this.inputStream);
	}

	@Then("^we can validate that the result contains \"(.*?)\"$")
	public void checkAccountName(String accountName) throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(this.inputStream));) {
			assertThat(br.readLine(), containsString(String.format("<accountName>%1$s</accountName>", accountName)));
		}
	}

}
