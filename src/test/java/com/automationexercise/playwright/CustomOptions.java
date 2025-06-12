package com.automationexercise.playwright;

import com.microsoft.playwright.junit.Options;
import com.microsoft.playwright.junit.OptionsFactory;

/**
 * https://playwright.dev/java/docs/junit#customizing-options
 */
public class CustomOptions implements OptionsFactory {

    @Override
    public Options getOptions() {
	return new Options() //
		.setHeadless(false) // headless browser?
		.setTestIdAttribute("data-qa") // website uses this as "test-id"
	;
    }
}
