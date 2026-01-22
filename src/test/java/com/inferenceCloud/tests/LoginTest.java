package com.inferenceCloud.tests;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.inferenceCloud.base.BaseTest;
import com.inferenceCloud.pages.DashboardPage;
import com.inferenceCloud.pages.LoginPage;

import org.testng.Assert;

@Listeners(com.inferenceCloud.listeners.TestListeners.class)
public class LoginTest extends BaseTest {

    @Test
    public void loginButtonIsDisabledWithoutCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(!loginPage.loginButtonLocator().isEnabled(),"Login button should be disabled when no credentials are provided");
        softAssert.assertEquals(loginPage.welcomeTextLocator().getText(), "Welcome to Inferencecloud.ai");
        softAssert.assertAll();
    }

    @Test
    public void testLogin() {
    LoginPage loginPage = new LoginPage(driver);
    DashboardPage dashboardPage = loginPage.loginUser(email, password);
    dashboardPage.closeNotificationIfPresent();
    Assert.assertEquals("Team Swiggy", dashboardPage.getTeamName());
    }


}
  