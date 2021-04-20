package com.example.study.demo.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dp {

    public static void main(String[] args) {
        int[][] mm = {{1, 3, 5, 9}, {8, 1, 3, 4}, {5, 0, 6, 1}, {8, 8, 4, 0}};
        System.out.println(minPathSum(mm));

        int[] nn = {2, 1, 3, 4, 2, 7, 8, 2, 9};
        System.out.println(lengthOfLIS(nn));

        // 向上取整
        System.out.println(7 / 2);
        System.out.println(maxLengthOfString("abcadf"));
        int[] aa = {-1, 0, 2, -2, 3, -3, 1, -1, 0, 0};
        System.out.println(threeSum(aa));


    }

    /**
     * 判断是否为回文字符串
     *
     * @param source
     */
    public static void circleString(String source) {

    }

    /**
     * 查找最大回文字子符串
     *
     * @param s
     */
    public static String maxCircleString(String s) {
        int len = s.length();
        // 长度为1则一定是回文字符串，返回自身
        if (len < 2) {
            return s;
        }

        int maxLen = 1;
        int begin = 0;
        // dp[i][j] 表示 s[i..j] 是否是回文串
        boolean[][] dp = new boolean[len][len];
        // 初始化：所有长度为 1 的子串都是回文串
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        char[] charArray = s.toCharArray();
        // 递推开始
        // 先枚举子串长度
        for (int L = 2; L <= len; L++) {
            // 枚举左边界，左边界的上限设置可以宽松一些
            for (int i = 0; i < len; i++) {
                // 由 L 和 i 可以确定右边界，即 j - i + 1 = L 得
                int j = L + i - 1;
                // 如果右边界越界，就可以退出当前循环
                if (j >= len) {
                    break;
                }

                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                // 只要 dp[i][L] == true 成立，就表示子串 s[i..L] 是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    /**
     * 查找最大回文字子符串
     *
     * @param s
     */
    public static String maxCircleString2(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    /**
     * 判断是否是回文字符串
     *
     * @param s
     * @param left
     * @param right
     * @return
     */
    public static int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
        }
        return right - left - 1;
    }


    /**
     * 最短路径
     * 思路：利用动态规划思想，每一步都只能向右或向下走，则有：dp[i][j] = min(dp[i - 1][j], dp[i][j - 1]) + matrix[i][j]
     * 先初始化第一行和第一列的路径，
     *
     * @param matrix int整型二维数组 the matrix
     * @return int整型
     */
    public static int minPathSum(int[][] matrix) {
        // write code here
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] dp = new int[n][m];
        dp[0][0] = matrix[0][0];
        // 第一行
        for (int i = 1; i < m; i++)
            dp[0][i] = dp[0][i - 1] + matrix[0][i];
        // 第一列
        for (int i = 1; i < n; i++)
            dp[i][0] = dp[i - 1][0] + matrix[i][0];

        // 双循环
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j]) + matrix[i][j];
            }
        }
        return dp[n - 1][m - 1];
    }

    /**
     * 最长上升子序列
     * 思路：动态规划，d[i] = max(d[i], d[j] + 1)，前提是nums[i] > nums[j]
     *
     * @param nums
     * @return
     */
    public static int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxans = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxans = Math.max(maxans, dp[i]);
        }
        return maxans;
    }

    /**
     * 最大不重复子字符串
     * 思路：利用标准ascii码的范围是0~127，初始化长度为128的数组用于保存每个字符最后出现的位置下标
     *
     * @param s
     * @return
     */
    public static int maxLengthOfString(String s) {
        // 记录字符上一次出现的位置
        int[] last = new int[128];
        for (int i = 0; i < 128; i++) {
            last[i] = -1;
        }
        int n = s.length();

        int res = 0;
        int start = 0; // 窗口开始位置
        for (int i = 0; i < n; i++) {
            int index = s.charAt(i);
            // 因为数组值初始为-1，因此未出现重复字符之前start均为0，出现重复字符后start为该字符上次出现的位置 + 1
            start = Math.max(start, last[index] + 1);
            res = Math.max(res, i - start + 1);
            last[index] = i;
        }

        return res;
    }

    /**
     * 寻找相加为0 的三个元素（去除重复组合）
     * 思路：排序 + 双指针
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        //排序
        Arrays.sort(nums);
        //双指针
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (nums[i] > 0) return lists;

            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int curr = nums[i];
            int L = i + 1, R = len - 1;
            while (L < R) {
                int tmp = curr + nums[L] + nums[R];
                if (tmp == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(curr);
                    list.add(nums[L]);
                    list.add(nums[R]);
                    lists.add(list);
                    while (L < R && nums[L + 1] == nums[L]) ++L;
                    while (L < R && nums[R - 1] == nums[R]) --R;
                    ++L;
                    --R;
                } else if (tmp < 0) {
                    ++L;
                } else {
                    --R;
                }
            }
        }
        return lists;
    }
}
