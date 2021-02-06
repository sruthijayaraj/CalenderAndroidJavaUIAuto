package tests;

import driver.manager.DriverManager;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import pages.LandingPage;
import pages.MeetingCreatePage;

import java.net.MalformedURLException;
import java.text.ParseException;

public class Main {

    public static void main(String[] args) throws MalformedURLException, ParseException {
       DriverManager driverManager = new DriverManager();
       AppiumDriver appiumDriver= driverManager.setUpCapabilities();
        WebDriverWait wait;
        LandingPage landingPage = new LandingPage(appiumDriver);
        landingPage.navigateIntroduction();
        HomePage homePage = new HomePage(appiumDriver);
        homePage.createEvent();
        MeetingCreatePage meetingCreatePage = new MeetingCreatePage(appiumDriver);
      //  meetingCreatePage.clickOnStartDate();

    }
}
