package ads.lab2.part4;

import ads.lab2.part1.ArrayStack;
import ads.lab2.part1.EmptyStackException;
import ads.lab2.part3.EmptyQueueException;
import ads.lab2.part3.QueueInterface;

/**
 * A class for stack-based queue
 */
public class StackQueue<AnyType> implements QueueInterface<AnyType> {

    private final ArrayStack<AnyType> in;
    private final ArrayStack<AnyType> out;

    /**
     * Build an empty queue
     * Complexity: THETA(1)
     */
    public StackQueue() {
        in = new ArrayStack<>();
        out = new ArrayStack<>();
    }

    /**
     * Return the number of elements
     * currently in the queue
     * Complexity: THETA(1)
     */
    public int size() {
        return in.size() + out.size();
    }

    /**
     * Return the next value to be dequeued
     * If the queue is empty throws EmptyQueueException
     * Complexity: amortized O(1)
     */
    public AnyType peek() throws EmptyQueueException {
        if (out.isEmpty() && in.isEmpty()) {
            throw new EmptyQueueException();
        }

        if (out.isEmpty()) {
            while(!in.isEmpty()) {
                try {
                    out.push(in.pop());
                } catch (EmptyStackException e) {
                    e.printStackTrace();
                }
            }
        }

        try {
            return out.peek();
        } catch (EmptyStackException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Enqueue x in the queue
     * Complexity: THETA(1)
     */
    public void enqueue(AnyType x) {
        in.push(x);
    }

    /**
     * Dequeue and return the next element to
     * be dequeued
     * If the queue is empty throws EmptyQueueException
     * Complexity: amortized O(1)
     */
    public AnyType dequeue() throws EmptyQueueException {
        if (out.isEmpty() && in.isEmpty()) {
            throw new EmptyQueueException();
        }

        if (out.isEmpty()) {
            for (int i = 0; i < in.size(); i++) {
                try {
                    out.push(in.pop());
                } catch (EmptyStackException e) {
                    e.printStackTrace();
                }
            }
        }

        try {
            return out.pop();
        } catch (EmptyStackException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Return a string representation of the queue
     * in the form of "<- A B C <-" where A is the
     * front and C the tail of the queue
     * Complexity: THETA(n) where n is the current
     * size of the queue
     */
    public String toString() {
        return "<-" + (out.toString() + in).replaceAll("\\[", "").replaceAll(" ->", "") + " <- ";
    }

    public static void main(String[] args) {
        StackQueue<Integer> q1 = new StackQueue<>();
        try {
            q1.enqueue(1); q1.enqueue(2); q1.enqueue(3);
            System.out.println(q1);
        } catch(Exception e) { System.out.println(e); }



        try {
            System.out.println(q1.peek());
        } catch(Exception e) { System.out.println(e); }
    }
}
