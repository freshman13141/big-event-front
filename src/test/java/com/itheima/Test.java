package com.itheima;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        // 分离奇数和偶数
        int[] even = Arrays.stream(nums).filter(n -> n % 2 == 0).toArray();
        int[] odd = Arrays.stream(nums).filter(n -> n % 2!= 0).toArray();

        int maxEven = 0;
        if (even.length > 0) {
            maxEven = Arrays.stream(even).max().getAsInt();
        }

        int minOdd = 0;
        if (odd.length > 0) {
            minOdd = Arrays.stream(odd).min().getAsInt();
        }

        System.out.println("最大的偶数: " + maxEven);
        System.out.println("最小的奇数: " + minOdd);
    }
}
