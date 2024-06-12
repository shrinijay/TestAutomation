package sprint1.day2;

import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SortTheDashboardName {

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
            List<String> strList = new ArrayList<>(); List<String> strListAfterSort = new ArrayList<>();
            List<WebElement> dNamesBfrSorting = driver.findElements(By.xpath("//th[@data-label='Dashboard Name']//a"));
            for(WebElement d : dNamesBfrSorting){
                strList.add(d.getText());
            }
            System.out.println("strList --> "+strList);
            Collections.sort(strList);
            System.out.println("strList after sorting--> "+strList);
            Thread.sleep(5000);
            driver.findElement(By.xpath("//th[@aria-label='Dashboard Name']//a")).click();
            Thread.sleep(5000);
            for(WebElement e: driver.findElements(By.xpath("//th[@data-label='Dashboard Name']//a")) )   {
                strListAfterSort.add(e.getText());
            }
            System.out.println("strListAfterSort --> "+strListAfterSort);
            int j=0;
            for(int i=0;i<strList.size();i++)
            {
                if(strList.get(i).equals(strListAfterSort.get(i))){
                    j++;
                }
            }
            if(j==strList.size()){
                System.out.println("Dashboard names are sorted");
            }
            else{
                System.out.println("Dashboard names are not sorted");
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
