package com.inferenceCloud.tests;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.inferenceCloud.base.BaseTest;
import com.inferenceCloud.dataproviders.TestDataFromExcel;
import com.inferenceCloud.driver.DriverFactory;
import com.inferenceCloud.pages.DashboardPage;
import com.inferenceCloud.pages.LoginPage;
import com.inferenceCloud.utils.ConfigReader;

import org.testng.Assert;

public class LoginTest extends BaseTest {

    @Test
    public void loginButtonIsDisabledWithoutCredentials() {
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(!loginPage.loginButtonLocator().isEnabled(),"Login button should be disabled when no credentials are provided");
        softAssert.assertEquals(loginPage.welcomeTextLocator().getText(), "Welcome to Inferencecloud.ai");
        softAssert.assertAll();
    }

    @Test (enabled = false, dataProvider = "excelData", dataProviderClass = TestDataFromExcel.class)
    public void testLoginWithInvalidCredentials(String email, String password, String expectedResult) {
    //boolean expected = Boolean.parseBoolean(expectedResult);
    LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
    loginPage.loginUser(email, password);
    Assert.assertTrue(loginPage.isLoginErrorDisplayed(), "Login should fail");
    }

    @Test(enabled = true)
    public void testLoginWithValidCredentials() {
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        DashboardPage dashboardPage = loginPage.loginUser(ConfigReader.getProperty("email"), ConfigReader.getProperty("password"));
        dashboardPage.closeNotificationIfPresent();
        Assert.assertEquals("Team Swiggy", dashboardPage.getTeamName(), "Login should succeed and user should be navigated to dashboard");

    }

}
  