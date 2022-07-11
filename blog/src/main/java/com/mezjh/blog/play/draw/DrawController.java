package com.mezjh.blog.play.draw;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.mezjh.blog.play.draw.application.IUserService;
import com.mezjh.blog.play.draw.application.IYouWinService;
import com.mezjh.blog.play.draw.domain.model.User;
import com.mezjh.blog.play.draw.domain.model.YouWin;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ZJH
 * @date 2022/1/27 14:09
 */
@RestController
@RequestMapping("/play")
public class DrawController {

    @Resource
    private IUserService IUserService;
    @Resource
    private IYouWinService IYouWinService;

    @GetMapping("/win/{name}")
    @Transactional(rollbackFor = Exception.class)
    public Boolean win(@PathVariable("name") String name) {
        List<User> users = IUserService
                .list();
        List<YouWin> youWins = new ArrayList<>();
        users.forEach(user -> {
            if (user.getIsGet()) {
                YouWin youWin = new YouWin();
                youWin.setName(user.getName());
                youWins.add(youWin);
            }
            if (user.getName().equals(name)) {
                YouWin youWin = new YouWin();
                youWin.setName(name);
                youWins.add(youWin);
                user.setIsGet(false);
            } else {
                user.setIsGet(true);
            }
        });
//        IYouWinService.remove(new LambdaQueryWrapper<>());
        IYouWinService.saveBatch(youWins);
        IUserService.saveOrUpdateBatch(users);
        return true;
    }

    @GetMapping("/reset")
    @Transactional(rollbackFor = Exception.class)
    public Boolean reset() {
        IUserService.update(new LambdaUpdateWrapper<User>()
                .set(User::getIsGet, false));
        List<YouWin> youWins = IYouWinService.list();
        if (!CollectionUtils.isEmpty(youWins)) {
            youWins.forEach(youWin -> IUserService.update(new LambdaUpdateWrapper<User>()
                    .eq(User::getName, youWin.getName())
                    .set(User::getIsGet, true)));
        }
        IYouWinService.remove(new LambdaQueryWrapper<>());
        return true;
    }
}
