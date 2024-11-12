# PlayPowerAssessment
This Repository includes the test Assessment Code- PlayPower
# QA Task - Web Application Testing
This project automates testing scenarios for a web application using Selenium WebDriver. The application is available at The Internet. The automated tests verify the functionality of various components of the application, providing a suite of tests that can run locally or in a CI/CD environment.

# Project Overview
This project aims to automate testing scenarios for web application components using Java and Selenium WebDriver. Key features include:
•	Automated tests for essential functionalities like adding/removing elements, handling alerts, and file uploads.
•	Verifications for various UI components, such as checkboxes, dropdowns, and hovers.
•	HTML report generation.
•	Compatibility with CI/CD for streamlined deployment.

# Prerequisites
Before running the tests, ensure the following are installed:
•	Java Development Kit (JDK): Version 8 or later
•	Apache Maven: For dependency management
•	Selenium WebDriver: Latest version
•	TestNG Plugin: Testing framework for structuring and running test cases

# Setup and Installation
1.	Clone the repository to your local machine using below repository link
git clone git clone https://github.com/yourusername/your-repository.git cd your-repository
2.	Then run the code in your machine using Jenkins / maven commends
3.	Remaining all the setups for this code has been done.
4.	After running the tests please look into the test output folder for reports and is named as test.html

# Test Scenarios
The following functionalities have been automated:
1.	Add/Remove Elements
o	Test adding/removing elements from the page.
o	Verify the count changes on 'Add Element' and 'Delete'.
2.	Checkboxes
o	Automate checking/unchecking checkboxes.
o	Verify that checkbox states change correctly.
3.	Dropdown
o	Select different options from the dropdown.
o	Verify that the selected option is displayed.
4.	Form Authentication
o	Test login functionality with credentials:
	Username: tomsmith
	Password: SuperSecretPassword!
o	Verify successful login and invalid credential error message.
5.	JavaScript Alerts
o	Accept and dismiss alerts.
o	Verify alert text before action.
6.	Inputs
o	Test that inputs accept numerical values.
o	Change input values and validate.

7.	Reports Generation
o	Generate HTML reports using TestNG (or other tools).
8.	File Upload
o	Automate file upload.
o	Verify that the uploaded file name appears on the page.
9.	Dynamic Loading
o	Wait for content to load dynamically.
o	Verify that content appears correctly after loading.
10.	Hovers
o	Perform hover actions on elements.
o	Verify correct information on hover.
11.	JavaScript Onload Event Error
o	Detect onload JavaScript errors.
o	Verify error message displays on the page.

# Bonus Functionalities
•	Test execution setup on CI/CD platforms (e.g., GitHub Actions).
•	Link to test report for CI/CD deployment.


# Running the Tests
To run the test open command line in project path then follow below steps
To run all tests locally, execute: mvn test
To run a specific test suite or group, use the following: mvn -Dtest=TestClassName test

# Generating Reports
HTML reports are generated automatically after test execution. The reports can be found in the below path  C: PlayPower\TestAsseesment\test-output\Reports 

# Dependencies and Configuration
This project uses the following main dependencies:
•	Java
•	Selenium WebDriver
•	WebDriver Manager
•	TestNG
•	Maven
•	ExtentReports
•	Apache POI
•	Log4j
Additional configurations (e.g., browser, base URL) should be set in the config.properties file.

