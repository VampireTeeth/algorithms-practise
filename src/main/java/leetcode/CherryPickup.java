package leetcode;

public class CherryPickup {

/*
    This problem can be transformed to 2 players going from (0,0) to (N-1, N-1) at the same time.
    Every time both player has a chance to move either down or right.
    Then we can define a state to be the maximum number of cherry that can be picked up when player1 reaches (i1, j1) and player2 (i2, i1+j1-i2) Note that because of the nature of movements the players are allowed to make, equation i1 + j1 == i2 + j2 always applies. e.g. (1, 2), (2, 1)
    Define state s[i1][j1][i2], then j2 = i1+j1-i2
    Init: 
    s[0][0][0] = g[0][0]

Solution 1:
Time complexity: O(n^3)
Space complexity: O(n^3)

    for l in 1..2*n-1:
        upper = min(l+1, n)
        for i1 in 0..upper:
            j1 = l - i1
            if j1 < 0 or j1 > n - 1: continue
            for i2 in 0..upper:
                j2 = l - j2
                if j2 < 0 or j2 > n - 1: continue
                if g[i1][j1] < 0 or g[i2][j2] < 0:
                    s[i1][j1][i2] = -1
                else:
                    down1left2 = i1 > 0 and j2 > 0 ? s[i1-1][j1][i2] : -1
                    down1down2 = i1 > 0 and i2 > 0 ? s[i1-1][j1][i2-1] : -1
                    left1down2 = j1 > 0 and i2 > 0 ? s[i1][j1-1][i2-1] : -1
                    left1left2 = j1 > 0 and j2 > 0 ? s[i1][j1-1][i2] : -1
                    ps = max(down1left2, down1down2, left1down2, left1left2)
                    if ps != -1:
                        if i1 != i2: s[i1][j1][i2] = g[i1][j1] + g[i2][j2] + ps
                        else: s[i1][j1][i2] = g[i1][j1] + ps
        return max(0, s[n-1][n-1][n-1])

Solution 2:
Define s to be state where s[l][i1][i2] indicates when player1 is at i1 and player2 is at i2 via l steps
Time complexity: O(n^3)
Space complexity: O(n^3)

    s = [[[-1 for i2 in 1..n-1] for i1 in 1..n-1] for l in 1..2*n-1] (3-dimention array 2 * n - 1 by n by n)
    for l in 1..2*n-1:
        upper = min(l+1, n)
        for i1 in 0..upper:
            j1 = l - i1
            if j1 < 0 or j1 > n - 1: continue
            for i2 in 0..upper:
                j2 = l - j2
                if j2 < 0 or j2 > n - 1: continue
                if g[i1][j1] < 0 or g[i2][j2] < 0:
                    ps = -1
                else:
                    down1left2 = i1 > 0 and j2 > 0 ? s[l-1][i1-1][i2] : -1
                    down1down2 = i1 > 0 and i2 > 0 ? s[l-1][i1-1][i2-1] : -1
                    left1down2 = j1 > 0 and i2 > 0 ? s[l-1][i1][i2-1] : -1
                    left1left2 = j1 > 0 and j2 > 0 ? s[l-1][i1][i2] : -1
                    ps = max(down1left2, down1down2, left1down2, left1left2)
                    if ps != -1:
                        if i1 != i2: ps = g[i1][j1] + g[i2][j2] + ps
                        else: ps = g[i1][j1] + ps
                    s[l][i1][i2] = ps
        return max(0, s[l-1][n-1][n-1])

Solution 3:
Reduce space complexity to O(n^2) based on solution 2
Given current steps l, we use s[(l-1) mod 2][i1][i2] as previous result for l-1 steps and s[l mod 2][i1][i2] for calculation of current result

Time complexity: O(n^3)
Space complexity: O(n^2)

    s0 = [[] for i in 0..n-1], s1 = [[] for i in 0..n-1]
    s = [s0, s1]
    for l in 1..2*n-1:
        upper = min(l+1, n)
        pidx = (l-1) % 2
        cidx = l % 2
        for i1 in 0..upper:
            j1 = l - i1
            if j1 < 0 or j1 > n - 1: continue
            for i2 in 0..upper:
                j2 = l - j2
                if j2 < 0 or j2 > n - 1: continue
                if g[i1][j1] < 0 or g[i2][j2] < 0:
                    ps = -1
                else:
                    down1left2 = i1 > 0 and j2 > 0 ? s[pidx][i1-1][i2] : -1
                    down1down2 = i1 > 0 and i2 > 0 ? s[pidx][i1-1][i2-1] : -1
                    left1down2 = j1 > 0 and i2 > 0 ? s[pidx][i1][i2-1] : -1
                    left1left2 = j1 > 0 and j2 > 0 ? s[pidx][i1][i2] : -1
                    ps = max(down1left2, down1down2, left1down2, left1left2)
                    if ps != -1:
                        if i1 != i2: ps = g[i1][j1] + g[i2][j2] + ps
                        else: ps = g[i1][j1] + ps
                    s[cidx][i1][i2] = ps
        return max(0, s[(l-1) % 2][n-1][n-1])         
    */
    public int cherryPickup(int[][] grid) {
        final int n = grid.length;
        if (n == 0) return 0;
        final int[][] s = new int[n][n];
        int i1 = 0, j1 = 0, i2 = 0, j2 = 0, c = 0;
        s[0][0] = grid[0][0];
        // Edge cases for player1 and player2
        for (int l = 1; l < 2*n-1; l++) {
            int downMax = Math.min(l, n-1);
            for (i1 = downMax, j1 = l - i1; i1 >= 0 && j1 < n; i1--, j1++) {
                for (i2 = downMax, j2 = l - i2; i2 >= 0 && j2 < n; i2--, j2++) {
                    if (grid[i1][j1] < 0 || grid[i2][j2] < 0) c = -1;
                    else {
                        int down1left2 = i1 > 0 && j2 > 0 ? s[i1-1][i2] : -1;
                        int down1down2 = i1 > 0 && i2 > 0 ? s[i1-1][i2-1] : -1;
                        int left1down2 = j1 > 0 && i2 > 0 ? s[i1][i2-1] : -1;
                        int left1left2 = j1 > 0 && j2 > 0 ? s[i1][i2] : -1;
                        c = Math.max(Math.max(down1left2,down1down2), Math.max(left1down2,left1left2));
                        if (c != -1) {
                            c += grid[i1][j1];
                            if (i1 != i2) c += grid[i2][j2];
                        }
                    }
                    s[i1][i2] = c;
                    System.out.println("--------------------------------------------------");
                    print(s);
                }
            }
        }
        return Math.max(0, s[n-1][n-1]);
    }

    private void print(int[][] s) {
        for (int[] si : s) {
            for (int n : si) {
                System.out.print(n+" ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        CherryPickup cherryPickup = new CherryPickup();
        int[][] grid = {{0,1},{1,0}};
        int ans = cherryPickup.cherryPickup(grid);
        System.out.println(ans);
    }
}
