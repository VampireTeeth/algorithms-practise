package sorting;

import java.util.Arrays;

public class MergeSortDemo {

	public int[] sort(int[] nums) {
		return recurSort(nums, 0, nums.length-1);
	}

	private int[] recurSort(int[] nums, int lo, int hi) {
		if (lo == hi) return new int[]{nums[lo]};
		if (lo + 1 == hi) return new int[]{Math.min(nums[lo], nums[hi]), Math.max(nums[lo], nums[hi])};
		int[] res = new int[hi-lo+1];
		int mid = (lo + hi) >> 1;
		int[] right = recurSort(nums, lo, mid);
		int[] left = recurSort(nums, mid+1, hi);
		for (int i = 0, j = 0, k = 0; i < right.length || j < left.length; k++) { 
			if (i == right.length) res[k] = left[j++];
			else if (j == left.length) res[k] = right[i++];
			else res[k] = (right[i] < left[j]) ? right[i++] : left[j++];
		}
		return res;
	}

	public static void main(String[] args) {
        int[] nums = { 30, 15, 20, 50, 42, 90, 78, 80, 65, 65};
        MergeSortDemo mergeSort = new MergeSortDemo();
        System.out.println(Arrays.toString(mergeSort.sort(nums)));
	}
}
