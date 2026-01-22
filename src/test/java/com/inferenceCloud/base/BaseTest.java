package com.inferenceCloud.base;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.inferenceCloud.driver.DriverFactory;
import com.inferenceCloud.utils.ConfigReader;

public class BaseTest {
    protected String email;
    protected String password;

    @BeforeMethod
    public void setupDriver() {
        DriverFactory.setDriver(ConfigReader.getProperty("browser"));  
        DriverFactory.getDriver().manage().window().maximize();
        DriverFactory.getDriver().get(ConfigReader.getProperty("prodUrl"));
        email = ConfigReader.getProperty("email");
        password = ConfigReader.getProperty("password");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.unloadDriver();
      
    }
     
    
    
}
