package sprint2.week1.TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import sprint2.week1.base.BaseClass;
import sprint2.week1.base.BaseClassThreadLocal;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DashboardFunctionalityThreadLocal extends BaseClassThreadLocal {

    @BeforeTest
    public void setValues(){
        fileName = "DashboardInput";
    }

    @AfterMethod
    public void signOut(){
       getDriver().findElement(By.xpath("//button[contains(@class,'branding-userProfile-button')]")).click();
        WebDriverWait wait = new WebDriverWait( getDriver(),Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf( getDriver().findElement(By.xpath("//a[contains(@class,'logout')]"))));
        getDriver().findElement(By.xpath("//a[contains(@class,'logout')]")).click();
    }

    @BeforeMethod
    public void selectDashBoardFromMenu(){
            //2.Click on the toggle menu button from the left corner
        getDriver().findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
            //3.Click View All and click Dashboards from App Launcher
        getDriver().findElement(By.xpath("//button[@aria-label='View All Applications']")).click();

            WebElement dashboard = getDriver().findElement(By.xpath("//a[@data-label='Dashboards']"));

            getDriver().executeScript("arguments[0].scrollIntoView();",dashboard);
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            js.executeScript("arguments[0].click();",dashboard);
        }

    /* @Test
    public void dummy(){
            System.out.println("came here");
            Assert.fail("failing it explictly to check retry analyzer");
        }*/
   // @Test(dataProvider = "dataFromExcel") //, retryAnalyzer = sprint2.week1.retry.RetryFailedCases.class
    public void createDashBoardForMultipleData(String data) throws InterruptedException {
        //4. Click on the New Dashboard option
        getDriver().findElement(By.xpath("//a[@title = 'New Dashboard']")).click();
        //5. Enter Name as 'Salesforce Automation by Your Name'  and Click on Create
        Thread.sleep(3000);
        getDriver().switchTo().frame(0);
        getDriver().findElement(By.id("dashboardNameInput")).sendKeys(data);
        getDriver().findElement(By.id("submitBtn")).click();
        getDriver().switchTo().defaultContent();
        //6.Click on Save and Verify Dashboard name
        Thread.sleep(3000);
        getDriver().switchTo().frame(0);
        getDriver().findElement(By.xpath("//button[text()='Save']")).click();
        getDriver().switchTo().defaultContent();
        String message = getDriver().findElement(By.xpath("//span[contains(@class,'toastMessage')]")).getText();
        System.out.println(message);
        Assert.assertTrue(message.equals("Dashboard saved"),"Dashboard saved successfully");

    }

    @Test(dataProvider = "dataToBeEntered") //, retryAnalyzer = sprint2.week1.retry.RetryFailedCases.class
    public void createDashBoard(String data) throws InterruptedException {
        //4. Click on the New Dashboard option
        getDriver().findElement(By.xpath("//a[@title = 'New Dashboard']")).click();
        //5. Enter Name as 'Salesforce Automation by Your Name'  and Click on Create
        Thread.sleep(3000);
        getDriver().switchTo().frame(0);
        getDriver().findElement(By.id("dashboardNameInput")).sendKeys(data);
        getDriver().findElement(By.id("submitBtn")).click();
        getDriver().switchTo().defaultContent();
        //6.Click on Save and Verify Dashboard name
        Thread.sleep(3000);
        getDriver().switchTo().frame(0);
        getDriver().findElement(By.xpath("//button[text()='Save']")).click();
        getDriver().switchTo().defaultContent();
        String message = getDriver().findElement(By.xpath("//span[contains(@class,'toastMessage')]")).getText();
        System.out.println(message);
        Assert.assertTrue(message.equals("Dashboard saved"),"Dashboard saved successfully");

    }

    @Test(dataProvider = "dataToBeEntered")//,retryAnalyzer = sprint2.week1.retry.RetryFailedCases.class
    public void editDashboard(String data) throws InterruptedException {

            //  5. Search the Dashboard 'Salesforce Automation by Your Name'
        getDriver().findElement(By.xpath("//input[@placeholder='Search recent dashboards...']")).sendKeys("Salesforce Automation by");
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
           // Thread.sleep(3000);
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//tbody/tr[1]")));
        getDriver().findElement(By.xpath("//table/tbody/tr[1]/td[6]//button")).click();
            Thread.sleep(3000);
        getDriver().findElement(By.xpath("//span[text()='Edit']")).click();
            // driver.findElement(By.xpath("//span[text()='Edit Dashboard name']/parent::button")).click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("(//div[@class='loadingSpinner'])[3]")));
            // wait.until(ExpectedConditions.presenceOfElementLocated(By.id("edit-dashboard-title")));
            Thread.sleep(3000);
        getDriver().switchTo().frame(getDriver().findElement(By.xpath("(//iframe[@title='dashboard'])[2]")));
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Edit Dashboard name']/parent::button")));
        getDriver().findElement(By.xpath("//span[text()='Edit Dashboard name']/parent::button")).click();
        getDriver().findElement(By.id("edit-dashboard-title")).sendKeys(data+"Edit");
        getDriver().findElement(By.id("edit-dashboard-title")).sendKeys(Keys.ENTER);
            String text = getDriver().findElement(By.xpath("//div[@class='slds-form-element editTitle']//span")).getText();
            System.out.println("text is " + text);
            if(text.contains("Edit"))
            {
                System.out.println("Dashboard edited");
            }
            else{
                System.out.println("Dashboard not edited");
                throw  new RuntimeException();
            }
        getDriver().switchTo().defaultContent();


    }

  @Test() //retryAnalyzer = sprint2.week1.retry.RetryFailedCases.class
    public void dashboardSubscription() throws InterruptedException {

      getDriver().findElement(By.xpath("//input[@placeholder='Search recent dashboards...']")).sendKeys("Salesforce Automation by");
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));

        wait.until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//tbody/tr[1]/td[6]"))));
        System.out.println("came after this step");
        Thread.sleep(5000);
      getDriver().findElement(By.xpath("//table/tbody/tr[1]/td[6]//button")).click();

      getDriver().findElement(By.xpath("//span[text()='Subscribe']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//legend[text()='Frequency']")));
        System.out.println("i am here bfr clickable");
        wait.until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.xpath("//label[@for='daily']/input"))));
        System.out.println("i am here after clickable");
        WebElement daily = getDriver().findElement(By.xpath("//label[@for='daily']/input"));
      getDriver().executeScript("arguments[0].click();",daily);
      getDriver().findElement(By.xpath("//span[text()='Save']")).click();
        Thread.sleep(3000);
        String message = getDriver().findElement(By.xpath("//span[contains(@class,'toastMessage')]")).getText();
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

   @Test(dependsOnMethods = {"editDashboard"}) //retryAnalyzer = sprint2.week1.retry.RetryFailedCases.class,
    public void deleteDashboard() {
       getDriver().findElement(By.xpath("//input[@placeholder='Search recent dashboards...']")).sendKeys("Salesforce Automation by");
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(50));
        wait.until(ExpectedConditions.stalenessOf(getDriver().findElement(By.xpath("//tr[@class='slds-hint-parent']"))));
       getDriver().findElement(By.xpath("//table/tbody/tr[1]/td[6]//button")).click();

       getDriver().findElement(By.xpath("//span[text()='Delete']")).click();

       getDriver().findElement(By.xpath("//button[@title='Delete']")).click();
        String message = getDriver().findElement(By.xpath("//span[contains(@class,'toastMessage')]")).getText();
        System.out.println(message);

        Assert.assertEquals("Dashboard was deleted.",message);


    }

    @Test() //retryAnalyzer = sprint2.week1.retry.RetryFailedCases.class
    public void sortDashboardName() throws InterruptedException {
        List<String> strList = new ArrayList<>(); List<String> strListAfterSort = new ArrayList<>();
        List<WebElement> dNamesBfrSorting = getDriver().findElements(By.xpath("//th[@data-label='Dashboard Name']//a"));
        for(WebElement d : dNamesBfrSorting){
            strList.add(d.getText());
        }
        System.out.println("strList --> "+strList);
        Collections.sort(strList);
        System.out.println("strList after sorting--> "+strList);
        Thread.sleep(5000);
        getDriver().findElement(By.xpath("//th[@aria-label='Dashboard Name']//a")).click();
        Thread.sleep(5000);
        for(WebElement e: getDriver().findElements(By.xpath("//th[@data-label='Dashboard Name']//a")) )   {
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
