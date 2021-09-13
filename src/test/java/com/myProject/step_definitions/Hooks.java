package com.myProject.step_definitions;

import com.myProject.utilities.common.ConfigurationReader;
import com.myProject.utilities.db.DBUtility;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import static io.restassured.RestAssured.*;

public class Hooks extends DBUtility {

//    @Before(order = 0)
//    public void setUpScenario() {
//        Driver.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        Driver.getDriver().manage().window().maximize();
//    }

    @Before(order = 1)
    public static void setURL() {
        baseURI = ConfigurationReader.getProperty("baseURI");
        basePath = ConfigurationReader.getProperty("basePath");
    }


    @After
    public void cleanUp(Scenario scenario) {
//        //scenario.getSourceTagNames();//prints all tags
//        scenario.getSourceTagNames();
//        if (scenario.isFailed()) {
//            byte[] screenShot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
//            scenario.attach(screenShot, "image/png", scenario.getName());
//        }
//        Driver.closeDriver();//UI
//        destroy(); //DB
        reset(); //API

    }

}
