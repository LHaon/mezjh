package com.mezjh.blog.transaction.propagationbehavior;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Spring事务传播行为二：Supports  支持当前事务，若当前没有事务，则以非事务的方式运行
 * @author ZJH
 * @date 2021/7/6 17:54
 */
@Service
public class Pb2SupportsA {

    @Resource
    private PbUserMapper pbUserMapper;
    @Resource
    private Pb2SupportsB pb2SupportsB;

    /**
     * 若当前没有事务，则以非事务的方法运行
     * 结论：
     * 1.A1非事务方法，B1以非事务方法运行
     * 2.无论在A1还是B1中发生异常，A1和B1的操作都不会回滚
     */
    public void methodA1() {
        List<PbUser> allUser = pbUserMapper.getAllUser();
        allUser.forEach(x -> pbUserMapper.workAdd(x.getId()));
        pb2SupportsB.methodB1();
//        throw new RuntimeException();
    }

    /**
     * 若当前存在事务，则支持该事务
     * 结论：
     * 1.A2和B1都是事务方法，A2中调用B1，B1加入到A2并一起提交
     * 2.无论在A2还是B1中发生异常，A2和B1都会被回滚
     */
    @Transactional(rollbackFor = Exception.class)
    public void methodA2() {
        List<PbUser> allUser = pbUserMapper.getAllUser();
        pbUserMapper.id1Add();
        try {
            pb2SupportsB.methodB1();
        } catch (Exception e) {

        }
        List<PbUser> allUser2 = pbUserMapper.getAllUser();
        throw new RuntimeException();
    }


    @Service
    public class Pb2SupportsB {

        @Resource
        private PbUserMapper pbUserMapper;

        @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
        public void methodB1() {
            List<PbUser> allUser = pbUserMapper.getAllUser();
            pbUserMapper.id2Add();
            List<PbUser> allUser2 = pbUserMapper.getAllUser();
//            throw new RuntimeException();
        }
    }
}
