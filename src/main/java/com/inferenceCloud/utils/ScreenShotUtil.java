package com.inferenceCloud.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class ScreenShotUtil {

    public static String getScreenShotPath(WebDriver driver, String testCaseName) {

        if (driver == null) {
        System.out.println("❌ Driver is NULL. Screenshot cannot be taken.");
        return null;
        }
        TakesScreenshot ts = (TakesScreenshot) driver;  
        File source = ts.getScreenshotAs(OutputType.FILE);

        String user_dir = System.getProperty("user.dir");
        String screenshotPath = user_dir + "/screenshots/";

        File folder = new File(screenshotPath);
            if (!folder.exists()) {
            folder.mkdirs();
        }
        String path = screenshotPath + testCaseName + ".png";
        File destination = new File(path);

        try {
            FileUtils.copyFile(source, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return screenshotPath;
    }
    
}
