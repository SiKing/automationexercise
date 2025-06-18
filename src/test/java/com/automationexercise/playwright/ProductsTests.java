package com.automationexercise.playwright;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;

@UsePlaywright(CustomOptions.class)
class ProductsTests {

    @BeforeEach
    void setUp(Page page) throws Exception {

	page.navigate("http://automationexercise.com");

	assertThat(page).hasTitle("Automation Exercise");

	page.getByText("Products").click();

	assertThat(page).hasTitle("Automation Exercise - All Products");
    }

    /**
     * Test Case 8: Verify All Products and product detail page
     * 
     * 1. Launch browser
     * 
     * 2. Navigate to url 'http://automationexercise.com'
     * 
     * 3. Verify that home page is visible successfully
     * 
     * 4. Click on 'Products' button
     * 
     * 5. Verify user is navigated to ALL PRODUCTS page successfully
     * 
     * 6. The products list is visible
     * 
     * 7. Click on 'View Product' of first product
     * 
     * 8. User is landed to product detail page
     * 
     * 9. Verify that detail detail is visible: product name, category, price,
     * availability, condition, brand
     */
    @Test
    void test08(Page page) {

	List<Locator> products = page.locator(".product-image-wrapper").all();
	for (Locator prod : products) {
	    Locator name = prod.locator("//p").first();
	    System.out.println("DEBUG> " + name.textContent());
	    assertThat(prod).isVisible();
	}

	products.getFirst().getByText("View Product").click();

	assertThat(page).hasTitle("Automation Exercise - Product Details");

	Locator productInformation = page.locator(".product-information");
	assertThat(productInformation.locator("//h2")).isVisible(); // product name
	assertThat(productInformation.locator("//p").first()).isVisible(); // category
	assertThat(productInformation.locator("//span/span")).isVisible(); // price
	assertThat(productInformation.getByText("Availability:")).isVisible(); // availability
	assertThat(productInformation.getByText("Condition:")).isVisible(); // condition
	assertThat(productInformation.getByText("Brand:")).isVisible(); // brand
    }

    /**
     * Test Case 9: Search Product
     * 
     * 1. Launch browser
     * 
     * 2. Navigate to url 'http://automationexercise.com'
     * 
     * 3. Verify that home page is visible successfully
     * 
     * 4. Click on 'Products' button
     * 
     * 5. Verify user is navigated to ALL PRODUCTS page successfully
     * 
     * 6. Enter product name in search input and click search button
     * 
     * 7. Verify 'SEARCHED PRODUCTS' is visible
     * 
     * 8. Verify all the products related to search are visible
     */
    @Test
    void test09(Page page) {

	page.locator("id=search_product").fill("green"); // search is case-insensitive
	page.locator("id=submit_search").click();

	assertThat(page.getByText("SEARCHED PRODUCTS")).isVisible();

	List<Locator> products = page.locator(".product-image-wrapper").all();
	for (Locator prod : products) {
	    Locator name = prod.locator("//p").first();
	    System.out.println("DEBUG> " + name.textContent());
	    assertTrue(name.textContent().toLowerCase().contains("green"));
	}
    }
}
