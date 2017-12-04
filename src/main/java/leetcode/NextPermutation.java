package leetcode;

public class NextPermutation {

    static void solution(int[] nums) {
        int idx1 = nums.length - 2;
        for (; idx1 >= 0 && nums[idx1] >= nums[idx1+1]; idx1--);
        if (idx1 >= 0) {
            int idx2 = idx1 + 1;
            for (; idx2 < nums.length && nums[idx2] > nums[idx1]; idx2++);
            // swap idx1 and idx2
            swap(nums, idx1, idx2 - 1);
        }
        // reverse from idx1+1 to the end of the nums
        reverse(nums, idx1 + 1, nums.length);
    }

    private static void reverse(int[] nums, int start, int end) {
        for (int i = start, j = end - 1; i <= j; i++, j--) {
            swap(nums, i, j);
        }
    }


    private static void swap(int[] nums, int idx1, int idx2) {
        int tmp = nums[idx1];
        nums[idx1] = nums[idx2];
        nums[idx2] = tmp;
    }


    public static void main(String[] args) {
        int [] nums = {7,6,5,4};
        solution(nums);
        for (int num : nums) {
            System.out.print(num);
            System.out.print(" ");
        }
    }
}
