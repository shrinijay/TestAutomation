package sprint2.week1;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.time.Duration;

public class BaseClass {
    public RemoteWebDriver driver;

    @BeforeClass
   public void preRequisites(){
       ChromeOptions options = new ChromeOptions();
       options.addArguments("--disable-notifications");
       driver = new ChromeDriver(options);
       driver.get("https://login.salesforce.com/");
       driver.manage().window().maximize();
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
       driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.findElement(By.id("username")).sendKeys("shrinidhivijay@testleaf.com");
        driver.findElement(By.id("password")).sendKeys("Sunlight2");
        driver.findElement(By.id("Login")).click();



   }

  /* @BeforeTest
    public void login(){

   }*/

   @AfterClass(alwaysRun = true)
    public void tearDown(){
        driver.quit();
   }




}
