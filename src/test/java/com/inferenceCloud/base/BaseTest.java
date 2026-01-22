package com.inferenceCloud.base;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.inferenceCloud.driver.DriverFactory;
import com.inferenceCloud.utils.ConfigReader;

public class BaseTest {
    public WebDriver driver;
    protected String email;
    protected String password;

    @BeforeMethod
    public void setupDriver() {
        driver = DriverFactory.getDriver(ConfigReader.getProperty("browser"));  
        driver.manage().window().maximize();
        driver.get(ConfigReader.getProperty("prodUrl"));
        email = ConfigReader.getProperty("email");
        password = ConfigReader.getProperty("password");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }   
    }
     
    
    
}
