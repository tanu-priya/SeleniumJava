package com.inferenceCloud.listeners;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.inferenceCloud.driver.DriverFactory;
import com.inferenceCloud.utils.AllureUtil;
import com.inferenceCloud.utils.ScreenShotUtil;

public class TestListeners implements ITestListener{

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("🚀 LISTENER STARTED");
        System.out.println("Test Started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test Passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("🔥 onTestFailure triggered for: " + result.getName());
        ScreenShotUtil.getScreenShotPath(DriverFactory.getDriver(),result.getName());
        AllureUtil.attachScreenshot(DriverFactory.getDriver(), "Failure Screenshot - " + result.getName());
    }
    
    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test Skipped: " + result.getName());
    }



    
}
