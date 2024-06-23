package sprint1.day2;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.sound.midi.Soundbank;
import java.time.Duration;

public class DeleteDashboard {

    public static void main(String[] args) throws InterruptedException {

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");

            ChromeDriver driver = new ChromeDriver(options); //ChromeDriver,RemoteWebDriver,FireFox - no need to do typecasting // WebDriver - typecasting typecasting -  parent class is trying to access child class
        try {
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

            driver.executeScript("arguments[0].scrollIntoView();", dashboard);
            driver.executeScript("arguments[0].click();", dashboard);
            driver.findElement(By.xpath("//input[@placeholder='Search recent dashboards...']")).sendKeys("Salesforce Automation by");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath("//tr[@class='slds-hint-parent']"))));
            driver.findElement(By.xpath("//table/tbody/tr[1]/td[6]//button")).click();

            driver.findElement(By.xpath("//span[text()='Delete']")).click();

            driver.findElement(By.xpath("//button[@title='Delete']")).click();
            String message = driver.findElement(By.xpath("//span[contains(@class,'toastMessage')]")).getText();
            System.out.println(message);
            if(message.equals("Dashboard was deleted"))
            {
                System.out.println("Dashboard deleted");
            }
            else{
                System.out.println("Not deleted");
            }
        }
        catch (Exception e) {
            e.printStackTrace();

        }
        finally {
            driver.quit();
        }

    }
}
