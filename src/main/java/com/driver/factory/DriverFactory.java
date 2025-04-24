package com.driver.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Platform;

import java.net.URL;
import java.net.MalformedURLException;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private static final String SELENIUM_HUB_URL = "http://localhost:4444/wd/hub"; // Replace if needed

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public static void setDriver(String browserName) {
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setPlatform(Platform.ANY);

            switch (browserName.toLowerCase()) {
                case "chrome":
                    capabilities.setBrowserName("chrome");
                    break;
                case "firefox":
                    capabilities.setBrowserName("firefox");
                    break;
                case "edge":
                    capabilities.setBrowserName("MicrosoftEdge");
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browserName);
            }

            RemoteWebDriver remoteDriver = new RemoteWebDriver(new URL(SELENIUM_HUB_URL), capabilities);
            driverThreadLocal.set(remoteDriver);

        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Selenium Grid URL: " + SELENIUM_HUB_URL, e);
        }
    }

    public static void quitDriver() {
        if (driverThreadLocal.get() != null) {
            driverThreadLocal.get().quit();
            driverThreadLocal.remove();
        }
    }
}
