package cucumber.Runner;

import cucumber.base.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.time.Duration;

@CucumberOptions(features="src/main/java/cucumber/FeatureFiles/ServiceTerriority.feature",
        glue="cucumber.stepDef",
        monochrome=true,
        publish=true)

public class RunnerClass extends BaseClass {


}
