package com.mezjh.blog.lambda;

/**
 * @author ZJH
 * @date 2021/1/5 18:16
 */
public class TestLambda2Salary implements TestLambda2Filter<User> {
    @Override
    public boolean run(User user) {
        return user.getSalary().compareTo(10000.0) == -1;
    }
}
