package com.mezjh.blog.transaction.propagationbehavior;

/**
 * Spring事务传播行为三： Mandatory 支持当前事务，若当前没有事务，则抛出异常
 * @author ZJH
 * @date 2021/7/6 17:54
 */
public class Pb3MandatoryA {
    // 非事务方法A 调用传播行为时Mandatory的事务方法B时， 调用前的所有操作都不会被回滚
}
