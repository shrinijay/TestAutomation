package sprint2.day2.AssessmentTestCase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class EndToEndFlow {

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
            //3. Click view All
            driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
            driver.findElement(By.xpath("//button[@aria-label='View All Applications']")).click();
            //4. Click Sales from App Launcher
            driver.findElement(By.xpath("//p[text()='Sales']")).click();
            //5. Set goals
            driver.findElement(By.xpath("//button[@aria-label='Edit my goals']")).click(); //sometimes getting this error - org.openqa.selenium.ElementClickInterceptedException: element click intercepted: Element <button lwc-485vfn4rmof="" class="slds-button slds-button_icon slds-button_icon-bare" type="button" aria-label="Edit my goals" part="button button-icon" aria-describedby="salesforce-lightning-tooltip-bubble_05eb18df-4437-9006-13b4-8007593f9cf4" title="">...</button> is not clickable at point (936, 575). Other element would receive the click: <lightning-spinner lwc-31eehllji7v="" size="medium" class="slds-spinner_container" lwc-47dd688sbro-host=""></lightning-spinner>

            WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[text()='Set Goals']"))));
            Random rand = new Random();
            int randNum1 = rand.nextInt((3000-1000)+1)+1000;
            System.out.println("randNum1 "+randNum1);
            driver.findElement(By.xpath("//input[@placeholder='Number of meetings']")).clear();
            driver.findElement(By.xpath("//input[@placeholder='Number of meetings']")).sendKeys(String.valueOf(randNum1));


            int randNum2 = rand.nextInt((6000-3500)+1)+3500;
            System.out.println("randNum2 "+randNum2);
            driver.findElement(By.xpath("//input[@placeholder='Number of calls']")).clear();
            driver.findElement(By.xpath("//input[@placeholder='Number of calls']")).sendKeys(String.valueOf(randNum2));
            int randNum3 = rand.nextInt((9000-6500)+1)+6500;
            System.out.println("randNum3 "+randNum3);
            driver.findElement(By.xpath("//input[@placeholder='Number of emails']")).clear();
            driver.findElement(By.xpath("//input[@placeholder='Number of emails']")).sendKeys(String.valueOf(randNum3));
            //get the value from time interval and check if it is monthly else select monthly
            String timeValue = driver.findElement(By.xpath("//button[@name='timeInterval']/span")).getText();
            //String timeValue = driver.findElement(By.name("timeInterval")).getText(); //--> this is not working
            System.out.println("Timevalue ----> "+timeValue);
            if(timeValue.contains("Monthly"))
            {
                System.out.println("Monthly is selected");
            }
            else{
                System.out.println("Other than monthly is selected, choosing monthly one");
            }
            Actions act = new Actions(driver);
            act.scrollToElement(driver.findElement(By.xpath("//button[@title='Save']"))).perform();
            driver.findElement(By.xpath("//button[@title='Save']")).click();
            Thread.sleep(5000);
            //7. Navigate to Dashboard tab
            WebElement ele = driver.findElement(By.xpath("//a[@title='Dashboards']"));
            driver.executeScript("arguments[0].click();",ele);
            //8. Click on New Dashboard
            WebElement newDB = driver.findElement(By.xpath("//div[@title='New Dashboard']"));
            wait.until(ExpectedConditions.visibilityOf(newDB));
            newDB.click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//iframe")));
            ////9. Enter the Dashboard name as "YourName_Workout"
            driver.switchTo().frame(0);
            driver.findElement(By.id("dashboardNameInput")).sendKeys("Shrinidhi_Workout");
            //11. Click on Create
            driver.findElement(By.id("submitBtn")).click();
            driver.switchTo().defaultContent();
            //12. Click on Done
            Thread.sleep(3000); //this is to be changed
            driver.switchTo().frame(0);
            driver.findElement(By.xpath("//button[text()='Done']")).click();
            driver.switchTo().defaultContent();
            //13. Click on Dashboard tab
            driver.executeScript("arguments[0].click();",ele);
            //14. Verify the Dashboard is Created
            List<WebElement> dashboardNames;
            dashboardNames = driver.findElements(By.xpath("//th[@data-label='Dashboard Name']//a"));
            int j=0;
            for(int i=0;i<dashboardNames.size();i++){
                if(dashboardNames.get(i).getText().equals("Shrinidhi_Workout"))
                {
                    /*System.out.println("Dashboard Created successfully");
                    break;*/
                    j=1;
                }

            }
            if(j==1)
            {
                System.out.println("Dashboard Created successfully");
            }
            else{
                System.out.println("Dashboard not created successfully");
            }
            Thread.sleep(3000);
            String buttonTable = "//a[text()="+"'Shrinidhi_Workout'"+"]/ancestor::th/following-sibling::td[6]//button";
            driver.findElement(By.xpath(buttonTable)).click();
            driver.findElement(By.xpath("//span[text()='Subscribe']")).click();

            //14. Select Frequency as "Daily"
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//label[@for='daily']/input"))));
            System.out.println("i am here after clickable");

            //driver.findElement(By.xpath("//label[@for='daily']/input")).click(); //org.openqa.selenium.ElementClickInterceptedException: element click intercepted: Element <input aria-describedby="" name="radio" id="daily" type="radio" data-aura-rendered-by="1447:0" class="uiInput uiInputRadio uiInput--default uiInput--radio" data-aura-class="uiInput uiInputRadio uiInput--default uiInput--radio" data-interactive-lib-uid="4"> is not clickable at point (372, 276). Other element would receive the click: <div class="slds-radio--button-group" data-aura-rendered-by="1302:0">...</div> -> this is the known issue in chrome - for this js exec to beused..in other browsers it will work.

            WebElement daily = driver.findElement(By.xpath("//label[@for='daily']/input"));
            driver.executeScript("arguments[0].click();",daily);
           //15. Time as 10:00 AM
            Select timedd = new Select(driver.findElement(By.id("time")));
            timedd.selectByVisibleText("10:00 am");
            //16. Click on Save
            driver.findElement(By.xpath("//span[text()='Save']")).click();
           // 17. Verify "You started Dashboard Subscription" message displayed or not
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
           // 18. Click on Dashboards tab
            driver.executeScript("arguments[0].click();",ele);
           // 19. Verify the newly created Dashboard is available -> already verified
           // 20. Click on dropdown for the item
            driver.findElement(By.xpath(buttonTable)).click();
           // 21. Select Delete
            driver.findElement(By.xpath("//span[text()='Delete']")).click();
          //  22. Confirm the Delete
            driver.findElement(By.xpath("//button[@title='Delete']")).click();
            String delmsg = driver.findElement(By.xpath("//span[contains(@class,'toastMessage')]")).getText();
            System.out.println(delmsg);
            if(delmsg.equals("Dashboard was deleted."))
            {
                System.out.println("Dashboard deleted");
            }
            else{
                System.out.println("Not deleted");
            }
          //  23. Verify the item is not available under Private Dashboard folder
            dashboardNames = driver.findElements(By.xpath("//th[@data-label='Dashboard Name']//a"));

            for(int i=0;i<dashboardNames.size();i++){
                if(!dashboardNames.get(i).getText().equals("Shrinidhi_Workout"))
                {
                    /*System.out.println("Dashboard Created successfully");
                    break;*/
                    j=1;
                }

            }
            if(j==1)
            {
                System.out.println("Deleted Dashboard is not listed");
            }
            else{
                System.out.println("Deleted Dashboard is listed - check once");
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
