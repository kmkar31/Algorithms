/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Outcast {
    private final WordNet net;

    // constructor takes a WordNet object
    public Outcast(WordNet wordnet) {
        this.net = wordnet;
    }

    // given an array of WordNet nouns, return an outcast
    public String outcast(String[] nouns) {
        int maxDistance = Integer.MIN_VALUE;
        String maxWord = "";
        for (String wordA : nouns) {
            int outcastDistance = 0;
            for (String wordB : nouns) {
                outcastDistance += this.net.distance(wordA, wordB);
            }
            if (outcastDistance > maxDistance) {
                maxDistance = outcastDistance;
                maxWord = wordA;
            }
        }
        return maxWord;
    }

    // see test client below
    public static void main(String[] args) {
        WordNet wordnet = new WordNet(args[0], args[1]);
        Outcast outcast = new Outcast(wordnet);
        for (int t = 2; t < args.length; t++) {
            In in = new In(args[t]);
            String[] nouns = in.readAllStrings();
            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }
    }
}
