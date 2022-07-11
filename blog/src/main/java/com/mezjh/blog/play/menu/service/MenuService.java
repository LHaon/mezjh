package com.mezjh.blog.play.menu.service;

import org.springframework.ui.Model;

/**
 * @author ZJH
 * @date 2020/10/23 17:17
 */
public interface MenuService {
    /**
     * 得到今日菜单
     * @param num
     * @param price
     * @return
     */
    String getTodayMenu(int num, int price, String shopName, Model model);

    String getMenuD();

    String getLove();
}
