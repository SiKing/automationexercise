package com.exercise.circularbuffer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CircularBufferTest {

    @Test
    void testPut() {
	// DEFAULT_CAPACITY is 7
	CircularBuffer<Integer> buffer = new CircularBuffer<>();

	// Test putting an item into an empty buffer
	Assertions.assertTrue(buffer.put(1));
	// Test putting another item
	Assertions.assertTrue(buffer.put(2));
	// Test putting multiple items
	Assertions.assertTrue(buffer.put(3));
	Assertions.assertTrue(buffer.put(4));
	Assertions.assertTrue(buffer.put(5));
	Assertions.assertTrue(buffer.put(6));
	// Buffer is not full yet
	Assertions.assertTrue(buffer.put(7));
	// Buffer is full now
	Assertions.assertFalse(buffer.put(7));
    }

    @Test
    void testGet() {
	// DEFAULT_CAPACITY is 7
	CircularBuffer<Integer> buffer = new CircularBuffer<>();

	// Test getting from an empty buffer
	Assertions.assertNull(buffer.get());
	// Fill the buffer
	buffer.put(1);
	buffer.put(2);
	buffer.put(3);
	Assertions.assertEquals(1, buffer.get());
	Assertions.assertEquals(2, buffer.get());
	Assertions.assertEquals(3, buffer.get());
	// Buffer is empty now
	Assertions.assertNull(buffer.get());
	// Test putting more items after emptying the buffer
	buffer.put(4);
	buffer.put(5);
	Assertions.assertEquals(4, buffer.get());
	Assertions.assertEquals(5, buffer.get());
	// Buffer is empty again
	Assertions.assertNull(buffer.get());
	// Getting from an empty buffer again
	Assertions.assertNull(buffer.get());
    }

    @Test
    void testCapatity() {
	CircularBuffer<Integer> buffer = new CircularBuffer<>(5);
	for (int i = 5; i > 0; i--) {
	    // fill up buffer
	    buffer.put(111);
	}
	Assertions.assertFalse(buffer.put(666), "Buffer should be full!");

	buffer = new CircularBuffer<>(10);
	for (int i = 10; i > 0; i--) {
	    // fill up buffer
	    buffer.put(111);
	}
	Assertions.assertFalse(buffer.put(666), "Buffer should be full!");
    }

    @Test
    void testClear() {
	CircularBuffer<Integer> buffer = new CircularBuffer<>();
	Assertions.assertTrue(buffer.isEmpty(), "Buffer should be empty to start");

	buffer.put(1);
	buffer.put(2);
	buffer.put(3);
	Assertions.assertFalse(buffer.isEmpty(), "Buffer should not be empty before clear");

	buffer.clear();
	Assertions.assertTrue(buffer.isEmpty(), "Buffer should be empty after clear");

	Assertions.assertTrue(buffer.put(4), "Buffer should accept new items after clear");
    }

    @Test
    void testGenricType() {
	CircularBuffer<String> buffer = new CircularBuffer<>();
	Assertions.assertTrue(buffer.put("Hello"));
	Assertions.assertTrue(buffer.put("World"));
	Assertions.assertEquals("Hello", buffer.get());
	Assertions.assertEquals("World", buffer.get());
    }
}
