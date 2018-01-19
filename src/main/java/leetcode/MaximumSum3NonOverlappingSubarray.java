package leetcode;

import java.util.Arrays;

/*
    In a given array nums of positive integers, find three non-overlapping subarrays with maximum sum.

    Each subarray will be of size k, and we want to maximize the sum of all 3*k entries.

    Return the result as a list of indices representing the starting position of each interval (0-indexed). 
    If there are multiple answers, return the lexicographically smallest one.


    Input: [1,2,1,2,6,7,5,1], 2
    Output: [0, 3, 5]
    Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].
    We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically larger.
 */
public class MaximumSum3NonOverlappingSubarray {

    public int[] solution(int[] nums, int k) {
        final int n = nums.length;
        int[] sums = new int[n+1];
        sums[0] = nums[0];
        for (int i = 1; i < n; i++) {
            sums[i] = sums[i-1] + nums[i];
        }
        int[][] dp = new int[4][n];
        int[][] id = new int[4][n];

        for (int i = 1; i < 4; i++)
            for (int j = 0; j < n; j++) {
                int curMax = j - k < 0 ? sums[j] : dp[i-1][j-k] + sums[j] - sums[j-k];
                if (j - 1 < 0 || curMax > dp[i][j-1]) {
                    dp[i][j] = curMax;
                    id[i][j] = j - k + 1;
                    if (id[i][j] < 0) id[i][j] = 0;
                } else {
                    dp[i][j] = dp[i][j-1];
                    id[i][j] = id[i][j-1];
                }
            }

        System.out.println(Arrays.deepToString(dp));
        System.out.println(Arrays.deepToString(id));
        int[] ans = new int[3];
        ans[2] = id[3][n-1];
        ans[1] = id[2][ans[2]-1];
        ans[0] = id[1][ans[1]-1];
        return ans;
    }

    public static void main(String[] args) {
        final int[] nums = new int[]{1,2,1,2,6,7,5,1};
        final int k = 2;
        MaximumSum3NonOverlappingSubarray sol = new MaximumSum3NonOverlappingSubarray();
        int[] ans = sol.solution(nums, k);
        System.out.println(Arrays.toString(ans));
    }
}
