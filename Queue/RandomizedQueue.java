/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int size;
    private Item[] data;

    // construct an empty randomized queue
    public RandomizedQueue() {
        data = (Item[]) new Object[1];
        size = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return (size == 0);
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    private void resize(int newLength) {
        Item[] temp = (Item[]) new Object[newLength];
        for (int i = 0; i < size; i++) {
            temp[i] = data[i];
        }
        data = temp;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("Null Object cannot be added");
        if (size == data.length) {
            resize(2 * data.length);
        }
        data[size++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (size == 0) throw new NoSuchElementException("Data Structure is Empty");
        int t = StdRandom.uniform(size);
        Item item = data[t];
        data[t] = null;
        size--;
        if (size > 0 && size <= (data.length) / 4) {
            resize(data.length / 2);
        }
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (size == 0) throw new NoSuchElementException("Data Structure is Empty");
        int t = StdRandom.uniform(size);
        Item item = data[t];
        return item;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RQIterator();
    }

    private class RQIterator implements Iterator<Item> {

        private int counter;
        private int[] arr;

        public RQIterator() {
            counter = 0;
            arr = StdRandom.permutation(size);
        }

        public boolean hasNext() {
            return counter < size;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException("No More Elements to iterate");
            Item item = data[arr[counter]];
            counter++;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException(
                    "Remove Operations are not allowed in this Implementation");
        }

    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> RQ = new RandomizedQueue<>();
        RQ.enqueue("Should be the first element");
        RQ.enqueue("Should be the second element");
        RQ.enqueue("Should be the last element");
        Iterator<String> iterator = RQ.iterator();
        while (iterator.hasNext()) {
            String s = iterator.next();
            System.out.println(s);
        }
        // This should print in a random order
        System.out.println(RQ.dequeue());
        System.out.println(RQ.dequeue());

    }

}
