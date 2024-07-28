package sprint3.assessment.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.time.Duration;

import static sprint3.assessment.util.fileUploadUsingRobotClass.fileUpload;

public class ServiceConsole  {

    private RemoteWebDriver driver;
    WebDriverWait wait;

    public ServiceConsole(RemoteWebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(this.driver, Duration.ofSeconds(30));

    }



    public ServiceConsole clickOnFilesFromDropDown() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='appNavItems']//a[@title='Files']"))));
        //wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[@title='Show Navigation Menu']"))));
        //driver.findElement(By.xpath("//div[@data-aura-class='navexAppNavMenu']//button[@title='Show Navigation Menu']")).click();
        //Thread.sleep(2000);
        //wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[@data-label='Files']")))); ////span[@class='slds-media__body']/span[text()='Files']
       // driver.findElement(By.xpath("//a[@data-label='Files']")).click();
       // System.out.println(driver.getTitle());
        return this;

    }

    public ServiceConsole uploadFiles(){
        driver.findElement(By.xpath("//a[@title='Upload Files']")).click();
        try {
            String usrDir = System.getProperty("user.dir");
            System.out.println("user dir "+usrDir);
            String filePath = usrDir+"/data/TxtToUpload.txt";
            System.out.println(usrDir+"filepath "+filePath);

            StringSelection selectFile = new StringSelection(usrDir+"\\data\\TxtToUpload.txt");
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selectFile,null);
            Robot robot = new Robot();
            robot.delay(1000);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);

            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);

            robot.delay(1000);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            /*String updMsg = driver.findElement(By.xpath("//button[@title='Close this window']/following::span[@aria-live='assertive']")).getText();
            System.out.println("uplMsg is "+updMsg);*/
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Done']")));
            driver.findElement(By.xpath("//span[text()='Done']")).click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@data-aura-class='forceActionsText']")));
            String updMsg = driver.findElement(By.xpath("//span[@data-aura-class='forceActionsText']")).getText();
            Assert.assertTrue(updMsg.contains("uploaded"),"File Not Uploaded");
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span[@data-aura-class='forceActionsText']")));


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    public ServiceConsole clickOnArrowForTheLatestModifiedItem(){
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//table[@role]/tbody/tr[1]/td[4]//a"))));
        driver.findElement(By.xpath("//table[@role]/tbody/tr[1]/td[4]//a")).click();
        //driver.findElement(By.xpath("//tr[1]/td[4]//span[text()='Show Actions']//ancestor::a")).click();
         return this;
    }

    public ServiceConsole clickOnPublicLink(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Public Link']")));
        driver.findElement(By.xpath("//a[@title='Public Link']")).click();
        return this;
    }

    public ServiceConsole clickOnCreateLink() throws InterruptedException, IOException, UnsupportedFlavorException {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[@title='Create Link']"))));
        driver.findElement(By.xpath("//button[@title='Create Link']")).click();
        driver.findElement(By.xpath("//span[text()='Create']")).click();
        System.out.println("input text "+driver.findElement(By.xpath("//input[@name='publicLinkURL']")).getText());
        driver.findElement(By.xpath("//button[@title='Copy Link']")).click();
        wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.xpath("//div[@aria-live='polite']//em")),"Link copied"));
        String link = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null).getTransferData(DataFlavor.stringFlavor);
        System.out.println("Copied link is "+link);
        driver.findElement(By.xpath("//button[@title='Close this window']")).click();


        return this;


    }

    public ServiceConsole selectDownloadOption(){
        driver.findElement(By.xpath("//a[@title='Download']")).click();
        return this;
    }

    public ServiceConsole clickOnShare(){
        driver.findElement(By.xpath("//a[@title='Share']")).click();
        return this;
    }

    public ServiceConsole enterDetailsInSharePopUp(){
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@title='Search People']"))));
        driver.findElement(By.xpath("//input[@title='Search People']")).click();
        driver.findElement(By.xpath("//div[@title='Bootcamp Testleaf']")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("ul[class*='has-error'] li"))));
        String errMsg = driver.findElement(By.cssSelector("ul[class*='has-error'] li")).getText();
        Assert.assertTrue(errMsg.contains("Can't share"),"Error Message is not available");


        return this;
    }

    public ServiceConsole removeBootcampTestleafUser(){

        driver.findElement(By.xpath("//span[@class='pillText']/following::span[@class='deleteIcon']")).click();
        return this;
    }

    public ServiceConsole enterAnotherUserForSharing(){

        driver.findElement(By.xpath("//input[@title='Search People']")).click();
        driver.findElement(By.xpath("//input[@title='Search People']")).sendKeys("Integration User");
        driver.findElement(By.xpath("//div[@title='Bootcamp Testleaf']")).click();
        driver.findElement(By.xpath("textarea[placeholder*='Add a message']")).sendKeys("Bootcamp Testleaf_Shrinidhi");
        driver.findElement(By.xpath("//span[text()='Share']")).click();
        String msg = driver.findElement(By.xpath("//span[contains(@class,'toastMessage')]")).getText();
        Assert.assertTrue(msg.contains("Intergration User")&&msg.contains("TxtToUpload"),"Msg does not contain the username and filename");
        return this;
    }

}

