package leetcode;


public class Solution198 {
    public static int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int[] result = new int[nums.length + 1];
        result[1] = nums[0];
        for (int i = 2; i <= nums.length; ++i) {
            result[i] = Math.max(result[i - 2] + nums[i - 1], result[i - 1]);
        }
        return result[nums.length];
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        System.out.println(rob(nums));

        nums = new int[]{2, 7, 9, 3, 1};
        System.out.println(rob(nums));

        nums = new int[]{2, 1, 1, 2};
        System.out.println(rob(nums));
    }
}
