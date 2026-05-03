package com.automation.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DriverFactory {

    private static final Logger log = LogManager.getLogger(DriverFactory.class);
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void initDriver() {

        String browser = ConfigReader.get("browser");
        String headless = ConfigReader.get("headless");
        String runMode = ConfigReader.get("runMode");

        log.info("Run Mode: " + runMode);
        log.info("Browser: " + browser);

        WebDriver webDriver = null;

        try {

            if (browser.equalsIgnoreCase("chrome")) {

                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");
                options.addArguments("--disable-notifications");

                if (headless.equalsIgnoreCase("true")) {
                    options.addArguments("--headless=new");
                }

                if (runMode.equalsIgnoreCase("remote")) {

                    log.info("Starting RemoteWebDriver (Grid)");

                    webDriver = new RemoteWebDriver(
                            new java.net.URI(ConfigReader.get("gridUrl")).toURL(),
                            options
                    );

                } else {

                    log.info("Starting Local ChromeDriver");

                    WebDriverManager.chromedriver().setup();
                    webDriver = new ChromeDriver(options);
                }
            }

            else if (browser.equalsIgnoreCase("firefox")) {

                FirefoxOptions options = new FirefoxOptions();

                if (headless.equalsIgnoreCase("true")) {
                    options.addArguments("--headless");
                }

                if (runMode.equalsIgnoreCase("remote")) {

                    webDriver = new RemoteWebDriver(
                            new java.net.URI(ConfigReader.get("gridUrl")).toURL(),
                            options
                    );

                } else {

                    WebDriverManager.firefoxdriver().setup();
                    webDriver = new FirefoxDriver(options);
                }
            }

            else if (browser.equalsIgnoreCase("edge")) {

                EdgeOptions options = new EdgeOptions();

                if (runMode.equalsIgnoreCase("remote")) {

                    webDriver = new RemoteWebDriver(
                            new java.net.URI(ConfigReader.get("gridUrl")).toURL(),
                            options
                    );

                } else {

                    WebDriverManager.edgedriver().setup();
                    webDriver = new EdgeDriver(options);
                }
            }

            else {
                throw new RuntimeException("Invalid browser: " + browser);
            }

        } catch (Exception e) {
            throw new RuntimeException("Driver initialization failed: " + e.getMessage());
        }

        driver.set(webDriver);

        log.info("Driver initialized successfully");

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
            log.info("Closing browser");
            driver.get().quit();
            driver.remove();
        }
    }
}