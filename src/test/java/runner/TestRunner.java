package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(features = "src/test/resources/features/",
                    glue = {"org.nsw.gov.stepdefs"},
             plugin = {"pretty", "html:target/cucumber-reports.html", "json:target/cucumber.json"},
             monochrome = true,
             publish = true)
public class TestRunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
