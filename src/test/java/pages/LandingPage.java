package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class LandingPage extends BasePage{



    public String NxtPage="next page";
    public String GotItButton="Got it";
    public By gotitButton =By.id("com.google.android.calendar:id/done_button");


    public LandingPage(AppiumDriver driver){
        super(driver);
    }

    public void navigateIntroduction(){
        implicitlyWait();
        driver.findElementByAccessibilityId(NxtPage).click();
        driver.findElementByAccessibilityId(NxtPage).click();
        driver.findElementByAccessibilityId(NxtPage).click();
        waitForElement(gotitButton);
        driver.findElement(gotitButton).click();
    }



}
