package leetcode;

public class KnightProbability {
    /*
      Leetcode 688
      On an NxN chessboard, a knight starts at the r-th row and c-th column and attempts to make exactly K moves. The rows and columns are 0 indexed, so the top-left square is (0, 0), and the bottom-right square is (N-1, N-1).
      A chess knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, then one square in an orthogonal direction.
      Each time the knight is to move, it chooses one of eight possible moves uniformly at random (even if the piece would go off the chessboard) and moves there.

      The knight continues moving until it has made exactly K moves or has moved off the chessboard.
      Return the probability that the knight remains on the board after it has stopped moving.

      Example:
      Input: 3, 2, 0, 0
      Output: 0.0625
      Explanation: There are two moves (to (1,2), (2,1)) that will keep the knight on the board.
      From each of those positions, there are also two moves that will keep the knight on the board.
      The total probability the knight stays on the board is 0.0625.
     */
    public double solution(int N, int K, int r, int c) {
        if (K == 0) return 1.0;
        if (N < 3) return 0.0;
        final double[][][] dp = new double[K][N][N];
        for (int k = 0; k < K; k++)
            for (int i = 0; i < N; i++)
                for (int j = 0; j < N; j++) {
                    dp[k][i][j] = -1.0;
                }
        return knightProbabilityRecur(N, K, r, c, dp);
    }
    private double knightProbabilityRecur(int N, int K, int r, int c, double[][][] dp) {
        if (dp[K-1][r][c] != -1.0) return dp[K-1][r][c];
        double res = 0.0, tmp = 0.0;
        int r1,c1;
        int[] moves1 = {-2, 2}, moves2 = {-1, 1};

        for (int m1 : moves1) {
            for (int m2 : moves2) {
                r1 = r + m1;
                c1 = c + m2;
                if (r1 >= 0 && r1 < N && c1 >= 0 && c1 < N) {
                    tmp = 0.125;
                    if (K > 1) {
                        tmp *= knightProbabilityRecur(N, K-1, r1, c1, dp);
                    }
                    res += tmp;
                }
                r1 = r + m2;
                c1 = c + m1;
                if (r1 >= 0 && r1 < N && c1 >= 0 && c1 < N) {
                    tmp = 0.125;
                    if (K > 1) {
                        tmp *= knightProbabilityRecur(N, K-1, r1, c1, dp);
                    }
                    res += tmp;
                }
            }
        }
        //System.out.println("K="+K+",res="+ res);
        dp[K-1][r][c] = res;
        return res;
    }

    public static void main(String[] args) {
        KnightProbability knightProbability = new KnightProbability();
        System.out.println(knightProbability.solution(8, 30, 6, 4));
    }
}
