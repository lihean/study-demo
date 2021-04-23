package com.example.study.demo.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class StringUtils {
    public static void main(String[] args) {
        System.out.println(longestPalindrome1("abdbahjh"));
        System.out.println(longestPalindrome2("abdbahjh"));
        System.out.println(longestPalindrome3("abdbahjh"));

        System.out.println(strStr("hello",
                "ll"));

        System.out.println(marsCalculate("7#6$5#12"));

        System.out.println(reverseLeftWords("abcdefghijklmn", 5));
    }

    /**
     * 最大回文子字符串
     * 思路：中心扩散法
     *
     * @param source
     * @return
     */
    public static String longestPalindrome1(String source) {
        if (source == null || source.length() == 0) {
            return "";
        }

        int n = source.length();
        int maxStart = 0, maxLen = 0;
        int left = 0, right = 0, len = 1;
        for (int i = 0; i < n; i++) {
            left = i - 1;
            right = i + 1;
            // 考虑字符串长度为奇偶数的情况
            while (left >= 0 && source.charAt(i) == source.charAt(left)) {
                len++;
                left--;
            }
            while (right < n && source.charAt(i) == source.charAt(right)) {
                len++;
                right++;
            }
            while (left >= 0 && right < n && source.charAt(left) == source.charAt(right)) {
                len += 2;
                left--;
                right++;
            }

            if (maxLen < len) {
                maxStart = left;
                maxLen = len;
            }
            len = 1;
        }

        return source.substring(maxStart + 1, maxStart + maxLen + 1);
    }

    /**
     * 最大回文子字符串
     * 思路：动态规划
     *
     * @param s
     * @return
     */
    public static String longestPalindrome2(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        int strLen = s.length();
        int maxStart = 0;  //最长回文串的起点
        int maxEnd = 0;    //最长回文串的终点
        int maxLen = 1;  //最长回文串的长度

        boolean[][] dp = new boolean[strLen][strLen];

        for (int r = 1; r < strLen; r++) {
            for (int l = 0; l < r; l++) {
                // 初始状态和状态转移方程
                if (s.charAt(l) == s.charAt(r) && (r - l <= 2 || dp[l + 1][r - 1])) {
                    dp[l][r] = true;
                    if (r - l + 1 > maxLen) {
                        maxLen = r - l + 1;
                        maxStart = l;
                        maxEnd = r;

                    }
                }

            }
        }
        return s.substring(maxStart, maxEnd + 1);
    }

    /**
     * 最大回文子字符串
     *
     * @param source
     * @return
     */
    public static String longestPalindrome3(String source) {
        if (source == null || source.length() == 0) {
            return "";
        }

        int n = source.length();
        String res = "";
        for (int i = 0; i < n; i++) {
            // 考虑字符串长度为奇偶数的情况
            String len1 = palindromeIndex(source, i, i);
            String len2 = palindromeIndex(source, i, i + 1);
            //分别与res做对比，取较大值
            res = res.length() >= len1.length() ? res : len1;
            res = res.length() >= len2.length() ? res : len2;
        }

        return res;
    }

    /**
     * 求取最大回文子字符串长度
     *
     * @param source
     * @param left
     * @param right
     * @return String
     */
    public static String palindromeIndex(String source, int left, int right) {
        while (left >= 0 && right < source.length() && source.charAt(left) == source.charAt(right)) {
            left--;
            right++;
        }

        // left已经减1，所以要加1
        return source.substring(left + 1, right);
    }

    /**
     * 从 haystack 中查找 needle 第一次出现的index
     *
     * @param haystack
     * @param needle
     * @return
     */
    public static int strStr(String haystack, String needle) {
        if ("".equals(haystack) && "".equals(needle)) {
            return 0;
        }
        if ("".equals(haystack)) {
            return -1;
        }
        if ("".equals(needle)) {
            return 0;
        }

        int len = haystack.length();
        int len2 = needle.length();

        char[] source = haystack.toCharArray();
        char[] target = needle.toCharArray();
        char first = target[0];
        int max = len - len2;
        for (int i = 0; i <= max; i++) {
            if (source[i] != first) {
                while (++i <= max && source[i] != first) ;
            }

            if (i <= max) {
                int j = i + 1;
                int end = j + len2 - 1;
                for (int k = 1; j < end && source[j]
                        == target[k]; j++, k++)
                    ;

                if (j == end) {
                    return i;
                }
            }
        }
        return -1;
    }

    public int strStr1(String haystack, String needle) {
        if ("".equals(haystack) && "".equals(needle)) {
            return 0;
        }
        if ("".equals(haystack)) {
            return -1;
        }
        if ("".equals(needle)) {
            return 0;
        }

        int index = -1;
        int len = haystack.length();
        int len2 = needle.length();
        for (int i = 0; i < len - len2 + 1; i++) {
            boolean has = true;
            for (int j = i; j < i + len2; j++) {
                if (haystack.charAt(j) != needle.charAt(j - i)) {
                    has = false;
                    break;
                }
            }

            if (has) {
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * 火星符号计算
     * 思路：数组，栈
     * 先将字符串按照规则转换为字符串数组，内容是数字和运算符
     * 再遍历数组入栈，该过程中先计算$
     * 元素出栈
     *
     * @param source
     * @return
     */
    public static int marsCalculate(String source) {
        if (source.charAt(0) == '$' || source.charAt(0) == '#') {
            return -1;
        }

        int len = source.length();

        if (source.charAt(len - 1) == '$' || source.charAt(len - 1) == '#') {
            return -1;
        }

        char[] chars = source.toCharArray();

        int start = 0, end = 0;
        List<String> numStrs = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            if (chars[i] == '$' || chars[i] == '#') {
                end = i;
                numStrs.add(source.substring(start, end));
                start = i + 1;
                numStrs.add(String.valueOf(chars[i]));
            } else if (chars[i] - '0' >= 0 && chars[i] - '0' <= 9) {
                if (i == len - 1) {
                    numStrs.add(source.substring(start, len));
                }
            } else {
                return -1;
            }
        }

        System.out.println(numStrs);
        LinkedList<String> stack = new LinkedList<String>();

        for (int i = numStrs.size() - 1; i >= 0; i--) {
            String num = numStrs.get(i);
            if (!"#".equals(num) && !"$".equals(num)) {
                stack.add(num);
            } else {
                if ("$".equals(num)) {
                    --i;
                    int temp = 3 * Integer.parseInt(numStrs.get(i)) + Integer.parseInt(stack.removeLast()) + 2;
                    if (temp < 0 || temp >= Integer.MAX_VALUE) {
                        System.out.println("溢出1");
                        return -1;
                    }

                    stack.add(String.valueOf(temp));
                } else {
                    stack.add(num);
                }
            }
        }

        int sum = Integer.parseInt(stack.removeLast());
        String last = null;
        while (!stack.isEmpty()) {
            last = stack.removeLast();
            if ("#".equals(last)) {
                sum = 2 * sum + 3 * Integer.parseInt(stack.removeLast()) + 4;
            }
        }

        if (sum < 0 || sum >= Integer.MAX_VALUE) {
            System.out.println("溢出");
            return -1;
        }
        return sum;
    }

    /**
     * 左旋字符串
     *
     * @param s
     * @param n
     * @return
     */
    public static String reverseLeftWords(String s, int n) {
        String res = null;
        if (null == s || "".equals(s)) {
            return res;
        }
        int len = s.length();
        if (n < 0 || n > len) {
            return res;
        }
//
        char[] chars = s.toCharArray();
//        for (int i = 0; i < len; i++) {
//            chars[i] = s.charAt(i);
//        }
//
//        for (int i = 0; i < n; i++) {
//            chars[len + i] = s.charAt(i);
//        }

        StringBuilder sb = new StringBuilder(n + len);
//        for (int i = n; i < len; i++) {
//            sb.append(chars[i]);
//        }
//
//        for (int i = 0; i < n; i++) {
//            sb.append(chars[i]);
//        }

        for(int i = n; i < n + s.length(); i++)
            sb.append(chars[i % s.length()]);

        return sb.toString();
    }
}
