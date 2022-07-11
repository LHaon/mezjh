package com.mezjh.blog.principle.singleresponsibility;

/**
 * 没有做大的修改  在类级别上没有遵循单一职责原则
 * 但在方法级别上遵守了单一职责原则
 * @author ZJH
 * @date 2020/9/24 15:52
 */
public class Demo3 {
    public static void main(String[] args) {
        new AnimalPlus().moveInAir("小鸟");
        new AnimalPlus().moveInLoad("猫咪");
        new AnimalPlus().moveInWater("金鱼");
    }
}

class AnimalPlus {
    public void moveInAir(String name) {
        System.out.println(name + "在天上飞");
    }
    public void moveInLoad(String name) {
        System.out.println(name + "在地上跑");
    }
    public void moveInWater(String name) {
        System.out.println(name + "在水里游");
    }
}
