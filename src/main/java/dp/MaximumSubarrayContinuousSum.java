package dp;

import java.util.HashMap;
import java.util.Map;

public class MaximumSubarrayContinuousSum {

    int[] solution(int[] arr) {
        int[] res = new int[3];
        res[0] = Integer.MIN_VALUE;
        Map<Integer, Integer> c = new HashMap<>();
        c.put(0, arr[0]);
        maxSumEndsAt(arr, arr.length - 1, c, res);
        return res;
    }

    private int maxSumEndsAt(int[] arr, int i, Map<Integer, Integer> cache, int[] res) {
        if (cache.containsKey(i)) return cache.get(i);
        int c1 = arr[i] + maxSumEndsAt(arr, i-1, cache, res), c2 = arr[i], maxSum = Math.max(c1, c2);
        if (maxSum > res[0]) {
            res[0] = maxSum;
            res[1] = maxSum == c1 ? res[1] : i;
            res[2] = i;
        }
        cache.put(i, maxSum);
        return maxSum;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{-2,-3,4,-1,-2,1,5,-3};
        MaximumSubarrayContinuousSum m = new MaximumSubarrayContinuousSum();
        int[] r = m.solution(arr);
        System.out.format("max=%d,lo=%d,hi=%d\n", r[0], r[1], r[2]);
    }
}
