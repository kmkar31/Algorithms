/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.HashMap;

public class WordNet {

    private final HashMap<Integer, String> synsets = new HashMap<Integer, String>();
    private final HashMap<String, ArrayList<Integer>> wordtoId = new HashMap<>();
    private final SAP sap;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        if (synsets == null || hypernyms == null)
            throw new IllegalArgumentException("Invalid Filenames");

        // For each line, add the synset and corresponding id to the synsets hashmap
        // The same synset can contain multiple words and the same word can belong to multiple synset IDs
        // Hence, Map each word in every synset to an array of IDs of the synsets it belongs to.
        In in = new In(synsets);
        while (!in.isEmpty()) {
            String[] line = in.readLine().split(",");
            int index = Integer.parseInt(line[0]);
            this.synsets.put(index, line[1]);
            for (String word : line[1].split(" ")) {
                if (!wordtoId.containsKey(word)) wordtoId.put(word, new ArrayList<Integer>());
                this.wordtoId.get(word).add(index);
            }
        }

        // Initialize the Directed graph
        Digraph net = new Digraph(this.synsets.size());

        // Add edges to the graph using the hypernyms file
        // The first field has the synset ID and the subsequent fields have hypernyms of that synset.
        in = new In(hypernyms);
        while (!in.isEmpty()) {
            String[] line = in.readLine().split(",");
            for (int i = 1; i < line.length; i++) {
                net.addEdge(Integer.parseInt(line[0]), Integer.parseInt(line[i]));
            }
        }

        // Check if the graph is acyclic
        if (new DirectedCycle(net).hasCycle())
            throw new IllegalArgumentException("The graph is not acyclic");

        // Check if the graph is rooted
        int count = 0;
        for (int v = 0; v < net.V(); v++) {
            if (net.outdegree(v) == 0) count++;
        }
        if (count != 1) throw new IllegalArgumentException("Graph is not rooted");


        // Initialize the SAP;
        this.sap = new SAP(net);

    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return wordtoId.keySet();
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        if (word == null) throw new IllegalArgumentException("Null Word");
        return wordtoId.containsKey(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        if (nounA == null || nounB == null || !isNoun(nounA) || !isNoun(nounB))
            throw new IllegalArgumentException("Invalid Nouns");
        return this.sap.length(wordtoId.get(nounA), wordtoId.get(nounB));
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        if (nounA == null || nounB == null || !isNoun(nounA) || !isNoun(nounB))
            throw new IllegalArgumentException("Invalid Nouns");
        return synsets.get(this.sap.ancestor(wordtoId.get(nounA), wordtoId.get(nounB)));
    }

    // do unit testing of this class
    public static void main(String[] args) {
        // Unit Testing done along with Outcast.java
    }
}
