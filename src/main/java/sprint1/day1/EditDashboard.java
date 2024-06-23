package sprint1.day1;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EditDashboard {

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
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", dashboard);
            //  5. Search the Dashboard 'Salesforce Automation by Your Name'
            driver.findElement(By.xpath("//input[@placeholder='Search recent dashboards...']")).sendKeys("Salesforce Automation by");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            //wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//tr[@class='slds-hint-parent']")));
            Thread.sleep(3000);
            driver.findElement(By.xpath("//table/tbody/tr[1]/td[6]//button")).click();
            Thread.sleep(3000);
            driver.findElement(By.xpath("//span[text()='Edit']")).click();
            // driver.findElement(By.xpath("//span[text()='Edit Dashboard name']/parent::button")).click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("(//div[@class='loadingSpinner'])[3]")));
            // wait.until(ExpectedConditions.presenceOfElementLocated(By.id("edit-dashboard-title")));
            Thread.sleep(3000);
            driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[@title='dashboard'])[3]")));
            driver.findElement(By.id("edit-dashboard-title")).sendKeys("Salesforce Automation by Shrinidhi Edit");
            driver.findElement(By.id("edit-dashboard-title")).sendKeys(Keys.ENTER);
            String text = driver.findElement(By.xpath("//div[@class='slds-form-element editTitle']//span")).getText();
            System.out.println("text is " + text);
            driver.switchTo().defaultContent();
        }
        catch (Exception e){
            System.out.println(e.fillInStackTrace());
            driver.quit();
        }


    }
}
