package sprint1.week2.day1;

import io.github.sukgu.Shadow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Customer_Service_Options {

    public static void main(String[] args) {
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
            System.out.println(driver.getTitle());
            //3. Click on Learn More link in Mobile Publisher
            driver.findElement(By.xpath("//span[text()='Mobile Publisher']/following::button[@title='Learn More']")).click();
            //new window opens - so windows handle

            Set<String> windows = driver.getWindowHandles();
            List<String> windowsList = new ArrayList<>(windows);

            driver.switchTo().window(windowsList.get(1));
            driver.findElement(By.xpath("//button[text()='Confirm']")).click();
            String newWindowTitle = driver.getTitle();
            // 4. MouseHover on Products and Select Service
            Shadow shadow = new Shadow(driver);
            WebElement products = shadow.findElementByXPath("//span[text()='Products']");
            Actions act = new Actions(driver);
            act.moveToElement(products).click().build().perform();
            Thread.sleep(5000);
            act.moveToElement(shadow.findElementByXPath("//div[text()='Service']")).perform();
            List<WebElement> allServiceLists = shadow.findElementsByXPath("//h2[text()='Service']/parent::div/following-sibling::ul//a[contains(@class,'linkedlist__listitem-title')]");
            System.out.println("Service Lists");
            for ( WebElement serviceList : allServiceLists
                 ) {
                System.out.println("\n"+serviceList.getText());
            }
            act.moveToElement(shadow.findElementByXPath("//div[text()='Service']")).click().perform();
            String title = driver.getCurrentUrl();
            System.out.println("title is "+title);
            if(title.contains("service"))
            {
                System.out.println("Service tab is selected");
            }
            else{
                System.out.println("Service tab is not selected");
            }

            driver.switchTo().window(windowsList.get(0));
            // 5. Verify the tabs displayed in the pageExpected Result:Below tabs should be displayed1. Products2. Company3. Support & Services4. Resurces5. COVID-19Step


        }
        catch (Exception e) {
            e.printStackTrace();

        }
        finally {
            driver.quit();
        }
    }
}
