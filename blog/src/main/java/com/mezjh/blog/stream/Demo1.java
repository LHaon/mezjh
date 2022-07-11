package com.mezjh.blog.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Java 8 流 暂不更新...
 * @author ZJH
 * @date 2020/9/13 22:18
 */
public class Demo1 {
    public static void main(String[] args) {
        stream1();
    }

    public static void stream1() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(6);
        list.add(5);
        list.add(3);
        list.add(4);
        list.add(2);
        Stream<Integer> stream = list.parallelStream();
        System.out.println(stream.max(Integer::compareTo).get());

    }
}
