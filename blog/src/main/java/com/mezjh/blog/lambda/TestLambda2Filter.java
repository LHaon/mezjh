package com.mezjh.blog.lambda;

/**
 * @author ZJH
 * @date 2021/1/5 18:06
 */
public interface TestLambda2Filter<T> {
    boolean run(T t);
}
