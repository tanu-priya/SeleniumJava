package com.inferenceCloud.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public static WebDriver setDriver(String browser) {

        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless=new"); // IMPORTANT
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1920,1080");
            tlDriver.set(new ChromeDriver(options));
            // set chrome driver
        } else if (browser.equalsIgnoreCase("firefox")) {
            // set firefox driver
            tlDriver.set(new FirefoxDriver());
        }

        return getDriver();

    }

    public static WebDriver getDriver() {
        return tlDriver.get();
    }

    public static void unloadDriver() {
        if (tlDriver.get() != null) {
            tlDriver.get().quit();
            tlDriver.remove();

        }
    }

}
