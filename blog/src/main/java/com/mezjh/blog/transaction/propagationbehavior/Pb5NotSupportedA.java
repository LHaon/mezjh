package com.mezjh.blog.transaction.propagationbehavior;

/**
 * Spring事务传播行为五：NotSupported 若当前存在事务，则会把当前事务挂起，以非事务的方式执行
 * @author ZJH
 * @date 2021/7/6 17:55
 */
public class Pb5NotSupportedA {
    // 事务方法A调用传播行为是NOT_SUPPORTED的事务B时，A被挂起，B以非事务方法执行，
    // B中发生异常A、B都不会回滚，A中发生异常只有A中的操作会被回滚
}
