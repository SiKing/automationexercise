package com.automationexercise.playwright;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.automationexercise.restassured.RegisterTests;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;

@UsePlaywright(CustomOptions.class)
class LoginTests {

    private static RegisterTests existingUser = new RegisterTests();

    @BeforeAll
    static void createUser() throws Exception {
	existingUser.test11_createUser();
    }

    @AfterAll
    static void deleteUser() throws Exception {
	existingUser.test12_deleteUser();
    }

    @BeforeEach
    void setUp(Page page) throws Exception {

	page.navigate("http://automationexercise.com");

	assertThat(page).hasTitle("Automation Exercise");

	page.getByText("Signup / Login").click();

	assertThat(page.getByText("Login to your account")).isVisible();
    }

    /**
     * Test Case 2: Login User with correct email and password
     * 
     * 1. Launch browser
     * 
     * 2. Navigate to url 'http://automationexercise.com'
     * 
     * 3. Verify that home page is visible successfully
     * 
     * 4. Click on 'Signup / Login' button
     * 
     * 5. Verify 'Login to your account' is visible
     * 
     * 6. Enter correct email address and password
     * 
     * 7. Click 'login' button
     * 
     * 8. Verify that 'Logged in as username' is visible
     * 
     * 9. Click 'Delete Account' button
     * 
     * 10. Verify that 'ACCOUNT DELETED!' is visible
     */
    @Test
    void test02(Page page) {

	page.getByTestId("login-email").fill(RegisterTests.email);
	page.getByTestId("login-password").fill(RegisterTests.password);

	page.getByTestId("login-button").click();

	assertThat(page.getByText("Logged in as " + RegisterTests.username)).isVisible();

	// account will be deleted in @AfterAll
    }

    /**
     * Test Case 3: Login User with incorrect email and password
     * 
     * 1. Launch browser
     * 
     * 2. Navigate to url 'http://automationexercise.com'
     * 
     * 3. Verify that home page is visible successfully
     * 
     * 4. Click on 'Signup / Login' button
     * 
     * 5. Verify 'Login to your account' is visible
     * 
     * 6. Enter incorrect email address and password
     * 
     * 7. Click 'login' button
     * 
     * 8. Verify error 'Your email or password is incorrect!' is visible
     */
    @Test
    void test03(Page page) {

	// both wrong
	page.getByTestId("login-email").fill(RandomStringUtils.insecure().nextAlphanumeric(10) + "@mailinator.com");
	page.getByTestId("login-password").fill(RandomStringUtils.insecure().nextAlphanumeric(10));

	page.getByTestId("login-button").click();

	assertThat(page.getByText("Your email or password is incorrect!")).isVisible();

	// email wrong
	page.getByTestId("login-email").fill(RandomStringUtils.insecure().nextAlphanumeric(10) + "@mailinator.com");
	page.getByTestId("login-password").fill(RegisterTests.password);

	page.getByTestId("login-button").click();

	assertThat(page.getByText("Your email or password is incorrect!")).isVisible();

	// password wrong
	page.getByTestId("login-email").fill(RegisterTests.email);
	page.getByTestId("login-password").fill(RandomStringUtils.insecure().nextAlphanumeric(10));

	page.getByTestId("login-button").click();

	assertThat(page.getByText("Your email or password is incorrect!")).isVisible();
    }

    /**
     * Test Case 4: Logout User
     * 
     * 1. Launch browser
     * 
     * 2. Navigate to url 'http://automationexercise.com'
     * 
     * 3. Verify that home page is visible successfully
     * 
     * 4. Click on 'Signup / Login' button
     * 
     * 5. Verify 'Login to your account' is visible
     * 
     * 6. Enter correct email address and password
     * 
     * 7. Click 'login' button
     * 
     * 8. Verify that 'Logged in as username' is visible
     * 
     * 9. Click 'Logout' button
     * 
     * 10. Verify that user is navigated to login page
     */
    @Test
    void test04(Page page) {

	test02(page);

	page.getByText("Logout").click();

	assertThat(page).hasTitle("Automation Exercise - Signup / Login");
    }
}
