package com.automationexercise.selenium;

import org.apache.commons.lang3.BooleanUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CustomOptions {

    static WebDriver driver;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
	// read property from Maven pom.xml
	boolean headless = BooleanUtils.toBoolean(System.getProperty("headless", "no"));
	ChromeOptions options = new ChromeOptions();
	if (headless)
	    options.addArguments("--headless=new");
	driver = new ChromeDriver(options);
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
	if (driver != null) {
	    driver.quit();
	    driver = null;
	}
    }
}
