package sprint2.week1.base;

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

import static sprint2.week1.util.ReadFromExcel.readDataFromExcel;

public class BaseClassThreadLocal {
    private static ThreadLocal<RemoteWebDriver> tlDriver;
    public RemoteWebDriver getDriver() {
        return tlDriver.get();
    }

    public void setDriver(RemoteWebDriver driver) {
        tlDriver.set(driver);
    }

    public String fileName;

    @BeforeSuite()
   public void preRequisites(){
       ChromeOptions options = new ChromeOptions();
       options.addArguments("--disable-notifications");
      setDriver(new ChromeDriver(options));
       getDriver().get("https://login.salesforce.com/");
       getDriver().manage().window().maximize();
       getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
       getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
       /*     driver.findElement(By.id("username")).sendKeys("shrinidhivijay@testleaf.com");
        driver.findElement(By.id("password")).sendKeys("Sunlight2");
        driver.findElement(By.id("Login")).click();*/
 }

   @BeforeMethod
    public void login(){

       getDriver().findElement(By.id("username")).sendKeys("shrinidhivijay@testleaf.com");
       getDriver().findElement(By.id("password")).sendKeys("Sunlight2");
       getDriver().findElement(By.id("Login")).click();
   }


   @AfterSuite(alwaysRun = true)
    public void tearDown(){
       getDriver().quit();
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

    public void takeScreenShot(String methodName) throws IOException {
       int rand = (int) (Math.random()*999);
       File dest = new File("./screenshots/snap"+methodName+rand+".jpg");
       File screenShot = getDriver().getScreenshotAs(OutputType.FILE);
       FileUtils.copyFile(screenShot,dest);


    }


}
