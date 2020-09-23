package com.example.selenium_cucmber.steps;

import com.example.selenium_cucmber.TestRunner;
import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.automateGoogleAccount.DriverInitializer;

import static org.junit.Assert.assertEquals;

public class SellerPortalLoginAutomationSteps implements En {

    private WebDriver webDriver;

    public SellerPortalLoginAutomationSteps() {
        Given("landing to seller portal login page", () -> {
            webDriver = TestRunner.getWebDriver();
            webDriver.get(DriverInitializer.getProperty("sellerPortal.url"));

        });

        When("user logs-in with credentials -> username : {word}, password: {word}", (String username,
                                                                                      String password) -> {

            // #firstName
            WebElement webElement = webDriver.findElement(By.id("mat-input-0"));
            webElement.sendKeys(username);

            webElement = webDriver.findElement(By.id("mat-input-1"));
            webElement.sendKeys(password);

            webElement = webDriver.findElement(By.xpath("//*[@id=\"app\"]/app-login/div/mat-card/mat-card-content/form/button"));
            webElement.click();

        });

        Then("user logged-in successfully with welcome title : {string}", (String welcomeTitle) -> {
            Thread.sleep(5000);
            WebElement webElement = webDriver.findElement(By.xpath("//*[@id=\"main\"]/header/mat-toolbar/div/div[3]/app-user-menu/button/span/span"));
            assertEquals(welcomeTitle, webElement.getText());
        });

        Then("login was unsuccessful with error message : {string}", (String errorMessage) -> {
            Thread.sleep(5000);
            WebElement webElement = webDriver.findElement(By.id("mat-error-3"));
            assertEquals(errorMessage, webElement.getText());
        });
    }

}
