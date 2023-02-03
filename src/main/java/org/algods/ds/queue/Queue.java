package org.algods.ds.queue;


import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<Item> implements Iterable<Item> {
    private Node<Item> first; // beginning of the queue
    private Node<Item> last; //ending of the queue
    private int n;  //number of elements is the queue
    private class Node<Item>{
       public Item item;
       public Node<Item> next;

    }
    /**
     * Initializes an empty queue.
     */
    public Queue() {
        first = null;
        last  = null;
        n = 0;
    }
    /**
     * Returns True if the queue is empty
     * **/
    public boolean isEmpty(){
        return first == null;
    }
    /**
     * Returns the number items in this queue
     */
    public int size(){
        return n;
    }
    /**
     * adds items to this queue
     * */
    public void enqueue (Item item){
        Node<Item> oldlast= last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;

        if(isEmpty()){
            first = last;
        }else
            oldlast.next=last;
        n++;
    }
    /**
     * returns the items and deletes items from this queue
     * */
    public Item dequeue(){
        if(isEmpty()){
             throw new NoSuchElementException("Queue Underflow");
        }
        Item item = first.item;
        first = first.next;
        n--;
        if(isEmpty()){
            last=null; //avoid loitering - you remove that reference but item still contains a value that points to the original object.
        }
        return item;
    }
    /**
     * Returns the item least recently added to this queue.
     * */
    public Item peek(){
        if(isEmpty()){
            throw new NoSuchElementException("Queue underflow");
        }
        Item item = first.item;
        return item;
    }
    @Override
    public Iterator<Item> iterator() {
        return new LinkedIterator(first);
    }

    private class LinkedIterator implements Iterator<Item>{
        private Node<Item> current;
        public LinkedIterator(Node<Item> item) {
            current = first;
        }
        @Override
        public boolean hasNext() {
            return current!= null;
        }
        @Override
        public Item next() {
            if(!hasNext()) throw new UnsupportedOperationException();
            Item item = current.item;
            current = current.next;
            return null;
        }
    }
    public static void main(String args[]) {
        Queue<String> queue = new Queue<>();
        queue.enqueue("kinjal");
        queue.enqueue("Anni");
        queue.enqueue("foram");
        System.out.println(queue.peek());
        System.out.println(queue.size());
//        while (queue.size() > 0) {
//            System.out.println(queue.dequeue());
//        }
        while (!queue.isEmpty()) {
            System.out.println(queue.dequeue());
        }
    }
}
