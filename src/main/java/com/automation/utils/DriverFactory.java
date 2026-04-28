package com.automation.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class DriverFactory {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void initDriver() {

        String browser = ConfigReader.get("browser");
        String headless = ConfigReader.get("headless");

        WebDriver webDriver = null;

        if (browser.equalsIgnoreCase("chrome")) {

            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();

            options.addArguments("--start-maximized");
            options.addArguments("--disable-notifications");

            if (headless.equalsIgnoreCase("true")) {
                options.addArguments("--headless=new");
            }

            webDriver = new ChromeDriver(options);
        }

        else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            webDriver = new FirefoxDriver();
        }

        else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            webDriver = new EdgeDriver();
        }

        driver.set(webDriver);

        getDriver().manage().timeouts().implicitlyWait(
                Duration.ofSeconds(
                        Integer.parseInt(ConfigReader.get("implicitWait"))
                )
        );
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}