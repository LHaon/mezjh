package com.mezjh.blog.play.draw.application.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mezjh.blog.play.draw.application.IYouWinService;
import com.mezjh.blog.play.draw.domain.mapper.YouWinMapper;
import com.mezjh.blog.play.draw.domain.model.YouWin;
import org.springframework.stereotype.Service;

/**
 * (YouWin)表服务实现类
 * @Author: ZJH
 * @Date: 2022-01-27 14:09:28
 */
@Service("youWinService")
public class IYouWinServiceImpl extends ServiceImpl<YouWinMapper, YouWin> implements IYouWinService {

}