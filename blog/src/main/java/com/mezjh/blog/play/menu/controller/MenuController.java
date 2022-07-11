package com.mezjh.blog.play.menu.controller;

import com.mezjh.blog.play.menu.service.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ZJH
 * @date 2020/10/23 17:06
 */
@Controller
@RequestMapping("/getMenu")
public class MenuController {

    @Resource
    private MenuService menuService;

    @GetMapping("{shopName}")
    public String getMenu(int num, int price, @PathVariable("shopName") String shopName, Model model) {
        String result = menuService.getTodayMenu(num, price, shopName, model);
        model.addAttribute("result", result);
        return "menu/menu";
    }

    @GetMapping("getMenuD")
    public String getToday() {
        String result = "今天中午可以吃：";
        return result + menuService.getMenuD();
    }

    @GetMapping("/love")
    public String getLove(Model model) {
        String result = menuService.getLove();
        model.addAttribute("result", result);
        return "menu/menu";
    }

    public static void main(String[] args) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String str1 = format.format(date);
        String str2 = new SimpleDateFormat("hhmmss").format(date);
        System.out.println(str1);
        System.out.println(str2);
        DecimalFormat decimalFormat = new DecimalFormat("000000000000");
        Long amount = 1000L;
        System.out.println(decimalFormat.format(amount));

    }
}
