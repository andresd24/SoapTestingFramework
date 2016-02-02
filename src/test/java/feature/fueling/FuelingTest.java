package feature.fueling;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
	features = "src/test/resources/feature/fueling/fueling.feature"  
)
public class FuelingTest {
}
