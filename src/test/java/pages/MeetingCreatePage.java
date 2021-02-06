package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

public class MeetingCreatePage extends BasePage{



    public By meetingLabel= By.id("com.google.android.calendar:id/input");
    public By MeetingEventButton= By.id("com.google.android.calendar:id/speed_dial_icon");
    public By startDate =By.id("com.google.android.calendar:id/start_date");
    public By startTime = By.id("com.google.android.calendar:id/start_time");
    public By endTimelocator = By.id("com.google.android.calendar:id/end_time");
    public By moreOption = By.id("com.google.android.calendar:id/more_options");
    public By toggleKeyboard = By.id("android:id/toggle_mode");
    public By endhourLocatorInDial = By.id("android:id/input_hour");
    public By timeSetterOkButton = By.id("android:id/button1");
    public By pm_locator = By.id("android:id/text1");
    public By endminLocatorInDial = By.id("android:id/input_minute");
    public By invitePeopleLocator = By.id("com.google.android.calendar:id/guest_input");
    public By doesNotRepeatLocator = By.xpath("//*[@text='Does not repeat']");
    public By customLocator = By.xpath("//*[@text='Custom…']");

    public MeetingCreatePage(AppiumDriver driver){
        super(driver);

    }


    public void createMeeting(String title) throws ParseException {
        driver.findElement(meetingLabel).sendKeys("Recurring-Team Catch Up");
    }


    public void setMeetingTime(int hr, int min) throws ParseException {
        String timeVal = driver.findElement(startTime).getAttribute("Text");
        System.out.println(" time is "+timeVal);

        String endTime =   getMeetingEndTime(timeVal,hr,min);

        setMeetingEndTime(endTime);
    }

    public void setMeetingsOnNonSuccessiveDays(){
        driver.findElement(moreOption).click();
        driver.findElement(doesNotRepeatLocator).click();
        setRecurringMeetingOnNonSuccessiveDays();
    }

    public void invitePeople(String people){

        inviteParticipants(people);
    }

    public void saveMeeting(){
        waitForElement(By.id("com.google.android.calendar:id/save"));
        driver.findElement(By.id("com.google.android.calendar:id/save")).click();
    }
    public void VerifyMeeting() throws ParseException {

        int MeetngsCreatedCount=0;
        waitForElement(By.className("android.view.View"));
        List<MobileElement> textViewElements= driver.findElementsByClassName("android.view.View");
        for (MobileElement ele: textViewElements){
            String tagName = ele.getAttribute("content-desc");
            if((!(tagName ==null))&&tagName.contains("Recurring-Team"))
            {   System.out.println(tagName);
                MeetngsCreatedCount++;
            }}

        Assert.assertTrue(MeetngsCreatedCount>0);

    }

    private void setMeetingEndTime(String endTime) {
        driver.findElement(endTimelocator).click();
        driver.findElement(toggleKeyboard).click();
        String[] timeSeparated = endTime.split(":");
        String hourToEnter = timeSeparated[0].toString();
        String[] secondHalf = timeSeparated[1].toString().split(" ");
        String mmTOEnter =secondHalf[0];

        driver.findElement(endhourLocatorInDial).clear();
        driver.findElement(endhourLocatorInDial).sendKeys(hourToEnter);
        driver.findElement(endminLocatorInDial).clear();
        driver.findElement(endminLocatorInDial).sendKeys(mmTOEnter);
        driver.findElement(timeSetterOkButton).click();
    }

    private void inviteParticipants(String people) {
        driver.findElement(invitePeopleLocator).click();
        driver.findElement(invitePeopleLocator).sendKeys(people);
        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER)); //enter
        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.BACK));// click on back button to close android keyboard
    }

    private void setRecurringMeetingOnNonSuccessiveDays() {
        driver.findElement(customLocator).click();
        LocalDate ldate = LocalDate.now();
        LocalDate secondDate= ldate.plusDays(2);
        LocalDate thirdDay = secondDate.plusDays(2);
        System.out.println(ldate.getDayOfWeek()+", "+ldate.getMonth()+" "+ldate.getDayOfMonth()+", "+ldate.getYear());
        String locatorOne = String.format("//*[@text='%s']",secondDate.getDayOfWeek().toString().substring(0,3));
        String locatorTwo = String.format("//*[@text='%s']",thirdDay.getDayOfWeek().toString().substring(0,3));
        System.out.println(locatorOne);
        waitForElement(By.xpath(locatorOne));
        driver.findElement(By.xpath(locatorOne)).click();
        waitForElement(By.xpath(locatorTwo));
        driver.findElement(By.xpath(locatorTwo)).click();
        driver.findElement(By.id("com.google.android.calendar:id/done")).click();
    }

    private String getMeetingEndTime(String timeVal, int hour, int min) throws ParseException {

        DateFormat formatter = new SimpleDateFormat("H:mm a");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(formatter.parse(timeVal));
        calendar.add(Calendar.HOUR,hour);
        calendar.add(Calendar.MINUTE,min);
        System.out.println(calendar.getTime());
        System.out.println( formatter.format(calendar.getTime()).toString());
        return formatter.format(calendar.getTime()).toString();
    }

    public void waitForElement(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

}
