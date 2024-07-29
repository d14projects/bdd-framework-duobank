package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@CucumberOptions(
            tags = "@login",
            features = "src/test/resources",
            glue = "stepDefinitions",
            plugin = {
            "pretty",
            "html:target/cucumber-report/report.html"
            }
          , publish = true  // generates a web based report with link on the console
//         , stepNotifications = true
//          , dryRun = true   // dry runs the scenario for snippet generation
)
@RunWith(Cucumber.class)
public class CucumberRunner {

}
