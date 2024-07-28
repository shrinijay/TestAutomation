package cucumber.stepDef;

import cucumber.Runner.RunnerClass;
import cucumber.base.BaseClass;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en_scouse.An;
import org.openqa.selenium.By;

public class ServiceTerriorityStepDef extends BaseClass{

    @Given("I login into the salesforce page")
    public void loginToSalesForcePage(){
        driver.findElement(By.id("username")).sendKeys("shrinidhivijay@testleaf.com");
        driver.findElement(By.id("password")).sendKeys("Sunlight2");
        driver.findElement(By.id("Login")).click();

    }
    @And("I select Service Terriority from App Launcher")
    public void selectServiceTerriority(){
        //2.Click on the toggle menu button from the left corner
        //div[@class='slds-icon-waffle']
        System.out.println("driver is "+driver);
        driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
        //3.Click View All
        driver.findElement(By.xpath("//button[@aria-label='View All Applications']")).click();
        //Click on Service Territories
        // WebElement serviceTerritory = driver.findElement(By.xpath("//p[text()='Service Territories']"));
        driver.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//p[text()='Service Territories']")));
        driver.executeScript("arguments[0].click();", driver.findElement(By.xpath("//p[text()='Service Territories']")));
    }

    @And("I create new Service Terriority")
    public void createNewServiceTerriority() throws InterruptedException {
        driver.findElement(By.cssSelector("div[title='New']")).click();
        Thread.sleep(5000);
    }
}
