package collection.queue;

import java.util.PriorityQueue;

public class PriorityQueuePrac {
    public static void main(String[] args) {
        PriorityQueue pq = new PriorityQueue();
        pq.offer("34");
        pq.offer("65");
        pq.offer("11");
        System.out.println(pq);
        System.out.println(pq.peek()); // returns "null" if there is no elements in collection.queue
        System.out.println(pq.element());  // No such element exception Error if there is no element in collection.queue

        System.out.println(pq.poll()); // returns "null" if there is no elements in collection.queue
        System.out.println(pq);
        System.out.println(pq.remove()); // No such element exception Error if there is no element in collection.queue
        System.out.println(pq);
    }
}
