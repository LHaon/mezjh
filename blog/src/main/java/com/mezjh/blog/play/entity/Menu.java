package com.mezjh.blog.play.entity;

import lombok.Data;

/**
 * @author ZJH
 * @date 2020/10/23 17:22
 */
@Data
public class Menu {
    private Double price;
    private String name;

    public Menu(String name, Double price) {
        this.name = name;
        this.price = price;
    }
}
