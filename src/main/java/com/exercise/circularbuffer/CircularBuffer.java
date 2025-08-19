package com.exercise.circularbuffer;

/**
 * My implementation based on
 * <a href="https://wikipedia.org/wiki/Circular_buffer">wikipedia.org</a>.
 */
public class CircularBuffer {

    // buffer capacity (length)
    private static final int DEFAULT_CAPACITY = 7;
    // NOTE: only (N - 1) elements can be stored at a given time
    private int capacity = DEFAULT_CAPACITY + 1;
    // write to buffer index (end)
    private int writeIndex = 0;
    // read from buffer index (start)
    private int readIndex = 0;
    // buffer in memory
    private int[] buffer = null;

    public CircularBuffer() {
	buffer = new int[this.capacity];
    }

    public CircularBuffer(int capacity) {
	if (capacity > 0)
	    this.capacity = capacity + 1;
	buffer = new int[this.capacity];
    }

    public int put(int item) {
	if (isFull())
	    return 0;

	buffer[writeIndex] = item;
	writeIndex = (writeIndex + 1) % capacity;
	return 1;
    }

    public int get() {
	if (isEmpty())
	    return 0;

	int value = buffer[readIndex];
	readIndex = (readIndex + 1) % capacity;
	return value;
    }

    public boolean isFull() {
	return (writeIndex + 1) % capacity == readIndex;
    }

    public boolean isEmpty() {
	return writeIndex == readIndex;
    }

    public void clear() {
	writeIndex = 0;
	readIndex = 0;
    }
}

// TODO:
// - change buffer to generic type
// https://jenkov.com/tutorials/java-performance/ring-buffer.html
// https://www.baeldung.com/java-ring-buffer