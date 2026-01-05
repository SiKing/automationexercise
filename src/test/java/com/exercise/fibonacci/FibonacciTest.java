package com.exercise.fibonacci;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FibonacciTest {

    @Test
    void testFibonacci() {
	Assertions.assertArrayEquals(new int[] { 0, 1, 1, 2, 3 }, Fibonacci.fib(5));
    }

    @Test
    void testFibonacciZero() {
	Assertions.assertEquals(0, Fibonacci.fib(1)[0]);

	Assertions.assertEquals(0, Fibonacci.fibRecursive(0));
    }

    @Test
    void testFibonacciPosition() {
	Assertions.assertEquals(4181, Fibonacci.fib(20)[19]);

	Assertions.assertEquals(4181, Fibonacci.fibRecursive(19));
    }

    @Test
    void testFibonacciMany() {
	StopWatch stopwatch = new StopWatch();
	stopwatch.start();
	Fibonacci.fib(100_000_000);
	stopwatch.stop();

	System.out.println("INFO> Time taken: " + stopwatch.getDuration().toMillis());
    }

    @Test
    void testFibRecursiveMany() {
	StopWatch stopwatch = new StopWatch();
	stopwatch.start();
	Fibonacci.fibRecursive(50); // XXX: >50 starts to take too long; works on my machine {tm}
	stopwatch.stop();

	System.out.println("INFO> Time taken: " + stopwatch.getDuration().toMillis());
    }
}
