package com.mezjh.blog.play.draw.application.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mezjh.blog.play.draw.application.IUserService;
import com.mezjh.blog.play.draw.domain.mapper.UserMapper;
import com.mezjh.blog.play.draw.domain.model.User;
import org.springframework.stereotype.Service;

/**
 * (User)表服务实现类
 * @Author: ZJH
 * @Date: 2022-01-27 14:08:41
 */
@Service("userService")
public class IUserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}