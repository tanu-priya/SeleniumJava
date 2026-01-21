package com.inferenceCloud.tests;
import org.testng.annotations.Test;

import com.inferenceCloud.base.BaseTest;
import com.inferenceCloud.pages.DashboardPage;
import com.inferenceCloud.pages.LoginPage;
import org.testng.Assert;

public class LoginTest extends BaseTest {

    @Test
    public void testLogin() {
    LoginPage loginPage = new LoginPage(driver);
    DashboardPage dashboardPage = loginPage.loginUser(email, password);
    dashboardPage.closeNotificationIfPresent();
    Assert.assertEquals("Team Swiggy", dashboardPage.getTeamName());
    }

}
  