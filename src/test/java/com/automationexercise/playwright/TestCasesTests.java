package com.automationexercise.playwright;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.util.List;

import org.apache.commons.lang3.BooleanUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.junit.UsePlaywright;
import com.microsoft.playwright.options.AriaRole;

class TestCasesTests {

    private static Playwright playwright;
    private static Browser browser;
    private BrowserContext context;
    private Page page;

    /**
     * All the setUp and tearDown can be replaced with {@link UsePlaywright}
     * annotation.
     * 
     * @throws Exception
     */
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
	// read property from Maven pom.xml
	boolean headless = BooleanUtils.toBoolean(System.getProperty("headless", "no"));
	playwright = Playwright.create();
	browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(headless));
    }

    /**
     * {@link Playwright} is actually {@link AutoCloseable}, so you can just launch
     * things from {@code try-with-resource} block.
     */
    @AfterAll
    static void tearDownAfterClass() throws Exception {
	if (playwright != null) {
	    playwright.close();
	    playwright = null;
	}
    }

    @BeforeEach
    void setUp() throws Exception {
	context = browser.newContext();
	page = context.newPage();
    }

    @AfterEach
    void tearDown() throws Exception {
	context.close();
    }

    /**
     * Test Case 7: Verify Test Cases Page
     * 
     * 1. Launch browser
     * 
     * 2. Navigate to url 'http://automationexercise.com'
     * 
     * 3. Verify that home page is visible successfully
     * 
     * 4. Click on 'Test Cases' button
     * 
     * 5. Verify user is navigated to test cases page successfully
     */
    @Test
    void test07() {

	page.navigate("http://automationexercise.com");

	assertThat(page).hasTitle("Automation Exercise");

	// This is **not** efficient, but I just want to try something!
	List<Locator> navigation = page.getByRole(AriaRole.LISTITEM).all();
	for (Locator nav : navigation) {
	    System.out.println("DEBUG> " + nav.textContent());
	    if (nav.textContent().contains("Test Cases")) {
		nav.click();
		break;
	    }
	}

	assertThat(page).hasTitle("Automation Practice Website for UI Testing - Test Cases");
    }
}
