package com.example.study.demo.algorithms;

public class DrawnStep {
    /**
     * 爬楼梯算法
     * 动态规划问题
     * 只能从n-1或n-2个阶梯登上n阶梯，所以要求出n-1和n-2的解，以此类推
     * @param n
     * @return
     */
    public int step(int n) {
        if (n == 1) {
            return 1;
        }

        if (n == 2) {
            return 2;
        }
        int res = 0;
        if (n > 3) {
            int a = 1, b = 2;
            for (int i = 3; i<= n; i++) {
                res = a + b;
                a = b;
                b = res;
            }
        }

        return res;
    }
}
