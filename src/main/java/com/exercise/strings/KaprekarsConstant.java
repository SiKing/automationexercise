package com.exercise.strings;

import java.util.Arrays;

/**
 * @see <a href=
 *      "https://en.wikipedia.org/wiki/6174#Kaprekar's_constant">wikipedia.org</a>
 */
public class KaprekarsConstant {

    private KaprekarsConstant() {
	// Do not instantiate this class
    }

    public static int getConstantFrom(int num) {

	// 1. validate number fits in 4 digits
	if (num > 9999)
	    throw new IllegalArgumentException("The number must have 4 digits!");

	int stop = 10; // to prevent endless loop
	do {
	    // 3. sort digits ascending - "subtrahend"
	    char[] tempCh = Integer.toString(num).toCharArray();
	    Arrays.sort(tempCh);
	    String subtrahend = new String(tempCh);
	    // left-pad with zeroes
	    subtrahend = String.format("%4s", subtrahend).replace(" ", "0");

	    // 2. validate number has at lest 2 digits different
	    // we're OK to check only the first and last digit, **after** sort
	    if (subtrahend.charAt(0) == subtrahend.charAt(3))
		throw new IllegalArgumentException("At least 2 digits must be different!");

	    // 4. sort digits descending - "minuend"
	    StringBuilder tempSb = new StringBuilder(subtrahend);
	    tempSb.reverse();
	    String minuend = tempSb.toString();

	    // 5. get the "difference"
	    num = Integer.parseInt(minuend) - Integer.parseInt(subtrahend);
	    System.out.println(String.format("DEBUG> %s - %s = %d", minuend, subtrahend, num));
	} while (num != 6174 && stop-- > 0);

	return num;
    }
}
