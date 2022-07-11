package com.mezjh.blog.lambda;

import java.math.BigDecimal;

/**
 * Lambda表达式基础语法
 * Java8中引入了一个新的操作符 "->" 称为箭头操作符或Lambda操作符 将Lambda表达式拆分成两部分
 * 左边:表达式的参数列表 也就是接口方法的参数(函数式接口:只有一个方法的接口)
 * 右边:表达式中需要执行的功能 也就是Lambda体
 *
 * 语法格式一：无参数，无返回值
 *       () -> System.out.println("");
 *
 * @author ZJH
 * @date 2021/1/7 17:13
 */
public class TestLambda3 {

    public static void main(String[] args)  {
        System.out.println(5534/500);
    }

    public static Object get(Object object) {
        return (object == null ? BigDecimal.ZERO :  object);

    }
}
