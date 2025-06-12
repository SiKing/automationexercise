package com.automationexercise.playwright;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.util.regex.Pattern;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;

/**
 * https://playwright.dev/java/docs/junit
 */
@UsePlaywright(CustomOptions.class)
class Register {

    private static String username = "Mark Abene";

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
    void test01(Page page) {

	page.navigate("http://automationexercise.com");

	assertThat(page).hasTitle("Automation Exercise");

	page.getByText("Signup / Login").click(); // ByText is normalized

	assertThat(page.getByText("New User Signup!")).isVisible();

	page.getByTestId("signup-name").fill(username);
	page.getByTestId("signup-email").fill(RandomStringUtils.insecure().nextAlphanumeric(10) + "@mailinator.com");

	page.getByTestId("signup-button").click();

	assertThat(page.getByText("ENTER ACCOUNT INFORMATION")).isVisible();

	page.locator("//input[@id='id_gender1']").click(); // XPath
	assertThat(page.getByTestId("name")).hasValue(username);
	assertThat(page.getByTestId("email")).hasValue(Pattern.compile(".*@mailinator.com"));
	page.getByTestId("password").fill(RandomStringUtils.insecure().nextAlphanumeric(10));
	page.getByTestId("days").selectOption("10");
	page.getByTestId("months").selectOption("10");
	page.getByTestId("years").selectOption("2000");

	page.locator("//input[@id='newsletter']").click();

	page.locator("//input[@id='optin']").click();

	page.getByTestId("first_name").fill(RandomStringUtils.insecure().nextAlphabetic(10));
	page.getByTestId("last_name").fill(RandomStringUtils.insecure().nextAlphabetic(10));
	page.getByTestId("company").fill(RandomStringUtils.insecure().nextAlphabetic(10));
	page.getByTestId("address").fill(RandomStringUtils.insecure().nextAlphanumeric(10));
	page.getByTestId("address2").fill(RandomStringUtils.insecure().nextAlphanumeric(10));
	assertThat(page.getByTestId("country")).hasValue("India");
	page.getByTestId("state").fill(RandomStringUtils.insecure().nextAlphabetic(10));
	page.getByTestId("city").fill(RandomStringUtils.insecure().nextAlphabetic(10));
	page.getByTestId("zipcode").fill(RandomStringUtils.insecure().nextAlphanumeric(10));
	page.getByTestId("mobile_number").fill(RandomStringUtils.insecure().nextNumeric(10));

	page.getByTestId("create-account").click();

	assertThat(page.getByText("ACCOUNT CREATED!")).isVisible();

	page.getByTestId("continue-button").click();

	assertThat(page.getByText("Logged in as " + username)).isVisible();

	page.getByText("Delete Account").click();

	assertThat(page.getByText("ACCOUNT DELETED!")).isVisible();
	page.getByTestId("continue-button").click();

	assertThat(page).hasTitle("Automation Exercise"); // back to Home page
    }
}
