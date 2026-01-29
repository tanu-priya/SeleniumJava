package com.inferenceCloud.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.inferenceCloud.driver.DriverFactory;
import com.inferenceCloud.utils.WaitUtil;

public class LoginPage {

    private WebDriver driver;
    public LoginPage(WebDriver driver){
        this.driver = DriverFactory.getDriver();

    }

    private By Email = By.name("email");
    private By Password = By.name("password");
    private By LoginButton = By.xpath("//button[@data-aria-label='Log In']");
    private By WelcomeText = By.xpath("//img/following-sibling::h2[contains(@class,'text')]");
    private By LoginError = By.xpath("//div[contains(@class,'ic-icon')]/following-sibling::p");

    public void enterEmail(String email){
        driver.findElement(Email).sendKeys(email);
    }

    public void enterPassword(String password){
        driver.findElement(Password).sendKeys(password);
    }

    public void clickLogin(){
        driver.findElement(LoginButton).click();
    }

    public WebElement loginButtonLocator(){
        return driver.findElement(LoginButton);
    }

    public WebElement welcomeTextLocator(){
        return driver.findElement(WelcomeText);
    }  
    public WebElement errorLocator(){
        return driver.findElement(LoginError);
    }
    public String getWelcomeText(){
        return errorLocator().getText();
    }

    public boolean isLoginErrorDisplayed(){
        WaitUtil.waitForVisibility(driver, 5, LoginError);
        return errorLocator().isDisplayed();
    }

    public DashboardPage loginUser(String email, String password){
        WaitUtil.waitForVisibility(driver, 5, Email);
        enterEmail(email);
        enterPassword(password);
        WaitUtil.waitForClickability(driver, 5, LoginButton);
        clickLogin();
        return new DashboardPage(driver);
    }
}