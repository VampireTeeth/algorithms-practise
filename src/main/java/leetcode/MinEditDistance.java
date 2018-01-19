package leetcode;

public class MinEditDistance {

    public int solution(String s, String t) {
        final int ns = s.length(), nt = t.length();
        final int[][] dp = new int[ns+1][nt+1];
        dp[0][0] = 0;
        for (int i = 1; i < ns+1; i++) {
            dp[i][0] = dp[i-1][0] + 1;
        }
        for (int i = 1; i < nt+1; i++) {
            dp[0][i] = dp[0][i-1] + 1;
        }
        for (int i = 1; i < ns+1; i++) {
            for (int j = 1; j < nt+1; j++) {
                boolean matching = s.charAt(i-1) == t.charAt(j-1);
                int costByInsert = dp[i][j-1] + 1, costByDelete = dp[i-1][j] + 1, costByReplace = dp[i-1][j-1] + (matching ? 0 : 1);
                dp[i][j] = Math.min(Math.min(costByInsert, costByDelete), costByReplace);
            }
        }
        printTable(dp);
        return dp[ns][nt];
    }

    public void printTable(int[][] t) {
        System.out.format("%5s", " ");
        for (int i = 0; i < t[0].length; i++){
            System.out.format("%5d", i);
        }
        System.out.println();
        for (int i = 0; i < t.length; i++) {
            int[] r = t[i];
            System.out.format("%5d", i);
            for (int c : r) {
                System.out.format("%5d", c);
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        MinEditDistance minEditDistance = new MinEditDistance();
        int ans = minEditDistance.solution("thou shall not", "you should not");
        System.out.println("The answer is: " + ans);
    }
    
}
