package com.exercise.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.jupiter.api.Test;

class StringSorterTest {

    @Test
    void testSorter() {
	String originalStr = "apple2 banana3 cherry5 date1";
	String expectedStr = "date1 apple2 banana3 cherry5";
	String actualStr = StringSorter.sortByLastChar(originalStr);

	assertEquals(expectedStr, actualStr, "Strings should be sorted by last character");
    }

    @Test
    void testMany() {
	StringBuilder string = new StringBuilder();
	for (int i = 0; i < 100_000; i++) {
	    string.append(RandomStringUtils.insecure().nextAlphabetic(10));
	    string.append(" ");
	}

	StopWatch stopwatch = new StopWatch();
	stopwatch.start();
	StringSorter.sortByLastChar(string.toString());
	stopwatch.stop();

	System.out.println("INFO> Time taken: " + stopwatch.getDuration().toMillis());
    }
}
