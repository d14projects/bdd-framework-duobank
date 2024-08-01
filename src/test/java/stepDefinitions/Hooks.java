package stepDefinitions;

import io.cucumber.java.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utilities.CSVEraser;
import utilities.CSVWriter;
import utilities.Driver;
import utilities.FrameworkConstants;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class Hooks {

    @BeforeAll
    public static void setUpSignUp(){

        CSVWriter.writeSignUpDataToFile(
                FrameworkConstants.SIGNUP_FILE,
                FrameworkConstants.VALID_LOGIN_FILE,
                10);
        CSVWriter.writeInvalidLoginDataToFile(FrameworkConstants.INVALID_LOGIN_FILE, 2);
    }

    @AfterAll
    public static void tearDownSignUp() {
           CSVEraser.deleteFile(FrameworkConstants.SIGNUP_FILE);
            CSVEraser.deleteFile(FrameworkConstants.VALID_LOGIN_FILE);
            CSVEraser.deleteFile(FrameworkConstants.INVALID_LOGIN_FILE);
    }

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
