import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FordFulkerson;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.HashMap;
import java.util.HashSet;

public class BaseballElimination {
    private static final int S = 0;
    private static final int T = 1;

    private final HashMap<String, Integer> NameToId;
    private final String[] Teams;
    private final int[] Wins;
    private final int[] Losses;
    private final int[] Remaining;
    private final int[][] G;
    private final HashMap<String, HashSet<String>> Cause;

    // create a baseball division from given filename in format specified below
    public BaseballElimination(String filename) {
        In in = new In(filename);
        int n = in.readInt();

        this.NameToId = new HashMap<>();
        this.Teams = new String[n];
        this.Wins = new int[n];
        this.Losses = new int[n];
        this.Remaining = new int[n];
        this.G = new int[n][n];
        this.Cause = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String teamName = in.readString();

            this.NameToId.put(teamName, i);
            this.Teams[i] = teamName;

            this.Wins[i] = in.readInt();
            this.Losses[i] = in.readInt();
            this.Remaining[i] = in.readInt();

            for (int j = 0; j < n; j++) {
                this.G[i][j] = in.readInt();
            }
        }
    }

    // number of Teams
    public int numberOfTeams() {
        return Teams.length;
    }

    // all Teams
    public Iterable<String> teams() {
        return this.NameToId.keySet();
    }

    // number of Wins for given team
    public int wins(String team) {
        return this.Wins[this.Index(team)];
    }

    // number of Losses for given team
    public int losses(String team) {
        return this.Losses[this.Index(team)];
    }

    // number of Remaining games for given team
    public int remaining(String team) {
        return this.Remaining[this.Index(team)];
    }

    // number of Remaining games between team1 and team2
    public int against(String team1, String team2) {
        return this.G[this.Index(team1)][this.Index(team2)];
    }

    private Integer Index(String team) {
        Integer Index = this.NameToId.get(team);
        if (Index == null) {
            throw new IllegalArgumentException();
        }

        return Index;
    }

    private HashSet<String> eliminationSet(String team) {
        if (this.NameToId.get(team) == null) {
            throw new IllegalArgumentException();
        }

        if (this.Cause.get(team) == null) {
            if (!trivialElimination(team)) {
                nonTrivialElimination(team);
            }
        }

        return this.Cause.get(team);
    }

    private boolean trivialElimination(String team) {
        int Index = this.Index(team);
        int maxWins = this.Wins[Index] + this.Remaining[Index];
        HashSet<String> eliminatingTeams = new HashSet<>();

        for (int i = 0; i < this.numberOfTeams(); i++) {
            if (i == Index) continue;

            if (maxWins < Wins[i]) {
                eliminatingTeams.add(this.Teams[i]);
            }
        }

        this.Cause.put(team, eliminatingTeams);

        return !eliminatingTeams.isEmpty();
    }


    private void nonTrivialElimination(String team) {
        int id = Index(team);
        int n = this.Teams.length;
        int preFinalLayer = n - 1;
        int totalGames = n * preFinalLayer;
        int nC2 = totalGames / 2;
        int V = 2 + nC2;

        FlowNetwork graph = new FlowNetwork(V);

        int count = preFinalLayer + 2;

        int maxWins = this.Wins[id] + this.Remaining[id];

        for (int i = 0; i < this.numberOfTeams(); i++) {
            if (i == id) continue;

            int preFinalIndex = i < id ? i + 2 : i + 1;
            graph.addEdge(new FlowEdge(preFinalIndex, T,
                                       maxWins - this.Wins[i]));

            for (int j = 0; j < i; j++) {
                if (j == id) continue;

                graph.addEdge(new FlowEdge(S, count, G[i][j]));
                graph.addEdge(
                        new FlowEdge(count, preFinalIndex, Double.POSITIVE_INFINITY));

                int team2Index = j < id ? j + 2 : j + 1;
                graph.addEdge(new FlowEdge(count, team2Index,
                                           Double.POSITIVE_INFINITY));

                count++;
            }
        }

        FordFulkerson ff = new FordFulkerson(graph, S, T);
        HashSet<String> eliminatingTeams = new HashSet<>();

        for (int i = 0; i < this.numberOfTeams(); i++) {
            if (i == id) continue;

            int preFinalIndex = i < id ? i + 2 : i + 1;

            if (ff.inCut(preFinalIndex)) {
                eliminatingTeams.add(Teams[i]);
            }
        }

        this.Cause.put(team, eliminatingTeams);
    }

    // is given team eliminated?
    public boolean isEliminated(String team) {
        return !eliminationSet(team).isEmpty();
    }

    // subset R of Teams that eliminates given team; null if not eliminated
    public Iterable<String> certificateOfElimination(String team) {
        return isEliminated(team) ? eliminationSet(team) : null;
    }


    public static void main(String[] args) {
        BaseballElimination division = new BaseballElimination(args[0]);
        for (String team : division.teams()) {
            if (division.isEliminated(team)) {
                StdOut.print(team + " is eliminated by the subset R = { ");
                for (String t : division.certificateOfElimination(team)) {
                    StdOut.print(t + " ");
                }
                StdOut.println("}");
            }
            else {
                StdOut.println(team + " is not eliminated");
            }
        }
    }
}
