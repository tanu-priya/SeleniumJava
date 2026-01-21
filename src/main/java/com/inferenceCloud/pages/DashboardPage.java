package com.inferenceCloud.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.inferenceCloud.utils.WaitUtil;

public class DashboardPage {

    private WebDriver driver;

    public DashboardPage(WebDriver driver){
        this.driver = driver;

    }

    private By TeamName = By.xpath("//h2[@title = 'Team Swiggy']");
    private By NotificationFinish = By.xpath("//button[@data-aria-label = 'Finish']");

    public String getTeamName(){
        return driver.findElement(TeamName).getText();
    }

     public void closeNotificationIfPresent() {
        WaitUtil.waitForVisibility(driver, 10, NotificationFinish);
        driver.findElement(NotificationFinish).click();
    }
    
}
