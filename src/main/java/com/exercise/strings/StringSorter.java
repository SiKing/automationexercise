package com.exercise.strings;

import java.util.Arrays;

public class StringSorter {

    private StringSorter() {
	// Do not instantiate this class
    }

    /**
     * Given a String of Strings, return a sorted String based on the last character
     * of each sub-String.
     * 
     * @param str
     * @return
     */
    public static String sortByLastChar(String str) {
	return String.join(" ", sortByLastChar(str.split(" ")));
    }

    public static String[] sortByLastChar(String[] arr) {
	Arrays.sort(arr, (a, b) -> {
	    char lastA = a.charAt(a.length() - 1);
	    char lastB = b.charAt(b.length() - 1);
	    return Character.compare(lastA, lastB);
	});
	return arr;
    }
}
