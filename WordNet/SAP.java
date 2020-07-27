/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SAP {

    private final Digraph sap;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        if (G == null) throw new IllegalArgumentException("Null Graph");
        this.sap = G;
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        if (v < 0 || v > this.sap.V() || w < 0 || w > this.sap.V())
            throw new IllegalArgumentException("Vertex Index Out Of Bounds");
        return ancestralPath(v, w)[1];
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        if (v < 0 || v > this.sap.V() || w < 0 || w > this.sap.V())
            throw new IllegalArgumentException("Vertex Index Out Of Bounds");
        return ancestralPath(v, w)[0];
    }

    private int[] ancestralPath(int v, int w) {
        BreadthFirstDirectedPaths bfdp1 = new BreadthFirstDirectedPaths(this.sap, v);
        BreadthFirstDirectedPaths bfdp2 = new BreadthFirstDirectedPaths(this.sap, w);
        return bfs(bfdp1, bfdp2);
    }

    private int[] ancestralPath(Iterable<Integer> v, Iterable<Integer> w) {
        BreadthFirstDirectedPaths bfdp1 = new BreadthFirstDirectedPaths(this.sap, v);
        BreadthFirstDirectedPaths bfdp2 = new BreadthFirstDirectedPaths(this.sap, w);
        return bfs(bfdp1, bfdp2);
    }


    private int[] bfs(BreadthFirstDirectedPaths bfdp1, BreadthFirstDirectedPaths bfdp2) {
        int minDistance = Integer.MAX_VALUE;
        int ancestor = 0;
        for (int i = 0; i < this.sap.V(); i++) {
            if (bfdp1.hasPathTo(i) && bfdp2.hasPathTo(i)) {
                int distance = bfdp1.distTo(i) + bfdp2.distTo(i);
                if (distance < minDistance) {
                    minDistance = distance;
                    ancestor = i;
                }
            }
        }
        if (minDistance == Integer.MAX_VALUE) {
            return new int[] { -1, -1 };
        }
        return new int[] { ancestor, minDistance };
    }


    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        if (v == null || w == null) throw new IllegalArgumentException("Null Iterables");
        return ancestralPath(v, w)[1];
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        if (v == null || w == null) throw new IllegalArgumentException("Null Iterables");
        return ancestralPath(v, w)[0];
    }

    // do unit testing of this class
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }
}

