package com.inferenceCloud.utils;
import java.io.ByteArrayInputStream;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Allure;

public class AllureUtil {

    public static void attachScreenshot(WebDriver driver, String name) {

        try {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

            Allure.addAttachment(name, new ByteArrayInputStream(screenshot));
        } catch (Exception e) {
            System.out.println("Unable to attach screenshot to Allure: " + e.getMessage());
        }
    }
}

