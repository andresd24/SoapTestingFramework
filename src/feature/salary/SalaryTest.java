package feature.salary;

import org.junit.runner.RunWith;

import cucumber.api.junit.Cucumber;
import cucumber.api.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/feature/salary/salary_management.feature"  //refer to Feature file
	)


public class SalaryTest {

}
