package com.exercise.circularbuffer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CircularBufferTest {

    @Test
    void testPut() {
	// DEFAULT_CAPACITY is 7
	CircularBuffer<Integer> buffer = new CircularBuffer<>();

	// Test putting an item into an empty buffer
	assertTrue(buffer.put(1));
	// Test putting another item
	assertTrue(buffer.put(2));
	// Test putting multiple items
	assertTrue(buffer.put(3));
	assertTrue(buffer.put(4));
	assertTrue(buffer.put(5));
	assertTrue(buffer.put(6));
	// Buffer is not full yet
	assertTrue(buffer.put(7));
	// Buffer is full now
	assertFalse(buffer.put(7));
    }

    @Test
    void testGet() {
	// DEFAULT_CAPACITY is 7
	CircularBuffer<Integer> buffer = new CircularBuffer<>();

	// Test getting from an empty buffer
	assertNull(buffer.get());
	// Fill the buffer
	buffer.put(1);
	buffer.put(2);
	buffer.put(3);
	assertEquals(1, buffer.get());
	assertEquals(2, buffer.get());
	assertEquals(3, buffer.get());
	// Buffer is empty now
	assertNull(buffer.get());
	// Test putting more items after emptying the buffer
	buffer.put(4);
	buffer.put(5);
	assertEquals(4, buffer.get());
	assertEquals(5, buffer.get());
	// Buffer is empty again
	assertNull(buffer.get());
	// Getting from an empty buffer again
	assertNull(buffer.get());
    }

    @Test
    void testCapatity() {
	CircularBuffer<Integer> buffer = new CircularBuffer<>(5);
	for (int i = 5; i > 0; i--) {
	    // fill up buffer
	    buffer.put(111);
	}
	assertFalse(buffer.put(666), "Buffer should be full!");

	buffer = new CircularBuffer<>(10);
	for (int i = 10; i > 0; i--) {
	    // fill up buffer
	    buffer.put(111);
	}
	assertFalse(buffer.put(666), "Buffer should be full!");
    }

    @Test
    void testClear() {
	CircularBuffer<Integer> buffer = new CircularBuffer<>();
	assertTrue(buffer.isEmpty(), "Buffer should be empty to start");

	buffer.put(1);
	buffer.put(2);
	buffer.put(3);
	assertFalse(buffer.isEmpty(), "Buffer should not be empty before clear");

	buffer.clear();
	assertTrue(buffer.isEmpty(), "Buffer should be empty after clear");

	assertTrue(buffer.put(4), "Buffer should accept new items after clear");
    }

    @Test
    void testGenricType() {
	CircularBuffer<String> buffer = new CircularBuffer<>();
	assertTrue(buffer.put("Hello"));
	assertTrue(buffer.put("World"));
	assertEquals("Hello", buffer.get());
	assertEquals("World", buffer.get());
    }
}
