package stepDefinitions;

import io.cucumber.java.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utilities.*;

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

    @Before ("@db_only")  //like before and after method in testng
    public void setUpDB(){
        System.out.println("Before Hook for DB-only Scenarios");
        DBUtils.createConnection();
    }

    @After ("@db_only")  //like before and after method in testng
    public void tearDownDB(){
        System.out.println("After Hook for DB-only Scenarios");

        DBUtils.close();
    }

    @Before ("not @db_only")
    public void setupScenario(){
        System.out.println("Before Hook for non-DB-only Scenarios");
        Driver.getDriver().manage().window().maximize();
        Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        DBUtils.createConnection();
    }

    @After ("not @db_only")
    public void tearDownScenario(Scenario scenario){
        System.out.println("After Hook for non-DB-only Scenarios");
        if (scenario.isFailed()){
            byte [] screenshotFile =  ( (TakesScreenshot) Driver.getDriver() ).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshotFile, "image/png", "failed");
        }

        Driver.quitDriver();
        DBUtils.close();
    }
}
