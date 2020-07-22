/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;

import java.util.NoSuchElementException;

public class Permutation {
    public static void main(String[] args) {
        if (args.length != 1)
            throw new NoSuchElementException("Enter a number k and a text file containing data");
        int k = Integer.parseInt(args[0]);

        RandomizedQueue<String> RQ = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            RQ.enqueue(StdIn.readString());
        }

        for (int i = 0; i < k; i++) {
            System.out.println(RQ.dequeue());
        }
    }
}
