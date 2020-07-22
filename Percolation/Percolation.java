/* *****************************************************************************
 *  Name:              Karthik Karumanchi
 *  Coursera User ID:  
 *  Last modified:     
 **************************************************************************** */


import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int n;
    private WeightedQuickUnionUF grid;
    private int numOpen;
    private int[] state;

    // creates n-by-n grid, with all sites initially blocked
    // state has three values - 0 for closed, 1 for open
    public Percolation(int n) {
        this.n = n;
        this.grid = new WeightedQuickUnionUF(n * n + 2);
        this.numOpen = 0;
        this.state = new int[n * n + 2];

        for (int i = 1; i <= n; i++) {
            this.grid.union(0, index(1, i));
        }
    }

    private int index(int row, int col) {
        return (1 + n * (row - 1) + (col - 1));
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (isOpen(row, col)) return;

        state[index(row, col)] = -1;
        numOpen++;
        int[] neighbors = {
                index(row + 1, col), index(row - 1, col), index(row, col + 1),
                index(row, col - 1)
        };
        boolean[] valid_neighbors = {
                isOpen(row + 1, col), isOpen(row - 1, col), isOpen(row, col + 1),
                isOpen(row, col - 1)
        };

        for (int i = 0; i < 4; i++) {
            if (valid_neighbors[i]) grid.union(index(row, col), neighbors[i]);
        }

        if (row == n && !(percolates())) grid.union(index(row, col), n * n + 1);
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row <= 0 || col <= 0 || row > n || col > n) return false;
        if (state[index(row, col)] == 0) return false;
        return true;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (grid.find(0) == grid.find(index(row, col)) && isOpen(row, col)) return true;
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numOpen;
    }

    // does the system percolate?
    public boolean percolates() {
        if (grid.find(0) == grid.find(n * n + 1)) return true;
        return false;
    }
    
    // test client (optional)
    public static void main(String[] args) {
        return;
    }
}
