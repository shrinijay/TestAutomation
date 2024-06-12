package sprint1.day2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashboardSubscription {

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

            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//tbody/tr[1]/td[6]"))));
            System.out.println("came after this step");
            Thread.sleep(5000);
            driver.findElement(By.xpath("//table/tbody/tr[1]/td[6]//button")).click();

            driver.findElement(By.xpath("//span[text()='Subscribe']")).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//legend[text()='Frequency']")));
            //driver.findElement(By.id("daily")).click(); //org.openqa.selenium.ElementClickInterceptedException: element click intercepted: Element <input aria-describedby="" name="radio" id="daily" type="radio" data-aura-rendered-by="700:0" class="uiInput uiInputRadio uiInput--default uiInput--radio" data-aura-class="uiInput uiInputRadio uiInput--default uiInput--radio" data-interactive-lib-uid="3"> is not clickable at point (287, 246). Other element would receive the click: <div class="slds-radio--button-group" data-aura-rendered-by="555:0">...</div>
            System.out.println("i am here bfr clickable");
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//label[@for='daily']/input"))));
            System.out.println("i am here after clickable");
           WebElement daily = driver.findElement(By.xpath("//label[@for='daily']/input"));
           driver.executeScript("arguments[0].click();",daily);
            driver.findElement(By.xpath("//span[text()='Save']")).click();
            Thread.sleep(3000);
            String message = driver.findElement(By.xpath("//span[contains(@class,'toastMessage')]")).getText();
            Thread.sleep(5000);
            System.out.println(message);
            if(message.contains("subscription"))
            {
                System.out.println("Dashboard subscription started");
            }
            else{
                System.out.println("Dashboard not subscribed");
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
