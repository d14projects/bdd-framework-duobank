package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
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

//    @Before ("@signup")
//    public void setUpSignUp(){
//
//        CSVWriter.writeSignUpDataToFile(
//                FrameworkConstants.SIGNUP_FILE,
//                FrameworkConstants.VALID_LOGIN_FILE,
//                10);
//        CSVWriter.writeInvalidLoginDataToFile(FrameworkConstants.INVALID_LOGIN_FILE, 2);
//        Driver.getDriver().manage().window().maximize();
//        Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//    }
//
//    @After ("@signup")
//    public void tearDownSignUp(Scenario scenario) {
//            CSVEraser.deleteFile(FrameworkConstants.SIGNUP_FILE);
//            CSVEraser.deleteFile(FrameworkConstants.VALID_LOGIN_FILE);
//            CSVEraser.deleteFile(FrameworkConstants.INVALID_LOGIN_FILE);
//        if (scenario.isFailed()){
//            byte [] screenshotFile =  ( (TakesScreenshot) Driver.getDriver() ).getScreenshotAs(OutputType.BYTES);
//            scenario.attach(screenshotFile, "image/png", "failed");
//        }
//
//        Driver.quitDriver();
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
