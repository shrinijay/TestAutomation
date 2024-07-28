package sprint3.assessment.base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

import static sprint2.week1.util.ReadFromExcel.readDataFromExcel;

public class BaseClass {
    public RemoteWebDriver driver;
    public String fileName;


    @BeforeSuite()
   public void preRequisites(){
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("profile.default_content_settings.popups",0);
        map.put("download.default_directory",System.getProperty("user.dir"));
       ChromeOptions options = new ChromeOptions();
       options.addArguments("--disable-notifications");
       options.setExperimentalOption("prefs",map);
       driver = new ChromeDriver(options);
       driver.get("https://login.salesforce.com/");
       driver.manage().window().maximize();
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
       driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

 }

     @AfterSuite(alwaysRun = true)
    public void tearDown(){
        driver.quit();
   }

    @DataProvider(name = "dataFromExcel")
    public Object[][] data(){

            return readDataFromExcel(fileName);

    }

    public void takeScreenShot(String methodName) throws IOException {
       int rand = (int) (Math.random()*999);
       File dest = new File("./screenshots/snap"+methodName+rand+".jpg");
       File screenShot = driver.getScreenshotAs(OutputType.FILE);
       FileUtils.copyFile(screenShot,dest);


    }


}
