package com.mezjh.blog.transaction.propagationbehavior;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Spring事务传播行为一：REQUIRED 若当前存在事务，则加入到该事务，若不存在事务，则新建一个事务
 * 由于@Transactional注解默认的传播行为就是REQUIRED，所以在这里未注明传播行为
 * @author ZJH
 * @date 2021/7/6 16:44
 */
@Service
public class Pb1RequiredA {

    @Resource
    private Pb1RequiredB pb1RequiredB;
    @Resource
    private PbUserMapper pbUserMapper;

    /**
     *  若当前不存在事务 则会新建一个事务
     *  结论:
     *  1.A1非事务方法，B1是事务方法，A1调用B1，B1会新建一个事务
     *  2.无论是在A1还是在B1中发生异常，A1的操作都不会回滚。B1的回滚仅发生在B1发生异常时。
     */
    public void methodA1() {
        List<PbUser> allUser = pbUserMapper.getAllUser();
        allUser.forEach(x -> pbUserMapper.workAdd(x.getId()));
        pb1RequiredB.methodB1();
        throw new RuntimeException();
    }

    /**
     * 若当前存在事务，则加入到该事务中
     * 结论：
     * 1.A2和B1都是事务方法，A2中调用B1，B1会加入到A2的事务中，并和A2的事务一起提交
     * 2.无论是在A2还是在B1中发生异常，A2和B1的操作都会被回滚
     */
    @Transactional(rollbackFor = Exception.class)
    public void methodA2() {
        List<PbUser> allUser = pbUserMapper.getAllUser();
        pbUserMapper.id1Add();
        try {
            pb1RequiredB.methodB1();
        } catch (Exception e) {

        }
        List<PbUser> allUser2 = pbUserMapper.getAllUser();
        throw new RuntimeException();
    }

    @Service
    public class Pb1RequiredB {

        @Resource
        private PbUserMapper pbUserMapper;

        @Transactional(rollbackFor = Exception.class)
        public void methodB1() {
            List<PbUser> allUser = pbUserMapper.getAllUser();
            pbUserMapper.id2Add();
            List<PbUser> allUser2 = pbUserMapper.getAllUser();
//            throw new RuntimeException();
        }
    }
}
