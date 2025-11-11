package com.refnando.poc.listeners;

import com.refnando.poc.base.DriverManager;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

public class ScreenshotOnFailure implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = null;
        try {
            driver = DriverManager.getDriver();
            if (driver != null) {
                // Screenshot
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment("Screenshot", "image/png", new ByteArrayInputStream(screenshot), "png");

                // Page source
                String pageSource = driver.getPageSource();
                Allure.addAttachment("Page Source", "text/html",
                        new ByteArrayInputStream(pageSource.getBytes(StandardCharsets.UTF_8)), ".html");

                // Current URL
                Allure.addAttachment("Current URL", "text/plain",
                        new ByteArrayInputStream(driver.getCurrentUrl().getBytes(StandardCharsets.UTF_8)), ".txt");
            } else {
                Allure.addAttachment("Driver not initialized",
                        new ByteArrayInputStream("Driver was null when trying to capture screenshot."
                                .getBytes(StandardCharsets.UTF_8)));
            }
        } catch (Exception e) {
            Allure.addAttachment("Screenshot error",
                    new ByteArrayInputStream(("Error capturing screenshot: " + e.getMessage())
                            .getBytes(StandardCharsets.UTF_8)));
        }
    }
}