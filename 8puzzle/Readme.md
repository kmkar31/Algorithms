# 8Puzzle Solver

## Use the A* algorithm to create a solver for the 8Puzzle and all higher variants of the same

<img src='8puzzle/logo.png'>

## Implementation

<h3>Board.java :</h3> defines the game state (i.e., the board) . Has methods to return all possible possible states within one move etc.
<h3>Solver.java :</h3> implements the A* algorithm to solve the board. Also takes unsolvable boards into consideration.
<h3>PuzzleChecker.java :</h3> solves each and every game given in the <b>/Games</b> folder and return minimum number of moves (-1 if unsolvable) .

