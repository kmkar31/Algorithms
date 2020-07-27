# WordNet

## Use Directed Acyclic Graphs(DAG) to process WordNets 

<img src='WordNet/logo.png'>

## Implementation

<ul>
<li><b>WordNet.java : </b> Implements a wordnet class that takes in two text files called synsets and hypernyms ans constructs a graph. It also calculates the shortest ancestor 
between two vertices.</li>
<li><b>SAP.java :</b> Implements a class to determine the shortest ancestor and path length between any two ancestors.</li>
<li><b>Outcast.java :</b> Determines which word is least related to the others from a set of words.</li>
</ul>

![wordnet graph](https://i2.wp.com/openscience.com/wp-content/uploads/2016/08/1.png?fit=729%2C483&ssl=1)

## Features:

* The graph is a rooted DAG i.e., there are no cycles in the graph and all vertices terminate into one vertex (the root).

![WG-2](https://coursera.cs.princeton.edu/algs4/assignments/wordnet/wordnet-event.png)

## Unit-Testing and Output:
### Test if the WordNet constructor throws errors when the graph is not a rooted DAG

<img src='WordNet/Wordnet_graph_testing.png'>

### Test if the shortest ancestral path length and ancestor are correct

<img src='WordNet/SAP_unit_testing.png'>

### Test if the correct outcast word is returned

<img src='WordNet/Outcast_unit_testing.png'>
