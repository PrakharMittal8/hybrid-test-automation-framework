# Hybrid Test Automation Framework

A reusable automation framework built using **Selenium + TestNG + REST Assured + Cucumber BDD** that supports UI testing, API testing, data-driven testing, reporting, retry mechanism, and multi-environment execution.

This framework is designed as a **template-based architecture** so that any new project can plug in its own application URLs, locators, APIs, and test data with minimal effort.

---

# Features

- UI Automation using Selenium WebDriver
- API Automation using REST Assured
- BDD Automation using Cucumber
- Data Driven Testing using Excel + TestNG DataProvider
- Retry Mechanism for flaky tests
- Screenshot capture on failures
- Extent Report integration
- Cucumber Report integration
- Multi-environment execution support
- Reusable Page Object Model architecture

---

# Tech Stack

- Java 17
- Selenium WebDriver
- TestNG
- Maven
- REST Assured
- Cucumber BDD
- Apache POI
- Extent Reports
- Log4j2
- WebDriverManager

---

# Project Structure

```bash
seleniumframework
│   mvnw
│   mvnw.cmd
│   pom.xml
│   README.md
│   testng.xml
│
└── src
    ├── main
    │   └── java
    │       └── com.automation
    │
    │           ├── api
    │           │   ├── CartAPI.java
    │           │   ├── LoginAPI.java
    │           │   └── ProductAPI.java
    │
    │           ├── pages
    │           │   ├── BasePage.java
    │           │   ├── LoginPage.java
    │           │   ├── ProductPage.java
    │           │   └── CartPage.java
    │
    │           └── utils
    │               ├── ApiUtils.java
    │               ├── ConfigReader.java
    │               ├── DriverFactory.java
    │               ├── ExcelUtil.java
    │               ├── ExtentManager.java
    │               ├── ScreenshotUtil.java
    │               └── WaitUtils.java
    │
    └── test
        ├── java
        │   └── com.automation
        │
        │       ├── base
        │       │   ├── BaseTest.java
        │       │   └── ApiBase.java
        │
        │       ├── hooks
        │       │   └── Hooks.java
        │
        │       ├── listeners
        │       │   └── TestListener.java
        │
        │       ├── retry
        │       │   └── RetryAnalyzer.java
        │
        │       ├── stepdefinitions
        │       │   └── AddToCartSteps.java
        │
        │       └── tests
        │           ├── AddToCartTest.java
        │           ├── ApiE2ETest.java
        │           ├── DataDrivenOrderTest.java
        │           └── TestRunner.java
        │
        └── resources
            ├── config-dev.properties
            ├── config-qa.properties
            ├── config-prod.properties
            ├── log4j2.xml
            │
            ├── features
            │   └── addToCart.feature
            │
            └── testdata
                └── LoginTestData.xlsx
```

---

# Framework Layers

---

## UI Automation Layer

Built using Selenium WebDriver + TestNG + Page Object Model

### BasePage.java
Contains reusable methods:

- click()
- type()
- getText()
- getElement()

### LoginPage.java
Handles login functionality

### ProductPage.java
Handles:

- Product selection
- Add to cart flow
- Cart navigation
- Spinner handling

### CartPage.java
Handles cart validation

---

# API Automation Layer

Built using REST Assured

### LoginAPI.java

- Performs login API call
- Generates auth token

### ProductAPI.java

- Fetches product list
- Adds product to cart

### CartAPI.java

- Validates cart products

---

# API End-to-End Flow

`ApiE2ETest.java`

Complete API flow:

- Login
- Fetch products
- Identify product dynamically
- Add product to cart
- Validate cart contents

---

# BDD Automation Layer

Built using Cucumber

---

## Feature File

`addToCart.feature`

```gherkin
Feature: Add product to cart

Scenario: Verify user can add product to cart
Given User launches application
When User logs in
And User adds product to cart
And User navigates to cart page
Then Product should be visible in cart
```

---

## Step Definitions

`AddToCartSteps.java`

Maps feature steps to reusable page methods.

---

## Test Runner

`TestRunner.java`

Executes Cucumber feature files.

---

# Data Driven Testing

Built using:

- Apache POI
- TestNG DataProvider

### Excel File

`LoginTestData.xlsx`

Contains:

- Username
- Password
- Product Name

### DataDrivenOrderTest.java

Reads data from Excel and executes tests dynamically.

---

# Multi Environment Support

Framework supports:

- Dev
- QA
- Production

Configuration files:

- config-dev.properties
- config-qa.properties
- config-prod.properties

Run specific environment:

```bash
mvn clean test -Denv=qa
```

```bash
mvn clean test -Denv=dev
```

```bash
mvn clean test -Denv=prod
```

---

# Reporting

---

## Extent Report

Generated at:

```bash
target/ExtentReport.html
```

Includes:

- Pass/Fail logs
- Screenshots
- Failure details

---

## Cucumber Report

Generated at:

```bash
target/cucumber-report.html
```

---

## TestNG Reports

Generated at:

```bash
target/surefire-reports
```

---

# Retry Mechanism

Implemented using:

`RetryAnalyzer.java`

Automatically retries failed flaky tests.

---

# Screenshot Handling

Implemented using:

`ScreenshotUtil.java`

Captures screenshots for failed UI tests.

For API tests:

- Screenshot capture is skipped if no browser session exists

---

# Wait Handling

Implemented using:

`WaitUtils.java`

Supports:

- Explicit waits
- Element visibility waits
- Clickable waits
- Spinner invisibility waits

---

# Logging

Implemented using:

- Log4j2

Configuration:

```bash
log4j2.xml
```

---

# SOLID Principles Applied

### Single Responsibility Principle
Separate responsibilities for:

- Pages
- APIs
- Utilities
- Tests

---

### Open Closed Principle
New test cases, APIs, pages, and environments can be added without modifying existing code.

---

### Liskov Substitution Principle
Page classes extend BasePage without breaking functionality.

---

### Interface Segregation Principle
Implemented using:

- ITestListener
- IRetryAnalyzer

---

### Dependency Inversion Principle
Tests depend on reusable utility classes instead of directly creating drivers/configs.

Example:

```java
DriverFactory.getDriver()
```

---

# Design Patterns Used

### Page Object Model
Used for UI automation scalability

### Factory Pattern
Used in DriverFactory

### Singleton Pattern
Used in ExtentManager

### Data Provider Pattern
Used for Excel-driven execution

---

# How to Run Tests

Run all tests:

```bash
mvn clean test
```

Run specific environment:

```bash
mvn clean test -Denv=qa
```

Run Cucumber tests:

```bash
mvn test
```

---

# Reusability

This framework can be reused for any new project by replacing:

- Application URLs
- Locators
- API endpoints
- Test data
- Feature files

Core framework architecture remains reusable.

-------------------------------------------------------------------------------
## Prerequisites & Setup

Before running this framework, complete the below setup:

### 1. Install Required Tools
- Java 17
- Maven
- Chrome / Firefox / Edge browser
- IDE (IntelliJ / Eclipse / VS Code)

---

### 2. Clone Repository

```bash
git clone https://github.com/PrakharMittal8/hybrid-test-automation-framework.git
cd hybrid-test-automation-framework
```

---

### 3. Create Test Application Account

This framework uses Rahul Shetty Academy E-commerce application:

https://rahulshettyacademy.com/client

Create your own account manually on the application.

Use your own:
- Email ID
- Password

Do NOT rely on my credentials.

---

### 4. Update Credentials in Properties File

Update your credentials inside:

```plaintext
src/test/resources/config-qa.properties
src/test/resources/config-dev.properties
src/test/resources/config-prod.properties
```

Example:

```properties
username=your_email@gmail.com
password=your_password
```

---

### 5. Update Excel Test Data

Update your credentials inside:

```plaintext
src/test/resources/testdata/LoginTestData.xlsx
```

Replace:
- username
- password

with your own credentials.

You can also modify product names for data-driven execution.

---

### 6. Run Framework

If Maven Wrapper is unavailable in GitHub upload:

```bash
mvn clean test
```

Run specific environment:

```bash
mvn clean test -Denv=qa
mvn clean test -Denv=dev
mvn clean test -Denv=prod

-------------------------------------------------------------------------------


# Author

Prakhar Mittal
