package com.example.study.demo.algorithms;

public class NumberUtils {
    public static void main(String[] args) {
        System.out.print(reverse(1463847412));
    }

    /**
     * 数字反转
     * 思路：注意反转过程中会溢出，因此需要找出判断溢出的方式
     * @param x
     * @return
     */
    public static int reverse(int x) {
        int ans = 0;
        while (x != 0) {
            // 判断是否存在溢出
            if ((ans * 10) / 10 != ans) {
                return 0;
            }

            // 取最后的个位数
            ans = ans * 10 + x % 10;
            // 将原数据除10
            x = x / 10;
        }
        return ans;

//        判断long转int后的值与原值是否相等来出来溢出的情况
//        long n = 0;
//        while(x != 0) {
//            n = n*10 + x%10;
//            x = x/10;
//        }
//        return (int)n==n? (int)n:0;
    }
}
