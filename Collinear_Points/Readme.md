# Collinear Points

## Find line segments containing 4 or more points from a set of given points.

### Implementation:

<h3>Points.java :</h3> Implements the Points class which contains functions to compare two points, get the slope of the line joining two points and also implements a Comparator.
<h3>LineSegment.java :</h3> Implements the LineSegment class which stores a collection of the end-points of each desired line segment.
<h3>BruteCollinearPoints.java :</h3> Implements a Brute force algorithm with O(n<sup>4</sup>) time complexity to get all line segments satisfying the conditions.
<h3>FastCollinearPoints.java :</h3> Implements a Merge Sort Algorithm using the Point class' comparator to sort each point wrt their slopes with a reference point.
<hr>

### Output (FastCollinearPoints.java) 

The Left side image shows the image obtained and the right side is the image given to verify.
<br><br>
<img src='RS_join.jpg'>
