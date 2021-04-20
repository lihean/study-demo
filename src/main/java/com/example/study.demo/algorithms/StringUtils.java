package com.example.study.demo.algorithms;

import java.util.Arrays;

public class StringUtils {
    public static void main(String[] args) {
        int[] nums = {12,23,89,8,9,10,2,0};
        System.out.print(largestSortedNums(nums));
    }

    /**
     * 给定一个整型数组，求重新排列后最大的数字，要求返回字符串
     * 思路：比较 S1 + S2 与 S2 + S1
     * @param nums
     * @return
     */
    public static String largestSortedNums(int[] nums) {
        int n = nums.length;
        if (n == 1) return String.valueOf(nums[0]);
        String[] arr = new String[n];
        for (int i=0; i < n; i++) {
            arr[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(arr, (a, b) -> {
            return (b + a).compareTo(a + b);
        });

        if (arr[0].equals("0")) {
            return "0";
        }

        StringBuilder sb = new StringBuilder(16);
        for (int i = 0; i < n; i++) {
            sb.append(arr[i]);
        }

        return sb.toString();
    }
}
