package com.itheima.entity;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

/**
 * 角色实体类
 * 对应数据库表 role
 */
@Data
@TableName("role")
public class Role {

    /**
     * 角色id，主键
     */
    @TableId
    private Integer id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色标识/编码
     */
    private String label;

    /**
     * 角色备注/描述
     */
    private String remark;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;
}