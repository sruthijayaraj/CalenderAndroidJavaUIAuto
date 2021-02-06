package stepdefinitions;

import driver.manager.DriverManager;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import pages.LandingPage;
import pages.MeetingCreatePage;

import java.net.MalformedURLException;
import java.text.ParseException;

import static Utilities.GenerateScreenshot.embedScreenshotToReport;

public class StepsDef {

    Scenario scenario;
    DriverManager driverManager;
    AppiumDriver appiumDriver;
    WebDriverWait wait;
    MeetingCreatePage meetingCreatePage;

    @Given("^I have launched the Calendar App$")
    public void launchApp() throws MalformedURLException {
        driverManager = new DriverManager();
        appiumDriver= driverManager.setUpCapabilities();
        System.out.println("Test");

    }


    @When("^It is not a (.*)$")
    public void getWeatherForNextDatesUsingPostcode(String day){
        System.out.println(day);
    }


    @And("^Meeting is not repeated on successive days$")
    public void meetingIsNotRepeatedOnSuccessiveDays(){
        meetingCreatePage.setMeetingsOnNonSuccessiveDays();
    }


    @Then("^I want to book a meeting with the title (.*)$")
    public void searchDataWithTempBetween(String title) throws ParseException {
        LandingPage landingPage = new LandingPage(appiumDriver);
        landingPage.navigateIntroduction();
        HomePage homePage = new HomePage(appiumDriver);
        homePage.createEvent();
        meetingCreatePage = new MeetingCreatePage(appiumDriver);
        meetingCreatePage.createMeeting( title);
    }
    @And("^set meeting duration as (.*) in the evening$")
    public void setMeetingDUration(String time) throws ParseException {
        String[] hrAndMin= time.split("/");
        meetingCreatePage.setMeetingTime(Integer.parseInt(hrAndMin[0]),Integer.parseInt(hrAndMin[1]));
    }

    @And("^I invite (.*) of people$")
    public void invitePeople(String title)  {
        meetingCreatePage.invitePeople(title);
    }


    @And("^I save the meeting$")
    public void saveMeeting()  {
        meetingCreatePage.saveMeeting();
    }

    @Then("^I Check if the meeting is created as expected$")
    public void verifyIfMeetingIsCreated() throws ParseException {
        meetingCreatePage.VerifyMeeting();
    }

    @After
    public void TearDown(Scenario scenario){
        if(scenario.isFailed()){
            embedScreenshotToReport(scenario, appiumDriver);
        }
    }

}
