package com.mezjh.blog.principle.singleresponsibility;

/**
 * 该设计虽然遵循了单一职责原则  但其分解成本较高 类较多
 * 解决方案如 Demo3 修改Demo1 将其分为三个方法
 * @author ZJH
 * @date 2020/9/24 15:46
 */
public class Demo2 {
    public static void main(String[] args) {
        new LoadAnimal().moveMethod("猫咪");
        new WaterAnimal().moveMethod("金鱼");
        new AirAnimal().moveMethod("小鸟");
    }
}

class AirAnimal {
    /**
     * 天上飞的动物
     * @param name
     */
    public void moveMethod(String name) {
        System.out.println(name + "在天上飞");
    }
}

class LoadAnimal {
    /**
     * 地上跑的动物
     * @param name
     */
    public void moveMethod(String name) {
        System.out.println(name + "在地上跑");
    }
}

class WaterAnimal {
    /**
     * 水里游的动物
     * @param name
     */
    public void moveMethod(String name) {
        System.out.println(name + "在水里游");
    }
}

