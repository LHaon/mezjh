package com.mezjh.blog.transaction.propagationbehavior;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Spring事务传播行为四：RequiresNew 不管当前有没有事务都会新建一个事务，若当前存在事务，则会把当前事务挂起
 * @author ZJH
 * @date 2021/7/6 17:54
 */
@Service
public class Pb4RequiresNewA {

    @Resource
    private PbUserMapper pbUserMapper;
    @Resource
    private Pb4RequiresNewB pb4RequiresNewB;

    /**
     * 若当前存在事务，则会把当前事务挂起，并开启一个新事务
     * 结论:
     * 1.事务方法A1调用事务方法B1时，A1会被挂起，B1提交后才会继续执行A1
     * 2.A1中的异常不会影响B1中的操作回滚，B1中的异常也不会影响A1中的操作回滚，它们都只会影响自身的操作回滚
     * 3.这里需要注意的是，对数据的修改会对这行数据增加排他锁；若这里在A1有修改，未提交，此时B1新建一个事务
     * 也去修改相同数据，会获取锁超时。
     */
    @Transactional(rollbackFor = Exception.class)
    public void methodA1() {
        List<PbUser> allUser = pbUserMapper.getAllUser();
        pbUserMapper.id1Add();
        try {
            pb4RequiresNewB.methodB1();
        } catch (Exception e) {

        }
        List<PbUser> allUser2 = pbUserMapper.getAllUser();
        throw new RuntimeException();
    }

    @Service
    public class Pb4RequiresNewB {

        @Resource
        private PbUserMapper pbUserMapper;

        @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
        public void methodB1() {
            List<PbUser> allUser = pbUserMapper.getAllUser();
            pbUserMapper.id2Add();
            List<PbUser> allUser2 = pbUserMapper.getAllUser();
//            throw new RuntimeException();
        }
    }
}
