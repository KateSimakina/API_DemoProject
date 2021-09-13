package com.myProject.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "@target/rerun.txt", //path from content root
        glue = "com/myProject/step_definitions_old"//path from source root
)

public class FailedTestRunner {
}
