package com.mezjh.blog.transaction;

import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author ZJH
 * @date 2021/7/6 14:20
 */
public class JDBCTransaction {

    @Transactional
    public void test() throws SQLException {
        Connection connection = null;
        try{
            //开启事务
            connection.setAutoCommit(false);
            // 一些sql操作
            //try的最后提交事务
            connection.commit();
        } catch(Exception e) {
            //回滚事务
            connection.rollback();
        }
    }
}
