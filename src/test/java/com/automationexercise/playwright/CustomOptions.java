package com.automationexercise.playwright;

import org.apache.commons.lang3.BooleanUtils;

import com.microsoft.playwright.junit.Options;
import com.microsoft.playwright.junit.OptionsFactory;

/**
 * https://playwright.dev/java/docs/junit#customizing-options
 */
public class CustomOptions implements OptionsFactory {

    @Override
    public Options getOptions() {
	// read property from Maven pom.xml
	boolean headless = BooleanUtils.toBoolean(System.getProperty("headless", "no"));
	return new Options() //
		.setHeadless(headless) // headless browser?
		.setTestIdAttribute("data-qa") // website uses this as "test-id"
	;
    }
}
