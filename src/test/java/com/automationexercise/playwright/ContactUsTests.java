package com.automationexercise.playwright;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.nio.charset.StandardCharsets;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;
import com.microsoft.playwright.options.FilePayload;

@UsePlaywright(CustomOptions.class)
class ContactUsTests {

    /**
     * Test Case 6: Contact Us Form
     * 
     * 1. Launch browser
     * 
     * 2. Navigate to url 'http://automationexercise.com'
     * 
     * 3. Verify that home page is visible successfully
     * 
     * 4. Click on 'Contact Us' button
     * 
     * 5. Verify 'GET IN TOUCH' is visible
     * 
     * 6. Enter name, email, subject and message
     * 
     * 7. Upload file
     * 
     * 8. Click 'Submit' button
     * 
     * 9. Click OK button
     * 
     * 10. Verify success message 'Success! Your details have been submitted
     * successfully.' is visible
     * 
     * 11. Click 'Home' button and verify that landed to home page successfully
     */
    @Test
    void test06(Page page) {

	page.navigate("http://automationexercise.com");

	assertThat(page).hasTitle("Automation Exercise");

	page.getByText("Contact us").click();

	assertThat(page.getByText("GET IN TOUCH")).isVisible();

	page.getByTestId("name").fill(RandomStringUtils.insecure().nextAlphabetic(10));
	page.getByTestId("email").fill(RandomStringUtils.insecure().nextAlphanumeric(10) + "@mailinator.com");
	page.getByTestId("subject").fill(RandomStringUtils.insecure().nextAlphabetic(10));
	page.getByTestId("message").fill(RandomStringUtils.insecure().nextAlphabetic(100));

	page.locator("//input[@type='file']").setInputFiles(
		new FilePayload("test06.txt", "text/plain", "this is a test file".getBytes(StandardCharsets.UTF_8)));

	/*
	 * By default, dialogs are auto-dismissed by Playwright, so you don't have to
	 * handle them. However, you can register a dialog handler before the action
	 * that triggers the dialog to either Dialog.accept() or Dialog.dismiss() it.
	 * 
	 * https://playwright.dev/java/docs/dialogs
	 */
	page.onDialog(dialog -> {
	    System.out.println("DEBUG> " + dialog.message());
	    dialog.accept();
	});
	page.getByTestId("submit-button").click();

	// https://playwright.dev/java/docs/locators#matching-one-of-the-two-alternative-locators
	assertThat(page.getByText("Success! Your details have been submitted successfully.").first()).isVisible();
    }
}
