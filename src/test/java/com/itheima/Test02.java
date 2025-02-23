package com.itheima;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Test02 {
    public static void main(String[] args) {
        DateTimeFormatter[] formatters = {
                DateTimeFormatter.ofPattern("yyyy-MM-dd"),
                DateTimeFormatter.ofPattern("yyyy-MM-d"),
                DateTimeFormatter.ofPattern("yyyy-M-d"),
                DateTimeFormatter.ofPattern("yyyy-M-dd")
        };
        String s = "2024-11-1";
        LocalDate date = null;
        for (DateTimeFormatter formatter : formatters) {
            try {
                date = LocalDate.parse(s,formatter);
                break;
            }catch (Exception ignored){

            }
        }
        if (date!=null){
            System.out.println(date);
        }else {
            throw new RuntimeException();
        }

    }
}
