

# Mobile Native App Automation Framework :) - # appiumCucumberHaseeb


# Prerequisites

Before running the tests, make sure you have the following installed:

* Java JDK (version 8 or higher)
* Maven
* Git

# Getting Started

To get started, clone the repository from GitHub:
git clone https://github.com/your-username/api-automation-framework.git

# Running the Tests

To run the tests on Android regresion test, use the following command:
mvn clean test -Dcucumber.filter.tags=@regression  -DplatformName="Android" -Dudid="emulator-5554" 

To run the regression tests on IOS , use the following command:
 mvn clean test -Dcucumber.filter.tags=@regression  -DplatformName="ios" -Dudid="5253B513-3935-4B71-87A2-1DCF9D4B38C3"

To run the sanity tests, use the following command:
mvn clean test -Denv=jsonplaceholder -Dcucumber.filter.tags=@sanity
This will execute the Cucumber test runner and generate the test reports.

# Test Reports

After running the tests, the test reports will be generated in the output/report directory and allure-results directly. Open
currently we have reports 
* Cucumber extent reports
* Cucumber pdf reports
* Allure reports

Technologies/Tools used in building the framework
=================================================
- IntelliJ - IDE
- Appium - Mobile Automation library
- Maven - Build automation tool
- Java - Programming language
- Cucumber - BDD
- Gherkin - DSL
- TestNG - Test Management library
- Log4J - Logging framework
- Extent Reports - Reporting framework


If you encounter any issues or errors while using the framework, please refer to the Troubleshooting section in the
README.md file on the GitHub repository. If the issue persists, feel free to open an issue on the repository page.

# Contributions

Contributions to the framework are welcome! If you have any improvements, bug fixes, or new features to suggest, please
fork the repository, make your changes, and submit a pull request.

# Contact
If you have any questions or need further assistance, please feel free to reach out to the project maintainer:

* Name: [MOHAMMED HASEEB ALI KHAN]
* Email: [mdhaseebpes@gmail.com]
  Thank you for using the Social Network API Automation Framework! Happy testing!
