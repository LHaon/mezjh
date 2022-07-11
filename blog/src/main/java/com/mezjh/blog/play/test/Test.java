package com.mezjh.blog.play.test;


import java.util.ArrayList;
import java.util.List;

/**
 * @author ZJH
 * @date 2021/7/1 18:21
 */
public class Test {
    public static void main(String[] args) throws Exception {
        List<String> list = new ArrayList<>();
        list.add("1");
        test(list);
        System.out.println(list.get(1));
    }

    public static void test(List<String> list) {
        list.add("2");
        list = new ArrayList<>();
        list.add("3");
        list.add("4");
    }
}
