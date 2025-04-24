# 🔍 Selenium Test Automation Framework (Parallel + ExtentReports + Excel + Grid)

## 📌 Overview

This is a TestNG-based Selenium Automation Framework designed for executing web tests with rich reporting, data-driven execution, and support for parallel testing on multiple browsers (Chrome, Firefox, Edge) using Selenium Grid. It’s built with extensibility, readability, and modularity in mind.

---

## ✅ Features

- ✅ **Data-Driven Testing** using Excel
- ✅ **Cross-Browser Support** – Chrome, Firefox, Edge
- ✅ **Parallel Execution** using TestNG
- ✅ **Remote Execution** via Selenium Grid (`RemoteWebDriver`)
- ✅ **Beautiful ExtentReports Integration**
- ✅ **Custom Verification Utility** for enhanced logging
- ✅ **Test Listeners** for ExtentReport lifecycle handling
- ✅ **Reusable Driver Factory** with thread-safety (`ThreadLocal<WebDriver>`)
- ✅ **Dynamic Test Naming** in Reports based on Excel Test Case ID

---


## 📦 Project Type

This is a **Maven-based Java Automation Framework** structured for cross-browser testing using Selenium Grid. It leverages modular packages like `listeners`, `utilities`, and `driver factory`, making it easy to scale and maintain.


---

## 🧪 Test Example

```java
@Test(dataProvider = "excelData")
public void runTest(HashMap<String, String> data) {
    DriverFactory.setDriver(data.get("Browser")); // Browser from Excel
    WebDriver driver = DriverFactory.getDriver();
    
    driver.get(data.get("URL"));

    VerificationUtils VP = new VerificationUtils(ExtentTestManager.getTest());
    VP.verifyEquals(data.get("Username"), data.get("Expected"), "Verifying Username");
    
    DriverFactory.quitDriver();
}
