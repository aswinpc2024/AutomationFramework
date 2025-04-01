#Prosper Automation Framework
#Overview
#This project is an automation framework for testing the Prosper application. It utilizes TestNG for test management and ExtentReports for reporting.

#Test Structure
BaseTest.java: Contains setup and teardown methods for initializing the WebDriver and managing ExtentReports.
LoginTest.java: Tests for verifying login functionality, including OTP-based login and password validation.
SignUpPageTest.java: Tests for verifying the sign-up functionality using email and mobile number.
Configuration
Ensure that the core.autocrlf setting in Git is configured to handle line endings correctly:
git config --global core.autocrlf true
Running Tests
Ensure all necessary dependencies are installed and up to date (e.g., Maven dependencies).
Run the test suite using your preferred method (e.g., through an IDE or command line).
Reporting
ExtentReports are generated for each test run, providing detailed insights into test execution and results.

Author
Aswin Chandran PC

LinkedIn
GitHub
