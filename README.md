# Selenium Automation Framework

A professional test automation framework built with Java, Selenium WebDriver, and TestNG.

## Technologies Used
- Java 11
- Selenium WebDriver 4.18.1
- TestNG 7.9.0
- Page Object Model (POM)
- ExtentReports
- WebDriverManager
- Maven

## Framework Structure
```
src/main/java
├── config/ — ConfigReader reads config.properties
├── pages/ — Page Object classes
└── utilities/ — ScreenshotUtil, ExtentReportManager

src/test/java
├── base/ — BaseTest with driver setup and teardown
└── tests/ — TestNG test classes

src/test/resources
├── config.properties — browser, URL, credentials
└── testng.xml — test suite configuration
```
## How to Run
1. Clone the repository
2. Open in IntelliJ IDEA
3. Update `config.properties` with your environment details
4. Right click `testng.xml` and click Run

## Features
- Page Object Model for maintainable code
- Automatic screenshot capture on test failure
- ExtentReports HTML report with timestamps
- Configurable browser and environment via properties file
- WebDriverManager for automatic ChromeDriver management