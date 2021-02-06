package Utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class GenerateScreenshot {
    public static void embedScreenshotToReport(io.cucumber.java.Scenario scenario, WebDriver driver){
        try {
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES.BYTES);
            scenario.attach(screenshot,"image/png","failed");
        }
        catch (Exception e) {
            System.out.println("An exception occurred while embedding screenshot to report" + e.getCause());
        }

    }
}
