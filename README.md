# 🧪 Selenium POC – Java 17 + TestNG + Allure Reports

![Java](https://img.shields.io/badge/Java-17-%23ED8B00?logo=java&logoColor=white)
![Selenium](https://img.shields.io/badge/Selenium-4.23.0-43B02A?logo=selenium&logoColor=white)
![TestNG](https://img.shields.io/badge/TestNG-7.10.0-brightgreen?logo=testng&logoColor=white)
![Allure](https://img.shields.io/badge/Allure-2.29.0-FF4088?logo=allure&logoColor=white)
![Build](https://img.shields.io/badge/Build-Maven-blue?logo=apachemaven&logoColor=white)

## 📘 Overview

This repository contains a **Selenium + TestNG automation framework** built in **Java 17**, ready for use with **Allure Reports** and **WebDriverManager**.

It provides:
- 🔧 A modular **Page Object Model (POM)** architecture  
- 🧩 Automatic driver management with **WebDriverManager**
- 🧱 Clear setup & teardown via **TestNG Base classes**
- 📊 Detailed **Allure HTML reports**
- 📸 **Automatic screenshots** and page source capture on failure
- 🌱 `.env` configuration for secure credentials (no secrets in code)

## 📂 Project Structure

```
selenium-poc/
├── pom.xml
├── testng.xml
├── .env
├── .gitignore
├── README.md
└── src/
    ├── main/java/com/refnando/poc/
    │   ├── base/
    │   │   ├── BasePage.java
    │   │   └── DriverManager.java
    │   ├── listeners/
    │   │   └── ScreenshotOnFailure.java
    │   ├── pages/
    │   │   └── LoginPage.java
    │   └── utils/
    │       └── EnvReader.java
    └── test/java/com/refnando/poc/tests/
        ├── base/
        │   └── BaseTest.java
        └── LoginTests.java
```

## ⚙️ Installation

### Prerequisites
- Java 17+
- Maven 3.8+
- Google Chrome installed (latest stable)
- Allure Commandline (optional for local HTML reports)

```bash
brew install allure
```

## 🚀 How to Run Tests

### 1. Define environment variables

You can configure login credentials using a **`.env`** file or system properties.

#### `.env` example:
```bash
BASE_URL=https://www.saucedemo.com/
TEST_USER=standard_user
TEST_PASS=secret_sauce
```

### 2. Execute the suite

Run all tests:
```bash
mvn clean test
```

Run in headless mode:
```bash
mvn clean test -Dheadless=true
```

Override credentials temporarily:
```bash
mvn clean test -DTEST_USER=locked_out_user -DTEST_PASS=secret_sauce
```

## 📊 Generate Allure Reports

After running the tests, generate the report with:

```bash
mvn allure:report
mvn allure:serve
```

The report will open automatically in your browser:
- ✅ Test status and execution timeline
- 📸 Screenshots and HTML source for failed tests
- 🔍 Step-by-step annotations and logs

## 🧩 Key Components

| File | Responsibility |
|------|----------------|
| **DriverManager.java** | Initializes, provides and quits WebDriver instances |
| **BasePage.java** | Common superclass for all Page Objects |
| **LoginPage.java** | Encapsulates login page actions |
| **BaseTest.java** | Handles test lifecycle (`@BeforeMethod` / `@AfterMethod`) |
| **ScreenshotOnFailure.java** | Attaches screenshot, page source and current URL on test failure |
| **EnvReader.java** | Loads environment variables and `.env` files safely |

## 🧠 Example Test

```java
@Test(description = "Validates successful login using secure credentials")
public void shouldLoginAndSeeInventory() {
    String baseUrl = EnvReader.get("BASE_URL");
    String username = EnvReader.get("TEST_USER");
    String password = EnvReader.get("TEST_PASS");

    DriverManager.getDriver().get(baseUrl);

    new LoginPage(DriverManager.getDriver())
        .typeUsername(username)
        .typePassword(password)
        .submit();

    Assert.assertTrue(
        DriverManager.getDriver().getCurrentUrl().contains("inventory.html"),
        "User has not been taken into inventory page."
    );
}
```

## 🧰 Useful Maven Commands

| Command | Description |
|----------|-------------|
| `mvn clean test` | Run all tests |
| `mvn clean test -Dheadless=true` | Run tests in headless mode |
| `mvn allure:report` | Generate HTML report |
| `mvn allure:serve` | Serve Allure report locally |
| `mvn dependency:tree` | Inspect dependencies |
| `mvn -DskipTests package` | Build without executing tests |

## 🧱 Future Enhancements
- Integrate **RetryAnalyzer** for flaky tests  
- Extend support to **API tests (RestAssured)**  
- Include **Dockerized execution** for CI/CD  
- Integrate **Concourse CI** and Allure publishing step  
- Add accessibility validation support (axe-selenium-java)

## 👤 Author

**Fernando Campos**  
_Quality Assurance Automation Engineer_  
🔗 [GitHub – refnando](https://github.com/refnando)

> *“Quality means doing it right when no one is looking.”* – Henry Ford
