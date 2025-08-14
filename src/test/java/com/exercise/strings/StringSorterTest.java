package com.exercise.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class StringSorterTest {

    @Test
    void testSorter() {
	String originalStr = "apple2 banana3 cherry5 date1";
	String expectedStr = "date1 apple2 banana3 cherry5";
	String actualStr = StringSorter.sortByLastChar(originalStr);

	assertEquals(expectedStr, actualStr, "Strings should be sorted by last character");
    }
}
