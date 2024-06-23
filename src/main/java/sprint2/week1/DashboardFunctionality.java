package sprint2.week1;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.SQLOutput;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DashboardFunctionality extends BaseClass {

    @BeforeMethod
    public void selectDashBoardFromMenu(){
            //2.Click on the toggle menu button from the left corner
           driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
            //3.Click View All and click Dashboards from App Launcher
            driver.findElement(By.xpath("//button[@aria-label='View All Applications']")).click();

            WebElement dashboard = driver.findElement(By.xpath("//a[@data-label='Dashboards']"));

            driver.executeScript("arguments[0].scrollIntoView();",dashboard);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();",dashboard);
        }


    @Test
    public void createDashBoard() throws InterruptedException {
        //4. Click on the New Dashboard option
        driver.findElement(By.xpath("//a[@title = 'New Dashboard']")).click();
        //5. Enter Name as 'Salesforce Automation by Your Name'  and Click on Create
        Thread.sleep(3000);
        driver.switchTo().frame(0);
        driver.findElement(By.id("dashboardNameInput")).sendKeys("Salesforce Automation by Shrinidhi 12");
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

            Assert.assertTrue(message.equals("Dashboard saved"),"Dashboard saved successfully");
        }
        else{
            System.out.println("Not created");
            throw new RuntimeException();

        }

    }

    @Test
    public void editDashboard() throws InterruptedException {

            //  5. Search the Dashboard 'Salesforce Automation by Your Name'
            driver.findElement(By.xpath("//input[@placeholder='Search recent dashboards...']")).sendKeys("Salesforce Automation by");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            Thread.sleep(3000);
            //wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//tr[@class='slds-hint-parent']")));
            driver.findElement(By.xpath("//table/tbody/tr[1]/td[6]//button")).click();
            Thread.sleep(3000);
            driver.findElement(By.xpath("//span[text()='Edit']")).click();
            // driver.findElement(By.xpath("//span[text()='Edit Dashboard name']/parent::button")).click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("(//div[@class='loadingSpinner'])[3]")));
            // wait.until(ExpectedConditions.presenceOfElementLocated(By.id("edit-dashboard-title")));
            Thread.sleep(3000);
            driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[@title='dashboard'])[2]")));
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Edit Dashboard name']/parent::button")));
            driver.findElement(By.xpath("//span[text()='Edit Dashboard name']/parent::button")).click();
            driver.findElement(By.id("edit-dashboard-title")).sendKeys("Salesforce Automation by Shrinidhi12 Edit");
            driver.findElement(By.id("edit-dashboard-title")).sendKeys(Keys.ENTER);
            String text = driver.findElement(By.xpath("//div[@class='slds-form-element editTitle']//span")).getText();
            System.out.println("text is " + text);
            if(text.contains("Edit"))
            {
                System.out.println("Dashboard edited");
            }
            else{
                System.out.println("Dashboard not edited");
                throw  new RuntimeException();
            }
            driver.switchTo().defaultContent();


    }

    @Test
    public void dashboardSubscription() throws InterruptedException {

        driver.findElement(By.xpath("//input[@placeholder='Search recent dashboards...']")).sendKeys("Salesforce Automation by");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//tbody/tr[1]/td[6]"))));
        System.out.println("came after this step");
        Thread.sleep(5000);
        driver.findElement(By.xpath("//table/tbody/tr[1]/td[6]//button")).click();

        driver.findElement(By.xpath("//span[text()='Subscribe']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//legend[text()='Frequency']")));
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
            Assert.assertTrue(message.contains("subscription"),"Dashboard subscription started");

        }
        else{
            System.out.println("Dashboard not subscribed");
            throw new RuntimeException();
        }

    }

    @Test
    public void deleteDashboard() {
        driver.findElement(By.xpath("//input[@placeholder='Search recent dashboards...']")).sendKeys("Salesforce Automation by");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath("//tr[@class='slds-hint-parent']"))));
        driver.findElement(By.xpath("//table/tbody/tr[1]/td[6]//button")).click();

        driver.findElement(By.xpath("//span[text()='Delete']")).click();

        driver.findElement(By.xpath("//button[@title='Delete']")).click();
        String message = driver.findElement(By.xpath("//span[contains(@class,'toastMessage')]")).getText();
        System.out.println(message);

        Assert.assertEquals("Dashboard was deleted.",message);


    }

    @Test
    public void sortDashboardName() throws InterruptedException {
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
       // Assert.assertFalse(j==strList.size(),"Dashboard names are  sorted");
        if(j==strList.size()){
            System.out.println("Dashboard names are sorted");

        }
        else{
            System.out.println("Dashboard names are not sorted");
        }
    }
}
