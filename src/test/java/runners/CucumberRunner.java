package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/Features",
        glue = {"nespressoStepDefinitionsPages"},
        plugin = {"json:target/cucumber.json"})
public class CucumberRunner extends AbstractTestNGCucumberTests {
}
