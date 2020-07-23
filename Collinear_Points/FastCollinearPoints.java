/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MergeX;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
    private Point[] points;
    private int n;
    private int segmentsCount;
    private LineSegment[] straightLines;

    // finds all line segments containing 4 points
    public FastCollinearPoints(Point[] points) {
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
        this.segmentsCount = 0;
        straightLines = check();
    }

    // the number of line segments
    public int numberOfSegments() {
        return this.segmentsCount;
    }

    // the line segments
    private LineSegment[] check() {
        LineSegment[] temp = new LineSegment[4 * n];
        MergeX.sort(points);

        for (int i = 0; i < points.length; i++) {
            Point p = points[i];

            int copySize = n - i - 1;
            Point[] copyPoints = new Point[copySize];
            System.arraycopy(points, i + 1, copyPoints, 0, copySize);
            MergeX.sort(copyPoints, p.slopeOrder());

            int lineCounter = 2;
            for (int j = 1; j < copySize; j++) {
                if (Double.compare(p.slopeTo(copyPoints[j - 1]), p.slopeTo(copyPoints[j])) == 0) {
                    lineCounter++;
                }
                else {
                    if (lineCounter >= 4) {
                        temp[segmentsCount++] = new LineSegment(p, copyPoints[j - 1]);
                    }
                    lineCounter = 2;
                }
            }
            if (lineCounter >= 4) {
                temp[segmentsCount++] = new LineSegment(p,
                                                        copyPoints[copyPoints.length - 1]);
            }
        }

        LineSegment[] realSegments = new LineSegment[segmentsCount];
        for (int i = 0; i < segmentsCount; i++) {
            realSegments[i] = temp[i];
        }
        return realSegments;
    }

    public LineSegment[] segments() {
        LineSegment[] temp = new LineSegment[this.segmentsCount];
        System.arraycopy(this.straightLines, 0, temp, 0, segmentsCount);
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
        StdDraw.setXscale(-10, 33000);
        StdDraw.setYscale(-10, 33000);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
