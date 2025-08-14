package com.automationexercise.selenium;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ContactUsTests extends LaunchBrowser {

    @Test
    void testContactUsFormSubmission() {
	// Navigate to Home page
	driver.get("https://automationexercise.com/");

	// Click on 'Contact Us' link
	driver.findElement(By.linkText("Contact us")).click();

	// Assert navigation by checking the heading
	WebElement heading = driver.findElement(By.xpath("//h2[contains(text(), 'Get In Touch')]"));
	assertTrue(heading.isDisplayed(), "Contact Us page heading should be visible");

	// Fill out the form
	driver.findElement(By.name("name")).sendKeys("Test User");
	driver.findElement(By.name("email")).sendKeys("testuser@example.com");
	driver.findElement(By.name("subject")).sendKeys("Test Subject");
	driver.findElement(By.name("message")).sendKeys("This is a test message.");

	// Upload a file
	String filePath = Paths.get("src/test/resources/testfile.txt").toAbsolutePath().toString();
	driver.findElement(By.name("upload_file")).sendKeys(filePath);

	// Submit the form
	driver.findElement(By.name("submit")).click();

	// Handle popup alert if present
	try {
	    driver.switchTo().alert().accept();
	} catch (org.openqa.selenium.NoAlertPresentException e) {
	    // No alert appeared; continue
	}

	// Verify success message
	WebElement successMsg = driver.findElement(By.cssSelector(".status.alert-success"));
	assertTrue(successMsg.isDisplayed());
	assertTrue(successMsg.getText().contains("Success"), "Success message should be displayed");
    }
}
