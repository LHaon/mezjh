package com.mezjh.blog.principle.singleresponsibility;

/**
 * @author ZJH
 * @date 2020/9/24 15:40
 */
public class Demo1 {
    public static void main(String[] args) {
        new Animal().moveMethod("猫咪");
        new Animal().moveMethod("金鱼");
        new Animal().moveMethod("小鸟");
    }
}

/**
 * 动物类
 */
class Animal {
    /**
     * 动物的移动方式 有天上飞、地上跑、水里游的
     * 故违反单一职责原则 解决方案如Demo2  根据不同动物创建不同的类
     * @param name
     */
    public void moveMethod(String name) {
        System.out.println(name + "在地上跑");
    }
}
