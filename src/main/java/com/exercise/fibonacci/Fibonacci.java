package com.exercise.fibonacci;

public class Fibonacci {

    private Fibonacci() {
	// Do not instantiate this class
    }

    /**
     * Return Fibonacci sequence up to n elements. n=0 returns empty array; n=1
     * returns array [0]; n=2 returns array [0,1]; n=3 returns array [0,1,1]; etc.
     * 
     * @param n
     * @return
     */
    public static int[] fib(int n) {
	int[] result = new int[n];
	int a = 0;
	int b = 1;

	for (int i = 0; i < n; i++) {
	    result[i] = a;
	    int next = a + b;
	    a = b;
	    b = next;
	}

	return result;
    }

    /**
     * <p>
     * Returns Fibonacci number at position n (0-based). n=0 returns 0; n=1 returns
     * 1; n=2 returns 1; n=3 returns 2; etc.
     * <p>
     * <strong>Note:</strong> This is not memory efficient, but more interesting?
     * 
     * @param n
     * @return
     */
    public static int fibRecursive(int n) {
	if (n <= 1)
	    return n;

	return fibRecursive(n - 1) + fibRecursive(n - 2);
    }
}
