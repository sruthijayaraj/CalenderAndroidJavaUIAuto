package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class HomePage extends BasePage {




    public String createEventButton="Create new event and more";
    public By Reminder =By.id("com.google.android.calendar:id/speed_dial_reminder_container");
    public By CalenderEvent = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout[2]/android.widget.ImageView");

    public HomePage(AppiumDriver driver){
        super(driver);
    }

    public void createEvent(){
        implicitlyWait();
        driver.findElementByAccessibilityId(createEventButton).click();
        driver.findElement(CalenderEvent).click();

    }



}
