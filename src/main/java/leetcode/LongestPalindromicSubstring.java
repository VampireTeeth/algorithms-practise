package leetcode;

public class LongestPalindromicSubstring {

    static String solution(String s) {
        int[][] dp = new int[s.length()][s.length()];
        int maxLen = 0;
        int sIdx = 0;

        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {
                boolean ijEq = s.charAt(i) == s.charAt(j);
                dp[i][j] = 
                        (i == j || // i == j dp[i][i] = 1
                        (i + 1 == j && ijEq) || // i + 1 == j dp[i][i+1] = (s[i]==s[j])
                        (dp[i+1][j-1] == 1 && ijEq)) ? 1 : 0;

                if (dp[i][j] == 1) {
                    int newLen = j - i + 1;
                    boolean isBetter = newLen > maxLen;
                    maxLen = isBetter ? newLen : maxLen;
                    sIdx = isBetter ? i : sIdx;
                }
            }
        }

        return s.substring(sIdx, sIdx + maxLen);
    }

    public static void main(String[] args) {
        System.out.println(solution("ababc"));
    }
}
