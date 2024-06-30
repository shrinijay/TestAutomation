package sprint2.week1.base;

import io.opentelemetry.sdk.metrics.data.Data;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import static sprint2.week1.util.ReadFromExcel.readDataFromExcel;

public class BaseClass {
    public static RemoteWebDriver driver;
    public String fileName;

    @BeforeSuite()
   public void preRequisites(){
       ChromeOptions options = new ChromeOptions();
       options.addArguments("--disable-notifications");
       driver = new ChromeDriver(options);
       driver.get("https://login.salesforce.com/");
       driver.manage().window().maximize();
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
       driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
       /*     driver.findElement(By.id("username")).sendKeys("shrinidhivijay@testleaf.com");
        driver.findElement(By.id("password")).sendKeys("Sunlight2");
        driver.findElement(By.id("Login")).click();*/
 }

   @BeforeMethod
    public void login(){

       driver.findElement(By.id("username")).sendKeys("shrinidhivijay@testleaf.com");
       driver.findElement(By.id("password")).sendKeys("Sunlight2");
       driver.findElement(By.id("Login")).click();
   }


   @AfterSuite(alwaysRun = true)
    public void tearDown(){
        driver.quit();
   }

    @DataProvider(name = "dataToBeEntered")
    public Object[] dataSingle(){

       String str[] = new String[1];
       str[0] = "Salesforce Automation by Shrinidhi-default";
       return str;
    }

    @DataProvider(name = "dataFromExcel")
    public Object[][] data(){

            return readDataFromExcel(fileName);

    }

    public static void takeScreenShot(String methodName) throws IOException {
       int rand = (int) (Math.random()*999);
       File dest = new File("./screenshots/snap"+methodName+rand+".jpg");
       File screenShot = driver.getScreenshotAs(OutputType.FILE);
       FileUtils.copyFile(screenShot,dest);


    }


}
