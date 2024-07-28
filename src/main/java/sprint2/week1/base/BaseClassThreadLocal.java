package sprint2.week1.base;

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

public class BaseClassThreadLocal {
    private static ThreadLocal<RemoteWebDriver> tlDriver = new ThreadLocal<>();
    public RemoteWebDriver getDriver() {
        return tlDriver.get();
    }

    public void setDriver(RemoteWebDriver driver) {
        tlDriver.set(driver);
    }

    public String fileName;

    @BeforeClass()
   public void preRequisites(){
       ChromeOptions options = new ChromeOptions();
       options.addArguments("--disable-notifications");
        tlDriver.set(new ChromeDriver(options));
       getDriver().get("https://login.salesforce.com/");
       getDriver().manage().window().maximize();
       getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
       getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
       /*     driver.findElement(By.id("username")).sendKeys("shrinidhivijay@testleaf.com");
        driver.findElement(By.id("password")).sendKeys("Sunlight2");
        driver.findElement(By.id("Login")).click();*/
        System.out.println("driver from before suite - "+getDriver());
        getDriver().findElement(By.id("username")).sendKeys("shrinidhivijay@testleaf.com");
        getDriver().findElement(By.id("password")).sendKeys("Sunlight2");
        getDriver().findElement(By.id("Login")).click();
 }

   /*@BeforeClass(alwaysRun = true)
    public void login(){
       System.out.println("driver from bc----->  " +getDriver());

   }*/


   @AfterClass(alwaysRun = true)
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
