/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;

import java.util.LinkedList;

public class PointSET {

    private SET<Point2D> set;

    // construct an empty set of points
    public PointSET() {
        this.set = new SET<Point2D>();
    }

    // is the set empty?
    public boolean isEmpty() {
        return set.isEmpty();
    }

    // number of points in the set
    public int size() {
        return set.size();
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException("Empty Query");
        if (!contains(p)) set.add(p);
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException("Empty Query");
        return set.contains(p);
    }

    // draw all points to standard draw
    public void draw() {
        for (Point2D point2D : set) {
            point2D.draw();
        }
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException("Empty Rectangle range");
        LinkedList<Point2D> collection = new LinkedList<Point2D>();
        for (Point2D point : set) {
            if (rect.contains(point)) collection.add(point);
        }
        return collection;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException("Empty Query");
        if (set.isEmpty()) return null;
        Point2D nearestPoint = null;
        for (Point2D point : set) {
            if (nearestPoint == null || point.distanceSquaredTo(p) < nearestPoint
                    .distanceSquaredTo(p)) {
                nearestPoint = point;
            }
        }
        return nearestPoint;
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {
    }
}
