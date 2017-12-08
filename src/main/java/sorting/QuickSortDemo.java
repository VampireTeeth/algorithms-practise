package sorting;

import java.util.Arrays;

public class QuickSortDemo {

    public void sort(int[] nums) {
        recurSort(nums, 0, nums.length - 1);
    }

    private void recurSort(int[] nums, int lo, int hi) {
        if (lo >= hi) return;
        int pivot = nums[hi];
        int i = -1;
        for (int j = 0; j < hi; j++) {
            if (pivot > nums[j]) {
                i++;
                swap(nums, i, j);
            }
        }
        swap(nums, i+1, hi);
        recurSort(nums, 0, i);
        recurSort(nums, i+2, hi);
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        int[] nums = { 30, 15, 20, 50, 42, 90, 78, 80, 65 };
        QuickSortDemo quickSort = new QuickSortDemo();
        quickSort.sort(nums);
        System.out.println(Arrays.toString(nums));
    }

}
