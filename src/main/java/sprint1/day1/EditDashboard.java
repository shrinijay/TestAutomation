package sprint1.day1;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class EditDashboard {

    public static void main(String[] args) throws InterruptedException {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");

        ChromeDriver driver = new ChromeDriver(options); //ChromeDriver,RemoteWebDriver,FireFox - no need to do typecasting // WebDriver - typecasting typecasting -  parent class is trying to access child class
        driver.get("https://login.salesforce.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.findElement(By.id("username")).sendKeys("shrinidhivijay@testleaf.com");
        driver.findElement(By.id("password")).sendKeys("Sunlight2");
        driver.findElement(By.id("Login")).click();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.getTitle();
        //2.Click on the toggle menu button from the left corner
        //div[@class='slds-icon-waffle']
        driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
        //3.Click View All and click Dashboards from App Launcher
        driver.findElement(By.xpath("//button[@aria-label='View All Applications']")).click();

        WebElement dashboard = driver.findElement(By.xpath("//a[@data-label='Dashboards']"));

        driver.executeScript("arguments[0].scrollIntoView();",dashboard);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();",dashboard);
      //  5. Search the Dashboard 'Salesforce Automation by Your Name'
        driver.findElement(By.xpath("//input[@placeholder='Search recent dashboards...']")).sendKeys("Salesforce Automation by");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//table/tbody/tr[1]/td[6]//button")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//span[text()='Edit']")).click();

        Thread.sleep(3000);
        driver.switchTo().frame(0);
        driver.findElement(By.id("dashboardNameInput")).sendKeys("Salesforce Automation by Shrinidhi 1");
        driver.findElement(By.id("submitBtn")).click();
        driver.switchTo().defaultContent();
        //6.Click on Save and Verify Dashboard name
        Thread.sleep(3000);
        driver.switchTo().frame(0);
        driver.findElement(By.xpath("//button[text()='Save']")).click();
        driver.switchTo().defaultContent();
       String message = driver.findElement(By.xpath("//span[contains(@class,'toastMessage')]")).getText();
        System.out.println(message);
        if(message.equals("Dashboard saved"))
        {
            System.out.println("Dashboard created successfully");
        }
        else{
            System.out.println("Not created");
        }
        driver.quit();

    }
}
