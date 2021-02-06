RestAssured API for Weather - with filter conditions

Installation (Pre-requisites)

* jdk1.8 or higher
* Maven
  *IntelliJ IDEA/jdk1.8 or higher
*Make sure Android simulator  is set up and running before executing the scripts 
  *Make sure Appium is started and running before executing the scripts.
The Appium capability used in this test is
{
"platformName": "Android",
"VERSION": "8.0.0",
"appPackage": "com.google.android.calendar",
"appActivity": "com.android.calendar.AllInOneActivity"
}
*Make sure emulator with the above onfiguration is up and running before the execution

### Features

Feature file includes scenario for creating a recurring meeting in Calendar

### How to run tests
*mvn  clean , mvn  install verify used for executing and generating reports.
*On  completion of execution ,  reports will be generated in folder
reports/html-reports/cucumber-html-reports/report-feature_1445543082.html?
target/cucumber-reports/cucumber.html?

Things to Improve:

* Considering AM PM for meeting requests
* Second scenario is not implemented due to lack of time (It is not a <non working day>)
* More edge case scenarios can be thought of and considered if more time was available




  
  