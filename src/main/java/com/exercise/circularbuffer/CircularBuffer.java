package com.exercise.circularbuffer;

/**
 * <p>
 * My implementation based on
 * <a href="https://wikipedia.org/wiki/Circular_buffer">wikipedia.org</a>.
 * <p>
 * <strong>Note:</strong> This is not thread-safe!
 */
public class CircularBuffer<E> {

    // buffer capacity (length)
    private static final int DEFAULT_CAPACITY = 7;
    // NOTE: only (N - 1) elements can be stored at a given time
    private int capacity = DEFAULT_CAPACITY + 1;
    // write to buffer index (end)
    private int writeIndex = 0;
    // read from buffer index (start)
    private int readIndex = 0;
    // buffer in memory
    private Object[] buffer = null;

    public CircularBuffer() {
	buffer = new Object[this.capacity];
    }

    public CircularBuffer(int capacity) {
	if (capacity > 0)
	    this.capacity = capacity + 1;
	buffer = new Object[this.capacity];
    }

    public boolean put(E value) {
	if (isFull())
	    return false;

	buffer[writeIndex] = value;
	writeIndex = (writeIndex + 1) % capacity;
	return true;
    }

    @SuppressWarnings("unchecked")
    public E get() {
	if (isEmpty())
	    return null;

	E value = (E) buffer[readIndex];
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
// https://jenkov.com/tutorials/java-performance/ring-buffer.html
// https://www.baeldung.com/java-ring-buffer