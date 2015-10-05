package com.pmodrigues.pageobjects.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Created by Marceloo on 05/10/2015.
 */
public final class WebDriverFactory {

    public static final WebDriver createWebDriver() {

        System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY,
                "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type");
        WebDriver driver = new ChromeDriver(options);
        return driver;

    }
}
