package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class BasePage {

    public AppiumDriver driver;
    public WebDriverWait wait;

    public BasePage(AppiumDriver driver){
         this.driver = driver;

         this.wait = new WebDriverWait(driver, 10);
     }

    public void implicitlyWait() {  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void waitForElement(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
}
