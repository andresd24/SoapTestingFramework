package feature.book;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/feature/book/search_book.feature"  //refer to Feature file
	)


public class BookSearchTest {
}
