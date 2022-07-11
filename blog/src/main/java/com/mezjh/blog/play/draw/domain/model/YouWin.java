package com.mezjh.blog.play.draw.domain.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * (YouWin)表实体类
 * @Author: ZJH
 * @Date: 2022-01-27 14:09:28
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "you_win", autoResultMap = true)
public class YouWin {

    @TableField("name")
    private String name;
    
}