package com.itheima;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Test001 {
    public static void main(String[] args) {
       /*  int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};
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
        System.out.println("最小的奇数: " + minOdd); */
        // String str = "Agsvv-nsh0kBgVFH";
        // System.out.println(ContainLetter(str));
        String oriTime = "2025-1-11";
        System.out.println(FormatDate(oriTime));

    }

    public static boolean ContainLetter(String s){
        boolean hasUpper = false;
        boolean hasLower = false;

        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (Character.isUpperCase(c)){
                hasUpper = true;
            } else if (Character.isLowerCase(c)) {
                hasLower = true;
            }
        }

        if (hasUpper && hasLower){
            return true;
        }
        return false;
    }
    public static String FormatDate(String date){
        String[] patters = {"yyyy-M-d","yyyy-MM-d","yyyy-M-dd"};
        if (date.matches("\\d{4}-\\d{2}-\\d{2}")){
            return date;
        }
        for (String patter : patters) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(patter);
            try {
                LocalDate newDate = LocalDate.parse(date,formatter);
                return newDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }catch (DateTimeParseException e){
                continue;
            }
        }
        return "-1";
    }

}
