package leetcode;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringNoRepeatChar {

    static int solution(String s) {
        int maxLength = 0;
        int minIdx = 0;
        Map<Character, Integer> cache = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            Integer prevIdx = cache.get(c);
            minIdx = (prevIdx != null && minIdx <= prevIdx) ? prevIdx + 1 : minIdx;
            cache.put(c, i);
            maxLength = Math.max(maxLength, i - minIdx + 1);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        int res = LongestSubstringNoRepeatChar.solution("yewwkew");
        System.out.println(res);
    }
    
}
