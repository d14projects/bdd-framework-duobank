package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import org.junit.BeforeClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utilities.CSVWriter;
import utilities.Driver;

import java.time.Duration;

public class Hooks {

//    @Before
//    public static void createFile(){
//        CSVWriter.writeSignUpDataToFile("src/test/signUpData.csv",
//                "src/test/validLogin.csv", 2);
//        CSVWriter.writeInvalidLoginDataToFile("src/test/invalidLogin.csv", 2);
//    }

    @Before
    public void setupScenario(){
        Driver.getDriver().manage().window().maximize();
        Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @After
    public void tearDownScenario(Scenario scenario){

        if (scenario.isFailed()){
            byte [] screenshotFile =  ( (TakesScreenshot) Driver.getDriver() ).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshotFile, "image/png", "failed");
        }

        Driver.quitDriver();
    }
}
