package com.inferenceCloud.base;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.inferenceCloud.driver.DriverFactory;
import com.inferenceCloud.utils.ConfigReader;
import com.inferenceCloud.utils.Log;

public class BaseTest {
    protected String email;
    protected String password;

    private Logger logger = Log.getLogger(BaseTest.class);

    @BeforeMethod
    public void setupDriver() {
        logger.info("Setting up the driver and navigating to the URL");
        DriverFactory.setDriver(ConfigReader.getProperty("browser"));          
        logger.info("Launching URL: " + ConfigReader.getProperty("prodUrl"));
        DriverFactory.getDriver().manage().window().maximize();
        DriverFactory.getDriver().get(ConfigReader.getProperty("prodUrl"));
        email = ConfigReader.getProperty("email");
        password = ConfigReader.getProperty("password");
    }

    @AfterMethod
    public void tearDown() {
        logger.info("Quitting Driver...");
        DriverFactory.unloadDriver();
      
    }
     
    
    
}
