package ads.lab2.part3;

import ads.lab2.part1.EmptyStackException;

/**
 * An interface for queue classes
 */
public interface QueueInterface<AnyType> {

    /**
     * Check if the queue is empty
     */
    default boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Return the number of elements
     * currently in the queue
     */
	int size();

    /**
     * Return the next value to be dequeued
     * If the queue is empty throws EmptyQueueException
     */
	AnyType peek() throws EmptyQueueException;

    /**
     * Enqueue x in the queue
     */
	void enqueue(AnyType x);

    /**
     * Dequeue and return the next element to
     * be dequeued
     * If the queue is empty throws EmptyQueueException
     */
	AnyType dequeue() throws EmptyQueueException;
}
