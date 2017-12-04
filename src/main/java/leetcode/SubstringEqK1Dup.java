package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubstringEqK1Dup {

    private static List<String> solution(String input, int num) {
        List<String> res = new ArrayList<>();
        if (num > input.length()) return res;
        Map<Character, Integer> appearTimesOfChar = new HashMap<>();
        for (int i = 0; i < num; i++) {
            char c = input.charAt(i);
            incAppearTimes(appearTimesOfChar, c);
        }
        tryPopulateRes(input, num, appearTimesOfChar, 0, num - 1, res);
        for (int i = 1, j = i + num - 1; i <= input.length() - num; i++, j++) {
            decAppearTimes(appearTimesOfChar, input.charAt(i - 1));
            incAppearTimes(appearTimesOfChar, input.charAt(j));
            tryPopulateRes(input, num, appearTimesOfChar, i, j, res);
        }
        return res;
    }

    private static void tryPopulateRes(String input, int num, Map<Character, Integer> appearTimesOfChar, int i, int j, List<String> res) {
        if (appearTimesOfChar.size() == num - 1) {
            res.add(input.substring(i, j + 1));
        }
    }

    private static void incAppearTimes(Map<Character, Integer> appearTimesOfChar, char c) {
        boolean isNew = !appearTimesOfChar.containsKey(c);
        appearTimesOfChar.put(c, isNew ? 1 : appearTimesOfChar.get(c) + 1);
    }

    private static void decAppearTimes(Map<Character, Integer> appearTimesOfChar, char c) {
        boolean isNew = !appearTimesOfChar.containsKey(c);
        if (isNew) return;
        int times = appearTimesOfChar.get(c) - 1;
        if (times == 0) appearTimesOfChar.remove(c);
        else appearTimesOfChar.put(c, times);
    }


    public static void main(String[] args) {
        String input = "abcdcabefdcf";
        int num = 4;
        System.out.println(SubstringEqK1Dup.solution(input, num));
    }
}
