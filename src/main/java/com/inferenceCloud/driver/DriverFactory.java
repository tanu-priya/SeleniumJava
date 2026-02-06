package com.inferenceCloud.driver;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverFactory {

    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public static WebDriver setDriver(String browser) {
        System.out.println(">>>> RUN MODE = " + System.getProperty("runMode"));
        System.out.println(">>>> BROWSER = " + browser);
        String runMode = System.getProperty("runMode", "local");// local | grid

        try {
            if (browser.equalsIgnoreCase("chrome")) {
                // set chrome driver
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--window-size=1920,1080");
                tlDriver.set(new ChromeDriver(options));
                if (runMode.equalsIgnoreCase("grid")) {
                    // Jenkins / Docker
                    System.out.println(">>>> USING SELENIUM GRID");
                    tlDriver.set(new RemoteWebDriver(
                            new URL("http://selenium-hub:4444"), options));
                } else {
                    options.addArguments("--headless");
                    System.out.println(">>>> USING LOCAL CHROMEDRIVER");
                    // Local
                    tlDriver.set(new ChromeDriver(options));
                }

            } else if (browser.equalsIgnoreCase("firefox")) {
                // set firefox driver
                tlDriver.set(new FirefoxDriver());
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
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
