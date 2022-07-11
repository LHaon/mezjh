package com.mezjh.blog.lambda;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * Lambda表达式练习笔记 学习来源：b站搜索 Java8
 * 从Test1开始看
 * @author ZJH
 * @date 2021/1/5 16:04
 */
public class TestLambda1 {

    /**
     * 使用匿名内部类
     */
    public void test1() {
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        TreeSet<Integer> treeSet = new TreeSet<>(comparator);
    }

    /**
     * 使用Lambda表达式
     */
    public void test1S() {
        //Comparator<Integer> comparator = Integer::compare;
        Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);
        TreeSet<Integer> treeSet = new TreeSet<>(comparator);
    }
}
