package com.mezjh.blog.play.menu.service;

import com.mezjh.blog.play.entity.Menu;
import com.mezjh.blog.play.menu.constant.ShopNames;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * @author ZJH
 * @date 2020/10/23 17:18
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService{

    private Map<Integer, Menu> vaTableXjj;
    private Map<Integer, Menu> meatXjj;
    private Map<Integer, Menu> vaTableSwf;
    private Map<Integer, Menu> meatSwf;
    private Map<Integer, Menu> foodD;
    private Map<Integer, Menu> foodLh;
    private HashSet<String> reMenu = new HashSet<>();


    @PostConstruct
    public void init() {
        initVeXjj();
        initMeatXjj();
        initVeSwf();
        initMeatSwf();
        initD();
        initLove();
    }

    @Override
    public String getTodayMenu(int num, int price, String shopName, Model model) {
        String result = "";
        int veNum = (num - 1) / 2;
        int meNum = num - veNum - 1;
        Double sumPrice = 0.0;
        int breakNum = 0;
        while (true) {
            result = "";
            List<Menu> menus = getMenuByShop(veNum, meNum, shopName);
            for (Menu menu : menus) {
                result += (menu.getName() + "，");
                sumPrice += menu.getPrice();
            }
            sumPrice += num * 2;
            if (sumPrice < Double.parseDouble(String.valueOf(price)) || breakNum > 3) {
                model.addAttribute("allPrice", "总价格为：" + sumPrice);
                if (breakNum > 3) {
                    result = "";
                    model.addAttribute("allPrice", "没有找到合适价格的菜，请重置价格");
                }
                break;
            }
            menus.clear();
            breakNum ++;
        }
        return result;
    }

    @Override
    public String getMenuD() {
        Integer num = new Random().nextInt(6) + 1;
        return foodD.get(num).getName();
    }

    @Override
    public String getLove() {
        Integer num = new Random().nextInt(8) + 1;
        return foodLh.get(num).getName();
    }

    private List<Menu> getMenuByShop(int veNum, int meNum, String shopName) {
        List<Menu> result = new ArrayList<>();
        HashMap<Integer, Boolean> flag = new HashMap<>();
        for(int i = 0;i < 30;i++) {
            flag.put(i, false);
        }
        if (shopName.equals(ShopNames.swf)) {
            veNum --;
            while (veNum > 0) {
                Integer num = new Random().nextInt(vaTableSwf.size()) + 1;
                if (!flag.get(num) && repeat(num, vaTableSwf)) {
                    flag.put(num, true);
                    result.add(vaTableSwf.get(num));
                    veNum --;
                }
            }
            result.add(new Menu("番茄蛋汤", 14.0));
            for(int i = 0;i < 30;i++) {
                flag.put(i, false);
            }
            while(meNum > 0) {
                Integer num = new Random().nextInt(meatSwf.size()) + 1;
                if (!flag.get(num) && repeat(num, meatSwf)) {
                    flag.put(num, true);
                    result.add(meatSwf.get(num));
                    meNum --;
                }
            }
        } else if (shopName.equals(ShopNames.xjj)) {
            while (veNum > 0) {
                Integer num = new Random().nextInt(vaTableXjj.size()) + 1;
                if (!flag.get(num) && repeat(num, vaTableXjj)) {
                    flag.put(num, true);
                    result.add(vaTableXjj.get(num));
                    veNum --;
                }
            }
            for(int i = 0;i < 30;i++) {
                flag.put(i, false);
            }
            while(meNum > 0) {
                Integer num = new Random().nextInt(meatXjj.size()) + 1;
                if (!flag.get(num) && repeat(num, meatXjj)) {
                    flag.put(num, true);
                    result.add(meatXjj.get(num));
                    meNum --;
                }
            }
        }
        reMenu.clear();
        return result;
    }

    private boolean repeat(Integer num, Map<Integer, Menu> menu) {
        String name = menu.get(num).getName();
        String frontName = name.substring(0, 2);
        String afterName = name.substring(name.length() - 2, name.length());
        if (reMenu.contains(frontName) || reMenu.contains(afterName)) {
            return false;
        } else {
            reMenu.add(frontName);
            reMenu.add(afterName);
            return true;
        }
    }


    private void initVeXjj() {
        this.vaTableXjj = new HashMap<>();
        vaTableXjj.put(1, new Menu("番茄炒蛋", 18.0));
        vaTableXjj.put(2, new Menu("韭菜炒蛋", 18.0));
        vaTableXjj.put(3, new Menu("炒时蔬", 12.0));
        vaTableXjj.put(4, new Menu("土豆丝", 12.0));
        vaTableXjj.put(5, new Menu("麻婆豆腐", 16.0));
        vaTableXjj.put(6, new Menu("鱼香茄子", 16.0));
        vaTableXjj.put(7, new Menu("豇豆茄子", 16.0));
        vaTableXjj.put(8, new Menu("手撕包菜", 16.0));
    }

    private void initMeatXjj() {
        this.meatXjj = new HashMap<>();
        meatXjj.put(1, new Menu("土豆肉丝", 24.0));
        meatXjj.put(2, new Menu("洋葱肉丝", 24.0));
        meatXjj.put(3, new Menu("千叶豆腐", 24.0));
        meatXjj.put(4, new Menu("泡椒猪肝", 26.0));
        meatXjj.put(5, new Menu("烂肉豇豆", 26.0));
        meatXjj.put(6, new Menu("豆腐干肉丝", 26.0));
        meatXjj.put(7, new Menu("青椒肉丝", 28.0));
        meatXjj.put(8, new Menu("木耳肉丝", 28.0));
        meatXjj.put(9, new Menu("鱼香肉丝", 28.0));
        meatXjj.put(10, new Menu("尖椒肉丝", 28.0));
        meatXjj.put(11, new Menu("宫保鸡丁", 28.0));
        meatXjj.put(12, new Menu("鸡米芽菜", 28.0));
        meatXjj.put(13, new Menu("川香回锅肉", 32.0));
        meatXjj.put(14, new Menu("肝腰合炒", 32.0));
        meatXjj.put(15, new Menu("火爆鸡杂", 32.0));
    }

    private void initD() {
        foodD = new HashMap<>();
        foodD.put(1, new Menu("酸菜饼", 0.0));
        foodD.put(2, new Menu("冒菜", 0.0));
        foodD.put(3, new Menu("饭", 0.0));
        foodD.put(4, new Menu("炒饭", 0.0));
        foodD.put(5, new Menu("米线", 0.0));
        foodD.put(6, new Menu("肥肠粉", 0.0));
    }

    private void initLove() {
        foodLh = new HashMap<>();
        foodLh.put(1, new Menu("双拼", 0.0));
        foodLh.put(2, new Menu("意面", 0.0));
        foodLh.put(3, new Menu("肠粉", 0.0));
        foodLh.put(4, new Menu("寿司", 0.0));
        foodLh.put(5, new Menu("炒饭", 0.0));
        foodLh.put(6, new Menu("螺狮粉", 0.0));
        foodLh.put(7, new Menu("麻辣烫", 0.0));
        foodLh.put(8, new Menu("炸串", 0.0));
    }

    private void initVeSwf() {
        vaTableSwf = new HashMap<>();
        vaTableSwf.put(1, new Menu("时令蔬菜", 12.0));
        vaTableSwf.put(2, new Menu("土豆丝", 12.0));
        vaTableSwf.put(3, new Menu("莲白粉丝", 16.0));
        vaTableSwf.put(4, new Menu("麻婆豆腐", 16.0));
        vaTableSwf.put(5, new Menu("韭菜豆干", 16.0));
        vaTableSwf.put(6, new Menu("番茄炒蛋", 16.0));
        vaTableSwf.put(7, new Menu("韭黄炒蛋", 18.0));
        vaTableSwf.put(8, new Menu("油渣莲白", 16.0));
        vaTableSwf.put(9, new Menu("茄子豇豆", 18.0));
        vaTableSwf.put(10, new Menu("烂肉豇豆", 18.0));
    }

    private void initMeatSwf() {
        meatSwf = new HashMap<>();
        meatSwf.put(1, new Menu("回锅肉", 35.0));
        meatSwf.put(2, new Menu("莲白回锅", 35.0));
        meatSwf.put(3, new Menu("葱香牛肉", 38.0));
        meatSwf.put(4, new Menu("韭黄肉丝", 26.0));
        meatSwf.put(5, new Menu("烂肉粉丝", 22.0));
        meatSwf.put(6, new Menu("宫保鸡丁", 30.0));
        meatSwf.put(7, new Menu("火爆腰花", 35.0));
        meatSwf.put(8, new Menu("鱼香肉丝", 28.0));
        meatSwf.put(9, new Menu("青笋木耳肉片", 26.0));
        meatSwf.put(10, new Menu("酱肉丝", 32.0));
        meatSwf.put(11, new Menu("肝腰合炒", 28.0));
        meatSwf.put(12, new Menu("泡椒鸡杂", 26.0));
        meatSwf.put(13, new Menu("青椒肉丝", 26.0));
        meatSwf.put(14, new Menu("水煮肉片", 30.0));
        meatSwf.put(15, new Menu("盐煎肉", 28.0));
        meatSwf.put(16, new Menu("麻辣茄子", 22.0));
    }
}
