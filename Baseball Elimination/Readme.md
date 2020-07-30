# Baseball Elimination

## Given the scoreboard of a tournament at any point of time predict whether or not the team has a chance of topping its division.

![](https://s24526.pcdn.co/wp-content/uploads/2019/07/web1_ball-640x381.jpg)

<hr>

## Implemetation:

The teams are split into two groups.
* One, even if they win all of their remaining games, they will still stand lower than the current division leader. Hence they have no shot at winning.
Such cases are called <b>Trivial Eliminations</b>

* Two, their chance of success depends on the outcome and schedule of the remaining games. Such cases are <b>Non-Trivial Eliminations</b>
<br><br>

For the Second Case, a graph based on the current leaderboard is created and and formed into a <b>MaxFlow</b> problem. 
In the maxflow problem, if there exists alteast one way to direct the flow such that the team in consideration would not need to win more games than it has remaining,
the team is still in the race. Otherwise, it is eliminated.

A Subset <b>R</b> of the division teams is formed . The subset R for an eliminated team is the set of all teams which resulted in its elimination.

![](https://x-wei.github.io/images/algoII_week3_1/pasted_image019.png)

<br><br>
 Here's such an explanation for Detroit's elimination in the American League East example above. With the best possible luck, 
 Detroit finishes the season with 49 + 27 = 76 wins. 
 Consider the subset of teams R = { New York, Baltimore, Boston, Toronto }. 
 Collectively, they already have 75 + 71 + 69 + 63 = 278 wins; there are also 3 + 8 + 7 + 2 + 7 = 27 remaining games among them, 
 so these four teams must win at least an additional 27 games. Thus, on average, the teams in R win at least 305 / 4 = 76.25 games. 
 Regardless of the outcome, one team in R will win at least 77 games, thereby eliminating Detroit.
 
 <hr>
 
 ## Outputs:
 
 ### Output for the above example
 
<img src='Baseball Elimination/Outputs/output1.png'>

### At the end of all Group Stage games

<img src='Baseball Elimination/Outputs/EndOfTournament.png'>

### 12-Country division

<img src='Baseball Elimination/Outputs/team12.png'>

### 60-Team division

<img src='Baseball Elimination/Outputs/team60.jpg'>
