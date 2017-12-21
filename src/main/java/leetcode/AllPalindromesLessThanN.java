package leetcode;

import java.util.ArrayList;
import java.util.List;

public class AllPalindromesLessThanN {

    List<Long> solution(long n) {
        List<Long> ans = new ArrayList<>();
        long palin = 0;
        for (int i = 1;(palin = createPalin(i, 10, true)) < n;i++, ans.add(palin));
        for (int i = 1;(palin = createPalin(i, 10, false)) < n;i++, ans.add(palin));
        return ans;
    }

    /**
     * This is the function creating palindrome from a input
     * for example:
     * Given isOdd = true, input = 567, then output = 56765
     * Given isOdd = false, input = 567, then output = 567765
     * @param input
     * @param b
     * @param isOdd
     * @return
     */
    private Long createPalin(long input, int b, boolean isOdd) {
        long n = input, palin = input;
        if (isOdd) n /= b;
        while (n > 0) {
            palin = palin * b + n % b;
            n /= b;
        }
        return palin;
    }

    public static void main(String[] args) {
        List<Long> ans = new AllPalindromesLessThanN().solution(1024L);
        System.out.println(ans);
    }
}
