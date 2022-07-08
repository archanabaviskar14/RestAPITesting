package cucumber.options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="C:\\Users\\RavindraBaviskar\\eclipse-workspace\\api\\src\\test\\java\\features\\placevalidationapi.feature",
				 glue={"stepDefinitions"},
				 plugin="json:target/jsonReports/cucumber-report.json")
				// tags= "@DeletePlace")
public class TestRunner {

}
 