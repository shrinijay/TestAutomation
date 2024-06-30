package sprint3.assessment.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import sprint3.assessment.base.BaseClass;

public class Login  {

    private RemoteWebDriver driver;
    public Login(RemoteWebDriver driver) {

        this.driver = driver;
    }

    public Login enterUserName(String username){
       driver.findElement(By.id("username")).sendKeys(username);
       return this;
    }

    public Login enterPwd(String pwd){
      driver.findElement(By.id("password")).sendKeys(pwd);
      return this;
    }

    public HomePage clickLogInButton(){
        driver.findElement(By.id("Login")).click();
        return new HomePage(driver);
    }






}
