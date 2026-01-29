package com.inferenceCloud.dataproviders;

import com.inferenceCloud.utils.ConfigReader;
import org.testng.annotations.DataProvider;


public class TestDataProvider {

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return new Object[][] {
            {ConfigReader.getProperty("email"), ConfigReader.getProperty("password"), true},
            {"invalid@email.com", "validPass", false},
            {"valid@email.com", "wrongPass", false}
        };
    
    }
}
