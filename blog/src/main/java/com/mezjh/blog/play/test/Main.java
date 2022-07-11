package com.mezjh.blog.play.test;

import org.apache.commons.lang3.RandomUtils;

import java.util.*;

/**
 * @author ZJH
 * @date 2021/11/1 17:02
 */
public class Main {

    public static final List<String> employees = new ArrayList<>(Arrays.asList("0101", "0102", "0103", "0104"));
    public static Map<Integer, String> employeeNumberMap = new HashMap<>();

    public static void main(String[] args) {
        Integer a = 2;
        Integer b = new Integer(128);
        int c = 128;
        System.out.println(a == b);
        System.out.println(b == c);

    }

    public static Integer buildNumber(int empCount) {
        return RandomUtils.nextInt(0, empCount);
    }
}
