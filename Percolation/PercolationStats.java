/* *****************************************************************************
 *  Name:              Karthik Karumanchi
 *  Coursera User ID:  
 *  Last modified:    
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {

    private double[] fracs;
    private int trials;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {

        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException("Enter Positive values for n and trials");
        this.trials = trials;
        this.fracs = new double[trials];

        for (int i = 0; i < trials; i++) {
            Percolation test = new Percolation(n);
            while (!(test.percolates())) {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                test.open(row, col);
            }
            this.fracs[i] = (double) test.numberOfOpenSites() / (n * n);
        }

    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(this.fracs);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(this.fracs);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return (mean() - (1.96 / (Math.sqrt(this.trials)) * stddev()));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return (mean() + (1.96 / (Math.sqrt(this.trials)) * stddev()));
    }

    // test client (see below)
    public static void main(String[] args) {
        if (args.length != 2)
            throw new IllegalArgumentException("Enter Two Positive Integer arguments");
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        Stopwatch timer = new Stopwatch();
        PercolationStats experiment = new PercolationStats(n, trials);
        System.out.printf("Mean: %f \n", experiment.mean());
        System.out.printf("Stddev: %f \n", experiment.stddev());
        System.out.printf("95%% Confidence Interval: [%f , %f] \n", experiment.confidenceLo(),
                          experiment.confidenceHi());
        System.out.printf("RunTime: %f \n", timer.elapsedTime());
    }

}
