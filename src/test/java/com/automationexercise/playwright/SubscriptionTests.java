package com.automationexercise.playwright;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;
import com.microsoft.playwright.options.AriaRole;

@UsePlaywright(CustomOptions.class)
class SubscriptionTests {

    @Test
    void test10_verifySubscriptionInHomePage(Page page) {
	// 1-2. Launch browser and navigate
	page.navigate("http://automationexercise.com");

	// 3. Verify home page is visible
	assertThat(page).hasTitle("Automation Exercise");

	// 4. Scroll down to footer
	page.evaluate("window.scrollTo(0, document.body.scrollHeight)");

	// 5. Verify text 'SUBSCRIPTION'
	assertThat(page.getByText("SUBSCRIPTION")).isVisible();

	// 6. Enter email and click arrow button
	String email = RandomStringUtils.insecure().nextAlphanumeric(10) + "@mailinator.com";
	page.locator("input[type='email']").fill(email);
	page.locator("button#subscribe, button[type='submit'], .fa-arrow-circle-o-right").first().click();

	// 7. Verify success message
	assertThat(page.getByText("You have been successfully subscribed!")).isVisible();
    }

    @Test
    void test11_verifySubscriptionInCartPage(Page page) {
	// 1. Launch browser
	// 2. Navigate to url 'http://automationexercise.com'
	page.navigate("http://automationexercise.com/");

	// 3. Verify that home page is visible successfully
	assertThat(page).hasTitle("Automation Exercise");

	// 4. Click 'Cart' button
	page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Cart")).click();

	// 5. Scroll down to footer
	page.evaluate("window.scrollTo(0, document.body.scrollHeight)");

	// 6. Verify text 'SUBSCRIPTION'
	assertThat(page.getByText("SUBSCRIPTION")).isVisible();

	// 7. Enter email address and click arrow button
	String email = RandomStringUtils.insecure().nextAlphanumeric(10) + "@mailinator.com";
	page.locator("input[type='email']").fill(email);
	page.locator("button#subscribe, button[type='submit'], .fa-arrow-circle-o-right").first().click();

	// 8. Verify success message 'You have been successfully subscribed!'
	assertThat(page.getByText("You have been successfully subscribed!")).isVisible();
    }
}
