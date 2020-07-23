/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MergeX;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
    private Point[] points;
    private int n;
    private LineSegment[] fourPointLines;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException("Points must be passed");
        for (Point point : points) {
            if (point == null) throw new IllegalArgumentException("Null Points cannot be passed");
        }
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length && j != i; j++) {
                if (points[i].equals(points[j]))
                    throw new IllegalArgumentException("Duplicate Points");
            }
        }
        this.points = new Point[points.length];
        System.arraycopy(points, 0, this.points, 0, points.length);
        this.n = this.points.length;
        fourPointLines = check();
    }

    // the number of line segments
    public int numberOfSegments() {
        return fourPointLines.length;
    }


    // the line segments
    private LineSegment[] check() {
        LineSegment[] temp = new LineSegment[4 * n];
        int counter = 0;
        MergeX.sort(points);
        for (int u = 0; u < n; u++) {
            for (int v = u + 1; v < n; v++) {
                for (int w = v + 1; w < n; w++) {
                    for (int x = w + 1; x < n; x++) {
                        Point p = points[u];
                        Point q = points[v];
                        Point r = points[w];
                        Point s = points[x];
                        if (Double.compare(p.slopeTo(q), p.slopeTo(r)) == 0
                                && Double.compare(p.slopeTo(q), p.slopeTo(s)) == 0) {
                            temp[counter++] = new LineSegment(p, s);
                        }
                    }
                }
            }
        }
        LineSegment[] lines = new LineSegment[counter];
        for (int i = 0; i < counter; i++) {
            lines[i] = temp[i];
        }
        return lines;

    }

    public LineSegment[] segments() {
        LineSegment[] temp = new LineSegment[numberOfSegments()];
        System.arraycopy(this.fourPointLines, 0, temp, 0, numberOfSegments());
        return temp;
    }

    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

}
