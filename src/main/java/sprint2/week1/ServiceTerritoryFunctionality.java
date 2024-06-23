package sprint2.week1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class ServiceTerritoryFunctionality extends BaseClass {

    @BeforeMethod
    public void clickOnServiceTerritoryMenu(){
        //2.Click on the toggle menu button from the left corner
        //div[@class='slds-icon-waffle']
        driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
        //3.Click View All
        driver.findElement(By.xpath("//button[@aria-label='View All Applications']")).click();
        //Click on Service Territories
        WebElement serviceTerritory = driver.findElement(By.xpath("//p[text()='Service Territories']"));
        driver.executeScript("arguments[0].scrollIntoView();", serviceTerritory);
        driver.executeScript("arguments[0].click();", serviceTerritory);
    }

    @Test
    public void createServiceTerritory(){
        driver.findElement(By.cssSelector("div[title='New']")).click();
        //Thread.sleep(5000);
        // 8) Enter Your Name in Name field
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By nameField = By.cssSelector("input[name='Name']");//using css selector not working have to ask
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@name='Name']"))));
        //Thread.sleep(5000);
        driver.findElement(By.xpath("//input[@name='Name']")).sendKeys("Shrinidhi1");
        // 9) Click on Operating Hours and Choose the First option
        driver.findElement(By.xpath("//input[contains(@placeholder,'Operating Hours')]")).click();
        //driver.findElement(By.cssSelector("ul[aria-label='Recent Operating Hours'] li:nth-of-type(2)")).click();
        driver.findElement(By.xpath("//ul[@aria-label='Recent Operating Hours']/li[2]")).click();
        // 10) Check Active Field
        WebElement activechkbox = driver.findElement(By.xpath("//input[@name='IsActive']"));
        activechkbox.click();
        System.out.println(activechkbox.isSelected());

        // 11) Enter the City your residing in City Field
        driver.findElement(By.xpath("//input[@name='city']")).sendKeys("Chennai");

        // 12) Enter the State your residing in State Field
        driver.findElement(By.xpath("//input[@name='province']")).sendKeys("TN");
        // 13) Enter the Country your residing in Country Field
        driver.findElement(By.xpath("//input[@name='country']")).sendKeys("India");
        // 14) Enter your current Postal Zip Code
        driver.findElement(By.xpath("//input[@name='postalCode']")).sendKeys("India");
        // 15) Click on Save
        driver.findElement(By.xpath("//button[@name='SaveEdit']")).click();
        // 16) Verify Service Territory is created SuccessfullyExpected Result:Service Territory Should be created
        String message = driver.findElement(By.xpath("//span[contains(@class,'toastMessage')]")).getText();
        System.out.println(message);
        Assert.assertTrue(message.contains("created"),"Service Terriority not created");



    }
}
