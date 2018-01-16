package leetcode;

public class LongestPalindromicSubstring {

    /*
     * Define dp to be a state indicating whether substring [i, j] is a palindrome
     * Then we have:
     * dp(i, j) = 1 if i = j
     *          = 1 if i + 1 = j and s[i] = s[j]
     *          = 1 if dp(i+1, j-1) = 1 and s[i] = s[j]
     *          = 0 otherwise
     */
    static String solution(String s) {
        final int len = s.length();
        int sIdx = 0, maxLen = 0;
        final boolean[] dp = new boolean[len];
        for (int i = len - 1; i >= 0; i--) {
            for (int j = len - 1; j >= i; j--) {
                if (i == j) dp[j] = true;
                else if (i + 1 == j) dp[j] = s.charAt(i) == s.charAt(j);
                else dp[j] = s.charAt(i) == s.charAt(j) && dp[j-1];
                if (dp[j] && (j - i + 1) > maxLen) {
                    maxLen = (j - i + 1);
                    sIdx = i;
                }
            }
        }
        return s.substring(sIdx, sIdx + maxLen);
    }

    public static void main(String[] args) {
        System.out.println(solution("ababa"));
    }
}
