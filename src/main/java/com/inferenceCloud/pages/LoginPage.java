package com.inferenceCloud.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.inferenceCloud.utils.WaitUtil;

public class LoginPage {

    private WebDriver driver;
    public LoginPage(WebDriver driver){
        this.driver = driver;

    }

    private By Email = By.name("email");
    private By Password = By.name("password");
    private By LoginButton = By.xpath("//button[@data-aria-label='Log In']");

    public void enterEmail(String email){
        driver.findElement(Email).sendKeys(email);
    }

    public void enterPassword(String password){
        driver.findElement(Password).sendKeys(password);
    }

    public void clickLogin(){
        driver.findElement(LoginButton).click();
    }

    public By loginButtonLocator(){
        return LoginButton;
    }

    public DashboardPage loginUser(String email, String password){
        enterEmail(email);
        enterPassword(password);
        WaitUtil.waitForClickability(driver, 5, LoginButton);
        clickLogin();
        return new DashboardPage(driver);
    }
}