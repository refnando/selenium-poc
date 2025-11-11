package com.refnando.poc.tests;

import com.refnando.poc.base.DriverManager;
import com.refnando.poc.pages.LoginPage;
import com.refnando.poc.tests.base.BaseTest;
import com.refnando.poc.utils.EnvReader;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("SauceDemo")
@Feature("Login")
public class LoginTests extends BaseTest {

    @Story("Happy path")
    @Test(description = "Succes login and user ia taken into /inventory.html")
    @Description("Validates that users can authenticate correctly using secure credentials.")
    public void shouldLoginAndSeeInventory() {
        String baseUrl = EnvReader.get("BASE_URL");
        String username = EnvReader.get("TEST_USER");
        String password = EnvReader.get("TEST_PASS");

        DriverManager.getDriver().get(baseUrl);

        new LoginPage(DriverManager.getDriver())
                .typeUsername(username)
                .typePassword(password)
                .submit();

        String url = DriverManager.getDriver().getCurrentUrl();
        Assert.assertTrue(url.contains("inventory.html"),
                "User has not been taken into inventory page. current URL: " + url);
    }
}