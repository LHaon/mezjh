package com.mezjh.blog.lambda;

/**
 * @author ZJH
 * @date 2021/1/5 18:08
 */
public class TestLambda2Age implements TestLambda2Filter<User> {
    @Override
    public boolean run(User user) {
        return user.getAge() > 20;
    }
}
