package com.inferenceCloud.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

   

    public static WebDriver setDriver(String  browser) {
        if(browser.equalsIgnoreCase("chrome")){
            tlDriver.set(new ChromeDriver());
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
        if(tlDriver.get() != null) {
            tlDriver.get().quit();
            tlDriver.remove();

        }
    }
    
}
