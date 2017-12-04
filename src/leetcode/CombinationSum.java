package leetcode;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {

    static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < candidates.length; i++) {
            res.add(new ArrayList<>());
        }
        for (int m = 1; m <= target; m++) {
            for (int i = 0; i < candidates.length; i++) {
                int curr = candidates[i];
                if (m >= curr) {
                }
            }
        }
        return res;
    }
    
    private static class Cell {
        private boolean hasCombinations = false;
        private List<List<Integer>> combinations = new ArrayList<>();

        private void addCombination(List<Integer> combination) {
            this.combinations.add(new ArrayList<>(combination));
        }

        private void cloneFrom(Cell another) {
            this.hasCombinations = another.hasCombinations;
            this.combinations = new ArrayList<>();
            for (List<Integer> comb : another.combinations) {
                this.combinations.add(new ArrayList<>(comb));
            }
        }
    }
}
