# Seam Carving

## Implement a Shortest Path Algorithm to determine the least important seam in a picture in order to resize ii.

<hr>

## Implementation

<ul> 
<li><b>SeamCarver.java :</b> Implements all the seam finding and energy calculations.</li>
<li><b>PrintEnergy.java :</b> Prints the energy values of every pixel in a given picture</li>
<li><b>PrintSeams.java </b> Marks the least important seam in a grid of energy values created from a picture</li>
<li><b>ShowEnergy.java :</b> Shows the given image as a heatmap of energy values</li>
<li><b>ShowSeams.java</b> Highlights the to-be-deleted seam in red in the energy heatmap</li>
<li><b>ResizeDemo.java :</b> Resizes an image based on an input of how many horizontal and vertical seams to delete</li>
<hr>

## Unit-Tests and Outputs

### CLI Tests

<img src='Tests_&_Outputs/Energy_unit_testing.png'>
<img src='Tests_&_Outputs/Seam_unit_testing_01.png'>
<img src='Tests_&_Outputs/Seam_unit_testing_02.png'>

### Image Tests

<img src='Tests_&_Outputs/energy_diagram.png'>
<img src='Tests_&_Outputs/seam_diagram.png'>

### Resized images
#### HJocean.png
##### Reduce width by 200px

<img src='Tests_&_Outputs/HJocean(-200,0).png'>

##### Reduce width by 100px and height by 50px

<img src='Tests_&_Outputs/HJocean_(-100,-50).png'>

##### Reduce width by 50px and height by 100px

<img src='Tests_&_Outputs/HJocean_(-50,-100).png'>

##### Reduce both height and width by 50px

<img src='Tests_&_Outputs/HJocean_(-50,-50).png'>

#### Chameleon.png
##### Reduce width by 200px

<img src='Tests_&_Outputs/Chameleon(-200,0).png'>
