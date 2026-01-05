package com.exercise.strings;

import java.util.Arrays;
import java.util.List;

public class StringSorter {

    private StringSorter() {
	// Do not instantiate this class
    }

    /**
     * The actual interview question: Given a String of Strings, return a sorted
     * String based on the last character of each sub-String.
     * 
     * @param str
     * @return
     */
    public static String sortByLastChar(String str) {
	return String.join(" ", sortByLastChar(str.split(" ")));
    }

    /**
     * Given an Array of Strings, return a sorted Array based on the last character
     * of each String.
     * 
     * @param arr
     * @return
     */
    public static String[] sortByLastChar(String[] arr) {
	Arrays.sort(arr, (a, b) -> {
	    char lastA = a.charAt(a.length() - 1);
	    char lastB = b.charAt(b.length() - 1);
	    return Character.compare(lastA, lastB);
	});
	return arr;
    }

    /**
     * For comparison, Given a List of Strings, return a sorted List based on the
     * last character of each String.
     * 
     * @param list
     * @return
     */
    public static List<String> sortByLastChar(List<String> list) {
	return list.stream().sorted((a, b) -> {
	    char lastA = a.charAt(a.length() - 1);
	    char lastB = b.charAt(b.length() - 1);
	    return Character.compare(lastA, lastB);
	}).toList();
    }
}
