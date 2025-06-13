package com.automationexercise.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

class RegisterTests extends LaunchBrowser {

    private static final String username = "Paul Stira";

    /**
     * Test Case 1: Register User
     * 
     * 1. Launch browser
     * 
     * 2. Navigate to url 'http://automationexercise.com'
     * 
     * 3. Verify that home page is visible successfully
     * 
     * 4. Click on 'Signup / Login' button
     * 
     * 5. Verify 'New User Signup!' is visible
     * 
     * 6. Enter name and email address
     * 
     * 7. Click 'Signup' button
     * 
     * 8. Verify that 'ENTER ACCOUNT INFORMATION' is visible
     * 
     * 9. Fill details: Title, Name, Email, Password, Date of birth
     * 
     * 10. Select checkbox 'Sign up for our newsletter!'
     * 
     * 11. Select checkbox 'Receive special offers from our partners!'
     * 
     * 12. Fill details: First name, Last name, Company, Address, Address2, Country,
     * State, City, Zipcode, Mobile Number
     * 
     * 13. Click 'Create Account button'
     * 
     * 14. Verify that 'ACCOUNT CREATED!' is visible
     * 
     * 15. Click 'Continue' button
     * 
     * 16. Verify that 'Logged in as username' is visible
     * 
     * 17. Click 'Delete Account' button
     * 
     * 18. Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button
     */
    @Test
    void test01() {

	driver.navigate().to("http://automationexercise.com");

	assertEquals("Automation Exercise", driver.getTitle());

	driver.findElement(By.partialLinkText("Signup / Login")).click();

	String signup = driver.findElement(By.className("signup-form")).findElement(By.tagName("h2")).getText();
	assertEquals("New User Signup!", signup);

	driver.findElement(By.className("signup-form")).findElement(By.name("name")).sendKeys(username);
	driver.findElement(By.className("signup-form")).findElement(By.name("email"))
		.sendKeys(RandomStringUtils.insecure().nextAlphanumeric(10) + "@mailinator.com");

	driver.findElement(By.xpath("//button[text()='Signup']")).click();

	String information = driver.findElement(By.tagName("h2")).getText(); // only one H2 on the page
	assertEquals("ENTER ACCOUNT INFORMATION", information);

	driver.findElement(By.id("id_gender1")).click();
	String uname = driver.findElement(By.id("name")).getAttribute("value");
	assertEquals(username, uname);
	String email = driver.findElement(By.id("email")).getAttribute("value").split("@")[1];
	assertEquals("mailinator.com", email);
	driver.findElement(By.id("password")).sendKeys(RandomStringUtils.insecure().nextAlphanumeric(10));
	new Select(driver.findElement(By.id("days"))).selectByValue("10");
	new Select(driver.findElement(By.id("months"))).selectByValue("10");
	new Select(driver.findElement(By.id("years"))).selectByVisibleText("2000");

	driver.findElement(By.id("newsletter")).click();

	driver.findElement(By.id("optin")).click();

	driver.findElement(By.id("first_name")).sendKeys(RandomStringUtils.insecure().nextAlphabetic(10));
	driver.findElement(By.id("last_name")).sendKeys(RandomStringUtils.insecure().nextAlphabetic(10));
	driver.findElement(By.id("company")).sendKeys(RandomStringUtils.insecure().nextAlphabetic(10));
	driver.findElement(By.id("address1")).sendKeys(RandomStringUtils.insecure().nextAlphanumeric(10));
	driver.findElement(By.id("address2")).sendKeys(RandomStringUtils.insecure().nextAlphanumeric(10));
	String country = new Select(driver.findElement(By.id("country"))).getFirstSelectedOption().getText();
	assertEquals("India", country);
	driver.findElement(By.id("state")).sendKeys(RandomStringUtils.insecure().nextAlphabetic(10));
	driver.findElement(By.id("city")).sendKeys(RandomStringUtils.insecure().nextAlphabetic(10));
	driver.findElement(By.id("zipcode")).sendKeys(RandomStringUtils.insecure().nextAlphanumeric(10));
	driver.findElement(By.id("mobile_number")).sendKeys(RandomStringUtils.insecure().nextNumeric(10));

	driver.findElement(By.xpath("//button[text()='Create Account']")).click();

	String created = driver.findElement(By.tagName("h2")).getText();
	assertEquals("ACCOUNT CREATED!", created);

	driver.findElement(By.linkText("Continue")).click();

	String user = driver.findElement(By.className("navbar-nav")).findElements(By.tagName("li")).get(9).getText();
	assertEquals("Logged in as " + username, user);

	driver.findElement(By.partialLinkText("Delete Account")).click();

	String deleted = driver.findElement(By.tagName("h2")).getText();
	assertEquals("ACCOUNT DELETED!", deleted);
	driver.findElement(By.linkText("Continue")).click();

	assertEquals("Automation Exercise", driver.getTitle()); // back to Home page
    }
}
