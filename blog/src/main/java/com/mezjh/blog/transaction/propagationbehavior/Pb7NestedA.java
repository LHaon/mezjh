package com.mezjh.blog.transaction.propagationbehavior;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Spring事务传播行为七：Nested 若当前存在事务，则在嵌套事务内执行，若当前没有事务，则按照Required的方式执行
 * @author ZJH
 * @date 2021/7/6 17:56
 */
@Service
public class Pb7NestedA {

    @Resource
    private PbUserMapper pbUserMapper;
    @Resource
    private Pb7NestedB pb7NestedB;

    /**
     * 若当前存在事务，则嵌套执行（与当前事务一起提交），若当前没有事务，则同Required
     * 结论：
     * 1.A1与B1都是事务方法，B1嵌套A1执行，与A1一起提交
     * 2.B1中的异常只会引起B1中的操作回滚，A1中的异常会让A1、B1的操作都回滚
     */
    @Transactional(rollbackFor = Exception.class)
    public void methodA1() {
        List<PbUser> allUser = pbUserMapper.getAllUser();
        pbUserMapper.id1Add();
        try {
            pb7NestedB.methodB1();
        } catch (Exception e) {

        }
        List<PbUser> allUser2 = pbUserMapper.getAllUser();
//        throw new RuntimeException();
    }

    @Service
    public class Pb7NestedB {

        @Resource
        private PbUserMapper pbUserMapper;

        @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
        public void methodB1() {
            List<PbUser> allUser = pbUserMapper.getAllUser();
            pbUserMapper.id2Add();
            List<PbUser> allUser2 = pbUserMapper.getAllUser();
//            throw new RuntimeException();
        }
    }
}
