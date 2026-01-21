package com.inferenceCloud.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    static WebDriver driver;

    public static WebDriver getDriver(String browser) {
        setDriver(browser);
        return driver;
    }

    public static void setDriver(String  browser) {
        if(browser.equalsIgnoreCase("chrome")){
            driver = new ChromeDriver();
            // set chrome driver
        } else if (browser.equalsIgnoreCase("firefox")) {
            // set firefox driver
            driver = new FirefoxDriver();
        }

     }
    
}
