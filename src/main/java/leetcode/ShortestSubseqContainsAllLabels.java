package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ShortestSubseqContainsAllLabels {

    private static List<Integer> solution(List<String> availableLabels, List<String> targetLabels) {
        Set<String> labels = new HashSet<>(availableLabels);
        Map<String, Integer> appearTimes = new HashMap<>();
        int rstart = -1, rend = -1, start = 0, end = 0, idx = 0, len = targetLabels.size(), avLen = availableLabels.size(), currLen = len, maxLen = len;
        String currLabel = null;
        for (start = 0, end = 0, idx = 0; idx < len; idx++) {
            currLabel = targetLabels.get(idx);
            if (!labels.contains(currLabel)) continue;
            start = idx;
            end = idx;
            boolean foundEnd = false, foundStart = false;
            //Moving end ahead until all labels are found or reached len
            for (;end < len; end++) {
                currLabel = targetLabels.get(end);
                if (labels.contains(currLabel)) {
                    incAppearTimes(appearTimes, currLabel);
                }
                if (appearTimes.size() == avLen) {
                    foundEnd = true;
                    break;
                }
            }

            //Moving start ahead until the label appears only once or reached end
            for (;start < end; start++) {
                currLabel = targetLabels.get(start);
                Integer times = appearTimes.get(currLabel);
                if (times != null && times == 1) {
                    foundStart = true;
                    break;
                }
                if (labels.contains(currLabel)) {
                    decAppearTimes(appearTimes, currLabel);
                }
            }

            if (foundEnd && foundStart) {
                currLen = end - start;
                maxLen = maxLen > currLen ? currLen : maxLen;
                rstart = start;
                rend = end;
            }
            idx = end + 1;
            appearTimes.clear();
        }
        return rstart == -1 ? Arrays.asList(0) : Arrays.asList(rstart, rend);
    }

    private static void incAppearTimes(Map<String, Integer> appearTimes, String label) {
        Integer times = appearTimes.get(label);
        appearTimes.put(label, times == null ? 1 : times + 1);
    }

    private static void decAppearTimes(Map<String, Integer> appearTimes, String label) {
        Integer times = appearTimes.get(label);
        if (times != null && times > 1) appearTimes.put(label, times - 1);
        else appearTimes.remove(label);
    }

    public static void main(String[] args) {
        List<String> availableLabels = Arrays.asList("rain", "the", "spain");
        List<String> targetLabels = Arrays.asList("the", "sun", "is", "rain", "but", "the", "in", "spain", "aaa", "bbb", "spain", "but", "the", "rain", "another");
        System.out.println(solution(availableLabels, targetLabels));
    }
}
