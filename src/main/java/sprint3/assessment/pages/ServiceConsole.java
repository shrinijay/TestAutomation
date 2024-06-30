package sprint3.assessment.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ServiceConsole {

    private RemoteWebDriver driver;
    WebDriverWait wait;

    public ServiceConsole(RemoteWebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(this.driver, Duration.ofSeconds(15));

    }



    public ServiceConsole clickOnFilesFromDropDown() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[@title='Files']"))));
        //wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[@title='Show Navigation Menu']"))));
        driver.findElement(By.xpath("//button[@title='Show Navigation Menu']")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[@class='slds-media__body']/span[text()='Files']"))));
        driver.findElement(By.xpath("//span[@class='slds-media__body']/span[text()='Files']")).click();
        System.out.println(driver.getTitle());
        return this;

    }

    public ServiceConsole clickOnArrowForTheLatestModifiedItem(){
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//tbody/tr[1]//span[contains(@class,' slds-icon-utility-down')]"))));
        driver.findElement(By.xpath("//tbody/tr[1]//span[contains(@class,' slds-icon-utility-down')]")).click();
         return this;
    }

    public ServiceConsole clickOnPublicLink(){
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[@title='Public Link']"))));
        driver.findElement(By.xpath("//a[@title='Public Link']")).click();
        return this;
    }

    public ServiceConsole clickOnCreateLink() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[@title='Create Link']"))));
        driver.findElement(By.xpath("//button[@title='Create Link']")).click();
        driver.findElement(By.xpath("//span[text()='Create']")).click();
        System.out.println("input text "+driver.findElement(By.xpath("//input[@name='publicLinkURL']")).getText());
        return this;
    }

}

