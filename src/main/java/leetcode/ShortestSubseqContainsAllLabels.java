package leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShortestSubseqContainsAllLabels {

    private static List<Integer> queueSolution(List<String> availableLabels, List<String> targetLabels) {
        Set<String> labels = new HashSet<>(availableLabels);
        int[] q = new int[targetLabels.size()];
        int rstart = -1, rend = -1, start = 0, end = 0, idx = 0, len = targetLabels.size();
        String currLabel = null;
        for (start = 0; start < len; q[start++] = -1);
        for (start = 0, end = 0, idx = 0; idx < len; idx++) {
            currLabel = targetLabels.get(idx);
            if (labels.contains(currLabel)) {
                if (q[start] != -1 && targetLabels.get(q[start]).equals(currLabel)) start++;
                q[end++] = idx;
            }
        }
        rstart = q[start];
        for (start = 0; start < len; q[start++] = -1);
        for (start = 0, end = 0, idx = len - 1; idx >= 0; idx--) {
            currLabel = targetLabels.get(idx);
            if (labels.contains(currLabel)) {
                if (q[start] != -1 && targetLabels.get(q[start]).equals(currLabel)) start++;
                q[end++] = idx;
            }
        }
        rend = q[start];
        return Arrays.asList(rstart, rend);
    }

    public static void main(String[] args) {
        List<String> availableLabels = Arrays.asList("rain", "the", "spain");
        List<String> targetLabels = Arrays.asList("the", "sun", "is", "rain", "but", "the", "in", "spain", "aaa", "bbb", "spain");
        System.out.println(queueSolution(availableLabels, targetLabels));
    }
}
