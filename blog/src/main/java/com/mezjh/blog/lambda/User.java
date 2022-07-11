package com.mezjh.blog.lambda;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author ZJH
 * @date 2021/1/5 16:30
 */
@Data
@AllArgsConstructor
public class User {
    private String name;
    private int age;
    private Double salary;
}
