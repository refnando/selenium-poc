package com.refnando.poc.tests.base;

import com.refnando.poc.base.DriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected String baseUrl = System.getProperty("baseUrl", "https://www.saucedemo.com/");

    @BeforeMethod
    public void setUp() {
        DriverManager.init();
        DriverManager.getDriver().manage().window().maximize();
        DriverManager.getDriver().get(baseUrl);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverManager.quit();
    }
}
