package com.math;

public class Math {

    public static int removeDuplicates(int[] nums) {
        // 数组为空直接返回
        if (nums.length == 0) {
            return 0;
        }
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }
    public void rotate(int[] nums, int k) {
        int length = nums.length;
        int[] numsCopy = new int[length];
        for (int i = 0; i < length; i++) {
            numsCopy[(k+i)%length] = nums[i];
        }
        System.arraycopy(numsCopy,0,nums,0,length);
    }

    public void rotate2(int[] nums, int k) {
        k %= nums.length;
        reverse(nums,0,nums.length-1);
        reverse(nums,0,k-1);
        reverse(nums,k,nums.length);
    }

    public static void reverse(int[] nums, int start, int end) {
        while (start<end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        Math.removeDuplicates(new int[]{1});
    }
}
