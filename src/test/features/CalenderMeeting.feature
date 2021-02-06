Feature: Create a recurring meeting using Native Android/iOS Calendar App.

	Scenario: Create a new recurring(3 days a week) meeting, and make sure it doesn't repeat on successive days
		Given I have launched the Calendar App
		Then I want to book a meeting with the title Recurring-Team Catch Up
		And set meeting duration as 1/30 in the evening
		And Meeting is not repeated on successive days
		And I invite one@1.com; 2@2.com of people
		And I save the meeting
		Then I Check if the meeting is created as expected



