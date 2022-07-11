package com.mezjh.blog.lambda;

import java.util.ArrayList;
import java.util.List;

/**
 * Lambda表达式练习
 * @author ZJH
 * @date 2021/1/5 16:29
 */
public class TestLambda2 {
    private static List<User> users = new ArrayList<>();
    static {
        users.add(new User("张", 18, 8888.0));
        users.add(new User("李", 27, 16888.0));
        users.add(new User("王", 16, 3333.0));
        users.add(new User("赵", 50, 48888.0));
        users.add(new User("刘", 22, 12888.0));
        users.add(new User("陈", 32, 12222.0));
        users.add(new User("林", 13, 2222.0));
    }


    public static void main(String[] args) {
        //筛选年龄大于20的人
        System.out.println("-----------------原始方法----------------");
        for (User user : filterAge(users)) {
            System.out.println(user.toString());
        }
        System.out.println();
        //筛选工资小于10000的人
        for (User user : filterSalary(users)) {
            System.out.println(user.toString());
        }
        System.out.println("---------------优化方法一------------");
        //每筛选一次 都得写一个过滤方法
        //优化方法一： 策略模式
        // 年龄大于20
        List<User> var1 = filterByType(users, new TestLambda2Age());
        for (User user: var1) {
            System.out.println(user.toString());
        }
        System.out.println();
        // 工资大于10000
        List<User> var2 = filterByType(users, new TestLambda2Salary());
        for (User user: var2) {
            System.out.println(user.toString());
        }
        System.out.println("--------------优化方法二------------");
        //每有一个条件都要新new一个类去实现策略接口
        //优化方式二：匿名内部类
        List<User> var3 = filterByType(users, new TestLambda2Filter<User>() {
            @Override
            public boolean run(User user) {
                return user.getAge() > 20;
            }
        });
        for (User user: var3) {
            System.out.println(user.toString());
        }
        System.out.println();
        List<User> var4 = filterByType(users, new TestLambda2Filter<User>() {
            @Override
            public boolean run(User user) {
                return user.getSalary().compareTo(10000.0) == -1;
            }
        });
        for (User user: var4) {
            System.out.println(user.toString());
        }
        // 使用Lambda表达式
        System.out.println("--------------优化方法三------------");
        List<User> var5 = filterByType(users, user -> user.getAge() > 20);
        var5.forEach(System.out::println);
        System.out.println();
        List<User> var6 = filterByType(users, user -> user.getSalary().compareTo(10000.0) == -1);
        var6.forEach(System.out::println);
        //使用Stream API
        System.out.println("--------------优化方法四------------");
        users.stream().filter(x -> x.getAge() > 20).forEach(System.out::println);
        users.stream().map(User::getName).forEach(System.out::println);

    }

    /**
     * 筛选年龄大于20的人
     * @param users
     * @return
     */
    public static List<User> filterAge(List<User> users) {
        List<User> result = new ArrayList<>();
        for (User var : users) {
            if (var.getAge() > 20) {
                result.add(var);
            }
        }
        return result;
    }

    /**
     * 筛选工资小于10000的人
     * @param users
     * @return
     */
    public static List<User> filterSalary(List<User> users) {
        List<User> result = new ArrayList<>();
        for (User var : users) {
            if (var.getSalary().compareTo(10000.0) == -1) {
                result.add(var);
            }
        }
        return result;
    }

    /**
     * 根据指定策略过滤
     * @param users
     * @param type
     * @return
     */
    public static List<User> filterByType(List<User> users, TestLambda2Filter<User> type) {
        List<User> result = new ArrayList<>();
        for (User user : users) {
            if (type.run(user)) {
                result.add(user);
            }
        }
        return result;
    }
}
