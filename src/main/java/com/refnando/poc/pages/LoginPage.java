package com.refnando.poc.pages;

import com.refnando.poc.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By username = By.id("user-name");
    private final By password = By.id("password");
    private final By loginBtn = By.id("login-button");

    public LoginPage(WebDriver driver) { super(driver); }

    public LoginPage typeUsername(String value) {
        driver.findElement(username).clear();
        driver.findElement(username).sendKeys(value);
        return this;
    }

    public LoginPage typePassword(String value) {
        driver.findElement(password).clear();
        driver.findElement(password).sendKeys(value);
        return this;
    }

    public void submit() { driver.findElement(loginBtn).click(); }
}