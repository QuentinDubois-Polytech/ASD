package ads.lab2.part3;

/**
 * A class for list-based queue
 */

import ads.lab2.part1.EmptyStackException;

/**
 * A class for list-based queue
 */
public class ListQueue<AnyType> implements QueueInterface<AnyType> {

    private ListNode head;
    private ListNode tail;
    private int size;


    /**
     * Build an empty queue
     * Complexity: THETA(1)
     */
    public ListQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Return the number of elements
     * currently in the queue
     * Complexity: THETA(1)
     */
    public int size() {
        return size;
    }

    /**
     * Return the next value to be dequeued
     * If the queue is empty throws EmptyQueueException
     * Complexity: THETA(1)
     */
    public AnyType peek() throws EmptyQueueException {
        if (size == 0) {
            throw new EmptyQueueException();
        }
        return head.data;
    }

    /**
     * Enqueue x in the queue
     * Complexity: THETA(1)
     */
    public void enqueue(AnyType x) {
        if (size != 0) {
            ListNode previous = tail;
            tail = new ListNode(x);
            previous.next = tail;
        } else {
            ListNode node = new ListNode(x);
            tail = node;
            head = node;
        }
        size++;
    }

    /**
     * Dequeue and return the next element to
     * be dequeued
     * If the queue is empty throws EmptyQueueException
     * Complexity: THETA(1)
     */
    public AnyType dequeue() throws EmptyQueueException {
        if (size != 0) {
            AnyType value = head.data;
            head = head.next;
            size--;
            return value;
        }
        throw new EmptyQueueException();
    }

    /**
     * Return a string representation of the queue
     * in the form of "<- A B C <-" where A is the
     * front and C the tail of the queue
     * Complexity: THETA(n) where n is the number
     * of items currently in the queue
     */
    public String toString() {
        StringBuilder res = new StringBuilder("<- ");
        for (ListNode node = head; node != null; node = node.next) {
            res.append(node.data).append(" ");
        }
        res.append("<-");
        return res.toString();
    }

    //////////////////////////////////////////////

    /**
     * A private class for list node
     */
    private class ListNode {
        AnyType data;
        ListNode next;

        ListNode(AnyType data) {
            this.data = data;
            this.next = null;
        }
    }
}
