package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/Features",
        glue = {"stepDefinitionPages"},
        plugin = {"json:target/cucumber.json"},
        tags = "@valid_Order")
public class CucumberRunner extends AbstractTestNGCucumberTests {
}
