package com.mezjh.blog.transaction.propagationbehavior;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author ZJH
 * @date 2021/7/6 16:48
 */
@Mapper
public interface PbUserMapper {

    /**
     * 查询所有用户
     * @return
     */
    @Select("select * from pb_user")
    List<PbUser> getAllUser();

    /**
     * 年龄增加1
     * @param id 用户ID
     */
    @Update("update pb_user set age=age+1 where id=#{id}")
    void ageAdd(Integer id);

    /**
     * 工作加1
     * @param id    用户ID
     */
    @Update("update pb_user set work=work+1 where id=#{id}")
    void workAdd(Integer id);

    @Update("update pb_user set work=work+1,age=age+1 where id=1")
    void id1Add();

    @Update("update pb_user set work=work+1,age=age+1 where id=2")
    void id2Add();
}
