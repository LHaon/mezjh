package com.mezjh.blog.play.draw.domain.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * (User)表实体类
 * @Author: ZJH
 * @Date: 2022-01-27 14:08:41
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "user", autoResultMap = true)
public class User {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    @TableField("name")
    private String name;
    
    @TableField("photo")
    private String photo;
    
    @TableField("lucky_number")
    private Integer luckyNumber;
    
    @TableField("is_get")
    private Boolean isGet;
    
    @TableField("level")
    private String level;
    
}