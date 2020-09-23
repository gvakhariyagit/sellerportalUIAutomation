package com.example.selenium_cucmber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import selenium.automateGoogleAccount.DriverInitializer;

import java.util.concurrent.TimeUnit;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features",
        //   tags = " not @Important",
        glue={"com.example.selenium_cucmber.steps"},
        plugin = {"pretty", "html:target/cucumber"}
)
public class TestRunner extends AbstractTestNGCucumberTests {

    public static ThreadLocal<WebDriver> webdriverThreards = new ThreadLocal<WebDriver>();

    /**
     * Before each scenario, initialise webDriver.
     */
    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public void beforeScenario(@Optional String browser) {
        intializeWebdriver(browser);
    }

    /**
     * After each scenario, quit the web driver.
     */
    @AfterMethod(alwaysRun = true)
    public void afterScenario() {
        webdriverThreards.get().close();
        webdriverThreards.remove();
    }

    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    private void intializeWebdriver(String browser) {
        System.out.println("Current thread : " + Thread.currentThread().getId());
        WebDriver webDriver = DriverInitializer.getDriver(browser);
        webDriver.manage().window().maximize();
        webDriver.manage().deleteAllCookies();
        webDriver.manage().timeouts().setScriptTimeout(80, TimeUnit.SECONDS);
        webdriverThreards.set(webDriver);

    }

    public static WebDriver getWebDriver() {
        return webdriverThreards.get();
    }
}