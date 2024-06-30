package sprint3.assessment.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import sprint3.assessment.base.BaseClass;

public class HomePage {

    private RemoteWebDriver driver;
    public HomePage(RemoteWebDriver driver){
        this.driver = driver;
    }

    public HomePage clickOnToggleMenu() {
        //2.Click on the toggle menu button from the left corner
        driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
        return this;
    }

    public HomePage clickViewAll() {

        //3.Click View All and click Dashboards from App Launcher
        driver.findElement(By.xpath("//button[@aria-label='View All Applications']")).click();
        return this;
    }

    public ServiceConsole selectServiceConsole(){
         driver.findElement(By.xpath("//p[text()='Service Console']")).click();
         return new ServiceConsole(driver);

    }

}
