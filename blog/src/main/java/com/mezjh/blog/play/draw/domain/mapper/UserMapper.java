package com.mezjh.blog.play.draw.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mezjh.blog.play.draw.domain.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * (User)表数据库访问层
 * @Author: ZJH
 * @Date: 2022-01-27 14:08:41
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}